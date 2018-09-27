package com.sample.app.task

import com.sample.app.domain.entity.User
import com.sample.app.domain.service.UserService
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SetupDataTask(
        private val userService: UserService
) : ApplicationListener<ContextRefreshedEvent> {
    private var alreadySetup = false

    @Transactional
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        if (alreadySetup) return
        alreadySetup = true

        // user data
        createUserIfNotFound(1, "test@gmail.com", "test")
    }

    @Transactional
    fun createUserIfNotFound(id: Long, username: String, password: String): User {
        val user = User(id = id, username = username, password = password)
        var retrieval = userService.tryCreate(user)
        if (!retrieval.isPresent)
            retrieval = userService.findByUsername(username)
        return retrieval.get()
    }
}
