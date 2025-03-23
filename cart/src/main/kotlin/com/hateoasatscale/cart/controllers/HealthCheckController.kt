package com.hateoasatscale.cart.controllers

import org.springframework.http.HttpEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class HealthCheckController {

    @GetMapping
    fun userInfo(): HttpEntity<String> {
        return HttpEntity<String>("Cart app is started")
    }
}
