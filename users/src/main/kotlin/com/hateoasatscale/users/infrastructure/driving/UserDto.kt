package com.hateoasatscale.users.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

class UserDto @JsonCreator constructor(
    private val frontendBaseUrl: String,
    private val id: Long,
    val username: String,
    val firstname: String,
    val lastname: String
) : RepresentationModel<UserDto>() {

    init {
        addSelfLink()
    }

    private fun addSelfLink() {
        val servicePath =
            (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?)?.request?.getHeader("X-Service-Path")
                ?: ""
        val path = linkTo(methodOn(UsersResource::class.java).userInfo(id)).toUri().path
        val fullUrl = "$frontendBaseUrl$servicePath$path"
        add(Link.of(fullUrl).withSelfRel())
    }
}
