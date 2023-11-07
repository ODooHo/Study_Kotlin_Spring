package com.dooho.board.controller

import com.dooho.board.dto.ResponseDto
import com.dooho.board.dto.auth.RefreshResponseDto
import com.dooho.board.dto.auth.SignInDto
import com.dooho.board.dto.auth.SignInResponseDto
import com.dooho.board.dto.auth.SignUpDto
import com.dooho.board.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController @Autowired constructor(private val authService: AuthService) {
    @PostMapping("/signUp")
    fun signUp(@RequestBody requestBody: SignUpDto?): ResponseDto<*> {
        return authService.signUp(requestBody)
    }

    @PostMapping("/signIn")
    fun signIn(@RequestBody requestBody: SignInDto?): ResponseDto<out SignInResponseDto?> {
        return authService.signIn(requestBody)
    }

    @PostMapping("/getAccess")
    fun getAccess(@RequestBody refreshToken: String?): ResponseDto<RefreshResponseDto?> {
        return authService.getAccess(refreshToken)
    }
}