package com.hateoasatscale.cart.infrastructure.driven.providers

import org.springframework.cloud.openfeign.FeignClient

@FeignClient("users")
interface UsersProvider {
}