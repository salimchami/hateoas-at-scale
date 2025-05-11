package com.hateoasatscale.users.infrastructure.driven

import com.hateoasatscale.users.domain.Role

class DbUser(val username: String, val role: Role, val firstname: String, val lastname: String)
