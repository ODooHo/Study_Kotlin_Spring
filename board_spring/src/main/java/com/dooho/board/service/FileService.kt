package com.dooho.board.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.AmazonS3Exception
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.util.IOUtils
import com.dooho.board.dto.ResponseDto
import com.dooho.board.entity.BoardEntity
import com.dooho.board.repository.BoardRepository
import com.dooho.board.repository.CommentRepository
import com.dooho.board.repository.UserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FilenameFilter
import java.io.IOException

@Service
class FileService(
    private val userRepository: UserRepository,
    private val boardRepository: BoardRepository,
    private val commentRepository: CommentRepository,
    private val amazonS3: AmazonS3
) {
    @Value("\${cloud.aws.s3.bucket}")
    private val bucketName: String? = null

    @Value("\${file.upload-dir}")
    private val uploadDir: String? = null

    @Value("\${default.image.extension}")
    private val defaultImageExtension: String? = null

    @Value("\${default.video.extension}")
    private val defaultVideoExtension: String? = null

    @Value("\${default.file.extension}")
    private val defaultFileExtension: String? = null

    fun uploadFile(
        boardImage: MultipartFile?,
        boardVideo: MultipartFile?,
        boardFile: MultipartFile?,
        board: BoardEntity
    ): ResponseDto<String?> {
        try {
            if (boardImage != null) {
                val fileName = setFileName(boardImage, board)
                val imagePath = uploadDir + "img/" + fileName
                uploadFileToS3(boardImage, imagePath)
                board.boardImage = fileName
            } else {
                board.boardImage = null
            }
            if (boardVideo != null) {
                val fileName = setFileName(boardVideo, board)
                val videoPath = uploadDir + "video/" + fileName
                uploadFileToS3(boardVideo, videoPath)
                board.boardVideo = fileName
            } else {
                board.boardVideo = null
            }
            if (boardFile != null) {
                val fileName = setFileName(boardFile, board)
                val filePath = uploadDir + "file/" + fileName
                uploadFileToS3(boardFile, filePath)
                board.boardFile = fileName
            } else {
                board.boardFile = null
            }
            boardRepository.save(board)
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseDto.setFailed("DataBase Error!")
        }
        return ResponseDto.setSuccess("Success", null)
    }

    fun setProfile(file: MultipartFile, userEmail: String?): ResponseDto<String?> {
        val user = userRepository.findByUserEmail(userEmail)
        val commentEntity = commentRepository.findByUserEmail(userEmail)
        val boardEntity = boardRepository.findByBoardWriterEmail(userEmail)
        val fileName = "${user!!.userEmail}.jpg"
        return try {
            // S3 버킷에 파일 업로드
            uploadFileToS3(file, "${uploadDir}img/$fileName")
            user.userProfile = fileName
            userRepository.save(user)
            commentEntity?.forEach { comment ->
                comment?.commentUserProfile = fileName
                commentRepository.save(comment)
            }
            boardEntity?.forEach { board ->
                board?.boardWriterProfile = fileName
                boardRepository.save(board)
            }
            ResponseDto.setSuccess("Success", fileName)
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseDto.setFailed("Database or S3 Error")
        }
    }

    @Throws(IOException::class)
    fun getProfileImage(imageName: String): ResponseEntity<ByteArray> {
        return try {
            val extension = getExtension("", imageName)
            val fileName = "$imageName$extension"
            val s3Object = amazonS3.getObject(bucketName, "${uploadDir}img/$fileName")
            val objectInputStream = s3Object.objectContent
            val imageData = IOUtils.toByteArray(objectInputStream)
            val headers = HttpHeaders()
            var mediaType = MediaType.IMAGE_JPEG
            if (extension.equals(".jpg", ignoreCase = true) || extension.equals(".jpeg", ignoreCase = true)) {
                mediaType = MediaType.IMAGE_JPEG
            } else if (extension.equals(".png", ignoreCase = true)) {
                mediaType = MediaType.IMAGE_PNG
            }
            headers.contentType = mediaType
            ResponseEntity.ok()
                .headers(headers)
                .body(imageData)
        } catch (e: AmazonS3Exception) {
            ResponseEntity.notFound().build()
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @Throws(IOException::class)
    fun getImage(imageName: String): ResponseEntity<ByteArray?> {
        if (imageName == "null") {
            return ResponseEntity.ok().body(null)
        }
        val extension = getExtension("", imageName)
        val fileName = "$imageName$extension"
        return try {
            val s3Object = amazonS3.getObject(bucketName, "${uploadDir}img/$fileName")
            val objectInputStream = s3Object.objectContent
            val imageData = objectInputStream.readAllBytes()
            val headers = HttpHeaders()
            var mediaType = MediaType.IMAGE_JPEG
            if (extension.equals(".jpg", ignoreCase = true) || extension.equals(".jpeg", ignoreCase = true)) {
                mediaType = MediaType.IMAGE_JPEG
            } else if (extension.equals(".png", ignoreCase = true)) {
                mediaType = MediaType.IMAGE_PNG
            }
            headers.contentType = mediaType
            ResponseEntity.ok()
                .headers(headers)
                .body(imageData)
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.ok().body(null)
        }
    }

    @Throws(IOException::class)
    fun getVideo(videoName: String): ResponseEntity<Resource?> {
        return if (videoName == "null") {
            ResponseEntity.ok().body(null)
        } else try {
            val s3Object = amazonS3.getObject(bucketName, "${uploadDir}video/$videoName")
            val objectInputStream = s3Object.objectContent
            val headers = HttpHeaders()
            headers.contentType = MediaType.valueOf("video/mp4")
            ResponseEntity.ok()
                .headers(headers)
                .body(InputStreamResource(objectInputStream))
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.notFound().build()
        }
    }

    @Throws(IOException::class)
    fun getFile(fileId: String): ResponseEntity<ByteArray?> {
        return if (fileId == "null") {
            ResponseEntity.ok().body(null)
        } else try {
            val s3Object = amazonS3.getObject(bucketName, "${uploadDir}file/$fileId")
            val objectInputStream = s3Object.objectContent
            val fileData = IOUtils.toByteArray(objectInputStream)
            val headers = HttpHeaders()
            headers.contentType = MediaType.APPLICATION_OCTET_STREAM
            headers.setContentDispositionFormData("attachment", fileId)
            ResponseEntity.ok()
                .headers(headers)
                .body(fileData)
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.notFound().build()
        }
    }

    @Throws(IOException::class)
    private fun getExtension(fileDirectory: String, fileId: String): String {
        val folder = File(fileDirectory)
        val filter = FilenameFilter { dir: File?, name: String ->
            try {
                return@FilenameFilter name.startsWith(fileId)
            } catch (e: Exception) {
                e.printStackTrace()
                return@FilenameFilter false
            }
        }
        val files = folder.list(filter)
        if (files == null || files.isEmpty()) {
            return ""
        }
        val fileName = files[0]

        val dotIndex = fileName.lastIndexOf('.')
        return if (dotIndex > 0 && dotIndex < fileName.length - 1) {
            fileName.substring(dotIndex)
        } else ""
    }

    private fun uploadFileToS3(file: MultipartFile, s3Key: String) {
        try {
            val inputStream = file.inputStream
            val metadata = ObjectMetadata()
            metadata.contentLength = file.size
            amazonS3.putObject(PutObjectRequest(bucketName, s3Key, inputStream, metadata))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setFileName(file: MultipartFile, board: BoardEntity): String {
        val originalFileName = file.originalFilename
        val extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1)
        return "${board.boardNumber}.$extension"
    }
}
