package com.hateoasatscale.users.infrastructure.driving

import com.hateoasatscale.users.domain.FindUser
import org.springframework.hateoas.EntityModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.net.InetAddress

@RestController
class UsersResource(
    private val findUser: FindUser,
) {

    @GetMapping("/users/{id}")
    fun userInfo(@PathVariable id: Long): EntityModel<UserDto> {
        val localHostAddress = InetAddress.getLocalHost().hostAddress
        val localHostName = InetAddress.getLocalHost().hostName

        // Remote address
        val remoteHostAddress = InetAddress.getLoopbackAddress().hostAddress
        val remoteHostName = InetAddress.getLoopbackAddress().hostName
        println("###################################")
        println("users-service: user id $id requested")
        println("local: $localHostAddress ($localHostName)")
        println("remote: $remoteHostAddress ($remoteHostName)")

        val user = findUser.by(id)
        val content = UserDto(id, user.username, user.firstname, user.lastname)
        return EntityModel.of(content)
    }
}
