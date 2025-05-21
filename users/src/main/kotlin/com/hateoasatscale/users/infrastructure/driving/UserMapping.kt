package com.hateoasatscale.users.infrastructure.driving

import com.hateoasatscale.users.domain.User
import org.springframework.hateoas.Link
import org.springframework.stereotype.Component

@Component
class UserMapping() {
    fun domainToDto(user: User, productsLinks: List<Link>, cartsLinks: List<Link>): UserDto {
        return UserDto(
            productsLinks,
            cartsLinks,
            user.permissions,
            user.username,
            user.firstname,
            user.lastname,
        )
    }

    fun domainToInfoDto(users: List<User>): List<UserInfoDto> {
        return users.map { UserInfoDto(it.username, it.firstname, it.lastname) }
    }
}
