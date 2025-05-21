package com.hateoasatscale.users.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator

class UserInfoDto @JsonCreator constructor(
    val username: String,
    val firstname: String,
    val lastname: String
)
