package com.yuniq.yuniq

import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long> {
    fun findByUserId(userId: String): User?
}