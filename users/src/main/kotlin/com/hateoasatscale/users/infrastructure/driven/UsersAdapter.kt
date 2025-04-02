package com.hateoasatscale.users.infrastructure.driven

import com.hateoasatscale.users.domain.User
import com.hateoasatscale.users.domain.UserNotFound
import org.springframework.stereotype.Component

@Component
class UsersAdapter : UsersRepository {
    @Throws(UserNotFound::class)
    override fun findBy(id: Long): User {
        return FakeDbUsers.users.find { it.id == id }
            ?.let { User(it.username, it.firstname, it.lastname) }
            ?: throw UserNotFound("User with id $id not found")
    }
}