package com.hateoasatscale.users.infrastructure.driven

import com.hateoasatscale.users.domain.User
import com.hateoasatscale.users.domain.UserNotFound
import com.hateoasatscale.users.domain.UsersRepository
import org.springframework.stereotype.Component

@Component
class UsersAdapter : UsersRepository {
    @Throws(UserNotFound::class)
    override fun findBy(username: String): User {
        return FakeDbUsers.users.find { it.username == username }
            ?.let { User(it.username, it.firstname, it.lastname) }
            ?: throw UserNotFound("User with username $username not found")
    }
}