package com.hateoasatscale.users.controllers.users

import org.springframework.hateoas.RepresentationModel

class User(val username: String, val firstname: String, val lastname: String) : RepresentationModel<User>()