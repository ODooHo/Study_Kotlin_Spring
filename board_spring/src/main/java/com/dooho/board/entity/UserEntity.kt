package com.dooho.board.entity

import com.dooho.board.dto.auth.SignUpDto
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "User")
data class UserEntity(
    @Id
    var userEmail: String?,
    var userPassword: String?,
    var userNickname: String?,
    var userPhoneNumber: String?,
    var userAddress: String?,
    var userProfile: String?
) {
    constructor(dto: SignUpDto?) : this(
        dto?.userEmail,
        dto?.userPassword,
        dto?.userNickname,
        dto?.userPhoneNumber,
        dto?.userAddress,
        dto?.userProfile
    )

    constructor() : this(null, null, null, null, null, null)


    // equals, hashCode, toString 메서드는 자동으로 생성됨
}