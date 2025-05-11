package com.hateoasatscale.cart.infrastructure.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.ForwardedHeaderFilter


@Configuration
class HateoasConfiguration {
    @Bean
    fun forwardedHeaderFilter(): FilterRegistrationBean<ForwardedHeaderFilter?> {
        val bean = FilterRegistrationBean<ForwardedHeaderFilter?>()
        bean.filter = ForwardedHeaderFilter()
        return bean
    }
}
