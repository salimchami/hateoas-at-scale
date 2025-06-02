package com.hateoasatscale.cart.infrastructure.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.web.filter.ForwardedHeaderFilter

 @Configuration
class HateoasConfiguration {

    @Bean
    fun forwardedHeaderFilter(): FilterRegistrationBean<ForwardedHeaderFilter> {
        val customFilter = ForwardedHeaderFilter()
        val bean = FilterRegistrationBean(customFilter)
        bean.filter = customFilter
        bean.order = Ordered.HIGHEST_PRECEDENCE
        return bean
    }
}
