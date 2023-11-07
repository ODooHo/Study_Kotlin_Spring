package com.dooho.board.service

import com.dooho.board.dto.ResponseDto
import com.dooho.board.dto.auth.RefreshResponseDto
import com.dooho.board.dto.auth.SignInDto
import com.dooho.board.dto.auth.SignInResponseDto
import com.dooho.board.dto.auth.SignUpDto
import com.dooho.board.entity.UserEntity
import com.dooho.board.repository.UserRepository
import com.dooho.board.security.TokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService @Autowired constructor(
    private val userRepository: UserRepository,
    private val tokenProvider: TokenProvider
) {
    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()
    fun signUp(dto: SignUpDto?): ResponseDto<*> {
        val userEmail = dto?.userEmail
        val userPassword = dto?.userPassword
        val userPasswordCheck = dto?.userPasswordCheck
        val userNickname = dto?.userNickname

        //이메일 중복 확인
        try {
            if (userRepository.existsById(userEmail)) {
                return ResponseDto.setFailed<Any>("Email already exist!")
            }
            if (userRepository.existsByUserNickname(userNickname)) {
                return ResponseDto.setFailed<Any>("Nickname already exist!")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseDto.setFailed<Any>("DataBase Error!")
        }

        // 비밀번호가 다르면 failed response
        if (userPassword != userPasswordCheck) {
            return ResponseDto.setFailed<Any>("password does not matched!")
        }

        // userEntity 생성,
        val userEntity = UserEntity(dto)
        //비밀번호 암호화
        val encodedPassword = passwordEncoder.encode(userPassword)
        userEntity.userPassword = encodedPassword

        //repository(db)에 저장
        try {
            userRepository.save(userEntity)
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseDto.setFailed<Any>("DataBase Error!")
        }
        return ResponseDto.setSuccess<Any?>("Sign Up Success!", null)
    }

    fun signIn(dto: SignInDto?): ResponseDto<out SignInResponseDto?> {
        val userEmail = dto?.userEmail
        val userPassword = dto?.userPassword
        var userEntity: UserEntity? = null
        try {
            userEntity = userRepository.findByUserEmail(userEmail)
            //잘못된 이메일
            if (userEntity == null) return ResponseDto.setFailed<SignInResponseDto>("Sign in Failed")
            //잘못된 패스워드
            if (!passwordEncoder.matches(
                    userPassword,
                    userEntity.userPassword
                )
            ) return ResponseDto.setFailed<SignInResponseDto>("Sign in Failed")
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseDto.setFailed<Any>("DataBase Error!")
        }
        userEntity!!.userPassword = ""
        val token = tokenProvider.createAccessToken(userEmail)
        val exprTime = 1800000
        val refreshToken = tokenProvider.createRefreshToken(userEmail)
        val refreshExprTime = 360000000
        val signInResponseDto = SignInResponseDto(token, exprTime, refreshToken, refreshExprTime, userEntity)
        return ResponseDto.setSuccess<SignInResponseDto>("Sign in Success", signInResponseDto)
    }

    fun getAccess(refreshToken: String?): ResponseDto<RefreshResponseDto?> {
        return try {
            val accessToken = tokenProvider.createAccessTokenFromRefreshToken(refreshToken)
            val exprTime = 1800000
            val refreshResponseDto = RefreshResponseDto(accessToken, exprTime)
            ResponseDto.setSuccess("Success", refreshResponseDto)
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseDto.setFailed("DataBase Error!")
        }
    }
}