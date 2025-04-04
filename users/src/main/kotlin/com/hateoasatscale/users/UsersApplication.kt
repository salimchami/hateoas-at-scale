package com.hateoasatscale.users

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@AutoConfiguration
@EnableFeignClients
class UsersApplication

fun main(args: Array<String>) {
    runApplication<UsersApplication>(*args)
}
