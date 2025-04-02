package com.hateoasatscale.users

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@AutoConfiguration
//@EnableFeignClients
class UsersApplication


fun main(args: Array<String>) {
    runApplication<UsersApplication>(*args)
}
