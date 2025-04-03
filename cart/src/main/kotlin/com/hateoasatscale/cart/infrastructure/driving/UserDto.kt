package com.hateoasatscale.cart.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator

class UserDto @JsonCreator constructor(val firstname: String, val lastname: String) {
}