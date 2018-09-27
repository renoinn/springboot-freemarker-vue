package com.sample.app.domain.service

import com.sample.app.domain.entity.User
import java.util.*

interface UserService {
    fun tryCreate(user: User): Optional<User>
    fun findByUsername(username: String): Optional<User>
    fun findById(id: Long): Optional<User>
}