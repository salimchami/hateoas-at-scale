package com.hateoasatscale.users.infrastructure.driven

import com.hateoasatscale.users.domain.User
import com.hateoasatscale.users.domain.UserNotFound
import com.hateoasatscale.users.domain.spi.UsersRepository
import org.springframework.stereotype.Component

@Component
class UsersAdapter : UsersRepository {
    @Throws(UserNotFound::class)
    override fun findBy(username: String): User {
        return FakeDbUsers.users.find { it.username == username }
            ?.let { User(it.role, it.username, it.firstname, it.lastname) }
            ?: throw UserNotFound("User with username $username not found")
    }

    override fun findAll(): List<User> {
        return FakeDbUsers.users.map { User(it.role, it.username, it.firstname, it.lastname) }
    }
}