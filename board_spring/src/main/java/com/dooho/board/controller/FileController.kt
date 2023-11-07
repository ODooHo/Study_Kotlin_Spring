package com.dooho.board.controller

import com.dooho.board.dto.ResponseDto
import com.dooho.board.service.FileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api")
class FileController @Autowired constructor(private val fileService: FileService) {
    @PostMapping("/upload/profile")
    fun setProfile(
        @RequestParam("file") file: MultipartFile?,
        @AuthenticationPrincipal userEmail: String?
    ): ResponseDto<String?> {
        return fileService.setProfile(file!!, userEmail)
    }

    @GetMapping("/images/{imageName}/profile")
    @Throws(IOException::class)
    fun getProfileImage(@PathVariable imageName: String?): ResponseEntity<ByteArray> {
        return fileService.getProfileImage(imageName!!)
    }

    @GetMapping("/images/{imageName}")
    @Throws(IOException::class)
    fun getImage(@PathVariable imageName: String?): ResponseEntity<ByteArray?> {
        return fileService.getImage(imageName!!)
    }

    @GetMapping("/videos/{videoName}")
    @Throws(IOException::class)
    fun getVideo(@PathVariable videoName: String?): ResponseEntity<Resource?> {
        return fileService.getVideo(videoName!!)
    }

    @GetMapping("/files/{fileName}")
    @Throws(IOException::class)
    fun getFile(@PathVariable fileName: String?): ResponseEntity<ByteArray?> {
        return fileService.getFile(fileName!!)
    }
}