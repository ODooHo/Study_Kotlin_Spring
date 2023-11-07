package com.dooho.board.service

import com.dooho.board.dto.ResponseDto
import com.dooho.board.dto.ResponseDto.Companion.setFailed
import com.dooho.board.dto.ResponseDto.Companion.setSuccess
import com.dooho.board.dto.board.LikyDto
import com.dooho.board.entity.LikyEntity
import com.dooho.board.repository.BoardRepository
import com.dooho.board.repository.LikyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LikyService @Autowired constructor(
    private val likyRepository: LikyRepository,
    private val boardRepository: BoardRepository
) {
    fun addLiky(dto: LikyDto?): ResponseDto<*> {
        val likyEntity = LikyEntity(dto)
        likyRepository.save(likyEntity)
        try {

            // 해당 게시글의 좋아요 개수 증가
            val board = boardRepository.findByBoardNumber(dto?.boardNumber)
            if (board != null) {
                board.boardLikeCount = board.boardLikeCount + 1
                boardRepository.save(board)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return setFailed<Any>("DataBase Error")
        }
        return setSuccess<Any?>("Success", null)
    }

    fun getLikyCount(boardNumber: Int?): ResponseDto<Int?> {
        val boardEntity = boardRepository.findById(boardNumber).orElse(null)!!
        var temp: Int? = 0
        try {
            temp = likyRepository.countByBoardNumber(boardNumber)
            if (temp != null) {
                boardEntity.boardLikeCount = temp
            }
            boardRepository.save(boardEntity)
        } catch (e: Exception) {
            e.printStackTrace()
            return setFailed("DataBase Error")
        }
        return setSuccess("Success", temp)
    }

    fun getLiky(boardNumber: Int?): ResponseDto<List<LikyEntity?>?> {
        var likyEntity: List<LikyEntity?>? = ArrayList()
        likyEntity = try {
            likyRepository.findByBoardNumber(boardNumber)
        } catch (e: Exception) {
            e.printStackTrace()
            return setFailed<List<LikyEntity?>?>("DataBase Error")
        }
        return setSuccess("Success", likyEntity)
    }

    fun deleteLiky(boardNumber: Int?, likeUserNickname: String?): ResponseDto<*> {
        try {
            // 데이터베이스에서 사용자의 닉네임과 게시글 번호에 해당하는 좋아요 삭제
            likyRepository.deleteByBoardNumberAndLikeUserNickname(boardNumber, likeUserNickname)
            // 해당 게시글의 좋아요 개수 감소
            val board = boardRepository.findByBoardNumber(boardNumber)
            if (board != null) {
                board.boardLikeCount = board.boardLikeCount - 1
                boardRepository.save(board)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return setFailed<Any>("DataBase Error")
        }
        return setSuccess<Any?>("Success", null)
    }
}