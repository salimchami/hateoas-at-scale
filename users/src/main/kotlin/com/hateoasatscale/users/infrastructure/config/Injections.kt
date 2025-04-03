package com.hateoasatscale.users.infrastructure.config

import com.hateoasatscale.users.domain.UserSearch
import com.hateoasatscale.users.domain.UsersRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Injections {

    @Bean
    fun FindUser(usersRepository: UsersRepository) = UserSearch(usersRepository)
}