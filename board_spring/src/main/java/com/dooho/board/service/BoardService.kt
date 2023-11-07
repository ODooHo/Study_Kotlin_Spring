package com.dooho.board.service

import com.dooho.board.dto.ResponseDto
import com.dooho.board.dto.ResponseDto.Companion.setFailed
import com.dooho.board.dto.ResponseDto.Companion.setSuccess
import com.dooho.board.dto.board.PatchBoardDto
import com.dooho.board.dto.board.PatchBoardResponseDto
import com.dooho.board.entity.BoardEntity
import com.dooho.board.repository.BoardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Service
class BoardService @Autowired constructor(
    private val boardRepository: BoardRepository,
    private val fileService: FileService
) {
    fun getBoard(boardNumber: Int?): ResponseDto<BoardEntity?> {
        try {
            val board = boardRepository.findByBoardNumber(boardNumber)
            // 게시글이 존재하면 board 객체의 boardClickCount를 1 증가시킴
            if (board != null) {
                val newClickCount = board.boardClickCount + 1
                board.boardClickCount = newClickCount
                boardRepository.save(board)
                return setSuccess("Success", board)
            } else {
                return setFailed("Board not found")
            }
        } catch (e: Exception) {
            return setFailed("Database Error!")
        }
    }

    fun register(
        boardTitle: String?,
        boardContent: String?,
        boardWriterEmail: String?,
        boardWriterProfile: String?,
        boardWriterNickname: String?,
        boardWriteDate: String?,
        boardImage: MultipartFile?,
        boardVideo: MultipartFile?,
        boardFile: MultipartFile?
    ): ResponseDto<BoardEntity?> {
        val formatter = DateTimeFormatter.ISO_INSTANT
        val zonedDateTime = ZonedDateTime.parse(boardWriteDate, formatter.withZone(ZoneId.of("UTC")))
        val localDate = zonedDateTime.toLocalDate()
        val boardEntity = BoardEntity(
            boardTitle = boardTitle,
            boardContent = boardContent,
            boardWriterEmail = boardWriterEmail,
            boardWriterProfile = boardWriterProfile,
            boardWriterNickname = boardWriterNickname,
            boardWriteDate = localDate
        )

        if (boardRepository.existsByBoardTitle(boardTitle)) {
            return setFailed("Same Title already exists!")
        }

        boardRepository.save(boardEntity)

        try {
            // fileService.uploadFile(boardImage, boardVideo, boardFile, boardEntity)
            boardRepository.save(boardEntity)
        } catch (e: Exception) {
            return setFailed("Database Error!")
        }

        return setSuccess("Register Success!", boardEntity)
    }

    val top3: ResponseDto<List<BoardEntity?>?>
        get() {
            var boardList: List<BoardEntity?>? = ArrayList()
            val date = LocalDate.now().minusDays(365)
            boardList = try {
                boardRepository.findTop3ByBoardWriteDateAfterOrderByBoardLikeCountDesc(date)
            } catch (e: Exception) {
                e.printStackTrace()
                return setFailed<List<BoardEntity?>?>("DataBase Error")
            }
            return setSuccess("Success", boardList)
        }
    val list: ResponseDto<List<BoardEntity?>?>
        get() {
            var boardList: List<BoardEntity?>? = ArrayList()
            boardList = try {
                boardRepository.findByOrderByBoardWriteDateDescBoardNumberDesc()
            } catch (e: Exception) {
                e.printStackTrace()
                return setFailed<List<BoardEntity?>?>("DataBase Error")
            }
            return setSuccess("Success", boardList)
        }

    fun deleteBoard(boardNumber: Int?): ResponseDto<*> {
        try {
            println("boardNumber = $boardNumber")
            boardRepository.deleteBoardEntityByBoardNumber(boardNumber)
        } catch (e: Exception) {
            e.printStackTrace()
            return setFailed<Any>("DataBase Error!")
        }
        return setSuccess<Any?>("Success", null)
    }


    fun editBoard(boardNumber: Int?, dto: PatchBoardDto?): ResponseDto<PatchBoardResponseDto?> {
        var board: BoardEntity? = null
        val boardTitle = dto?.boardTitle
        val boardContent = dto?.boardContent
        val boardWriteDate = dto?.boardWriteDate
        try {
            board = boardRepository.findByBoardNumber(boardNumber)
            board?.boardTitle = boardTitle
            board?.boardContent = boardContent
            board?.boardWriteDate =boardWriteDate
            boardRepository.save(board)
        } catch (e: Exception) {
            e.printStackTrace()
            return setFailed("DataBase Error!")
        }
        val patchBoardResponseDto = PatchBoardResponseDto(board)
        return setSuccess("Success!", patchBoardResponseDto)
    }
}