package com.dooho.board.controller

import com.dooho.board.dto.ResponseDto
import com.dooho.board.dto.user.MyPageDto
import com.dooho.board.dto.user.PatchUserDto
import com.dooho.board.dto.user.PatchUserResponseDto
import com.dooho.board.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController @Autowired constructor(private val userService: UserService) {
    @CrossOrigin(origins = ["*"])
    @PatchMapping("/edit")
    fun patchUser(
        @RequestBody requestBody: PatchUserDto?,
        @AuthenticationPrincipal userEmail: String?
    ): ResponseDto<PatchUserResponseDto?> {
        return userService.patchUser(requestBody, userEmail)
    }

    @GetMapping("/myPage")
    fun myPage(@AuthenticationPrincipal userEmail: String?): ResponseDto<MyPageDto?> {
        return userService.myPage(userEmail)
    }
}