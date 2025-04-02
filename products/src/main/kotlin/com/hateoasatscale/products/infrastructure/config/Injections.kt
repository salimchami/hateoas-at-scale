package com.hateoasatscale.products.infrastructure.config

import com.hateoasatscale.products.domain.ProductSearch
import com.hateoasatscale.products.infrastructure.driven.ProductsRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Injections {

    @Bean
    fun FindProduct(usersRepository: ProductsRepository) = ProductSearch(usersRepository)
}