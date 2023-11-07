package com.dooho.board.service

import com.dooho.board.dto.ResponseDto
import com.dooho.board.dto.ResponseDto.Companion.setFailed
import com.dooho.board.dto.ResponseDto.Companion.setSuccess
import com.dooho.board.dto.user.MyPageDto
import com.dooho.board.dto.user.PatchUserDto
import com.dooho.board.dto.user.PatchUserResponseDto
import com.dooho.board.entity.BoardEntity
import com.dooho.board.entity.CommentEntity
import com.dooho.board.entity.UserEntity
import com.dooho.board.repository.BoardRepository
import com.dooho.board.repository.CommentRepository
import com.dooho.board.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository,
    private val boardRepository: BoardRepository,
    private val commentRepository: CommentRepository
) {
    fun myPage(userEmail: String?): ResponseDto<MyPageDto?> {
        var user: UserEntity? = null
        var board: List<BoardEntity?>? = ArrayList()
        try {
            user = userRepository.findByUserEmail(userEmail)
            board = boardRepository.findByBoardWriterEmail(userEmail)
        } catch (e: Exception) {
            return setFailed("Does Not Exist User")
        }
        val dto = MyPageDto(userEmail, userNickname = user?.userNickname, userProfile = user?.userProfile, userBoard = board)
        return setSuccess("Success", dto)
    }

    fun patchUser(requestBody: PatchUserDto?, userEmail: String?): ResponseDto<PatchUserResponseDto?> {
        var userEntity: UserEntity? = null
        var boardEntity: List<BoardEntity?>? = ArrayList()
        var commentEntity: List<CommentEntity?>? = ArrayList()
        val userNickname: String? = requestBody?.userNickname
        try {
            userEntity = userRepository.findByUserEmail(userEmail)
            commentEntity = commentRepository.findByUserEmail(userEmail)
            boardEntity = boardRepository.findByBoardWriterEmail(userEmail)
            if (userEntity == null) {
                return setFailed("Does Not Exist User")
            }
            userEntity.userNickname = userNickname
            userRepository.save(userEntity)
            for (board in boardEntity!!) {
                board?.boardWriterNickname = userNickname
                boardRepository.save(board)
            }
            for (comment in commentEntity!!) {
                comment!!.commentUserNickname = userNickname
                commentRepository.save(comment)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return setFailed("DataBase Error!")
        }
        userEntity.userPassword = ""
        val patchUserResponseDto = PatchUserResponseDto(userEntity)
        return setSuccess("Success", patchUserResponseDto)
    }
}