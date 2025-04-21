package com.hateoasatscale.cart.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.hateoas.RepresentationModel

class UserDto @JsonCreator constructor(val username: String) :
    RepresentationModel<UserDto>()