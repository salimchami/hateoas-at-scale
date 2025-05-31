package com.hateoasatscale.products.infrastructure.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import jakarta.servlet.http.HttpServletResponse
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.web.filter.ForwardedHeaderFilter
import org.springframework.web.filter.OncePerRequestFilter

class CustomForwardedHeaderFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val wrappedRequest = object : HttpServletRequestWrapper(request) {
            override fun getHeader(name: String): String? {
                val originalValue = super.getHeader(name)
                if (name == "X-Forwarded-Host" || name == "X-Forwarded-Port") {
                    println("####################################### name: $name, originalValue: $originalValue")
                }
                return if (name.equals("X-Forwarded-Host", ignoreCase = true) && originalValue != null) {
                    // Prendre seulement la première IP/host
                    originalValue.split(",").firstOrNull()?.trim()
                } else {
                    originalValue
                }
            }

            override fun getHeaders(name: String): java.util.Enumeration<String> {
                return if (name.equals("X-Forwarded-Host", ignoreCase = true)) {
                    val originalValue = super.getHeader(name)
                    val firstValue = originalValue?.split(",")?.firstOrNull()?.trim()
                    if (firstValue != null) {
                        java.util.Collections.enumeration(listOf(firstValue))
                    } else {
                        java.util.Collections.emptyEnumeration()
                    }
                } else {
                    super.getHeaders(name)
                }
            }
        }

        // Appliquer le ForwardedHeaderFilter standard après notre modification
        val forwardedHeaderFilter = ForwardedHeaderFilter()
        forwardedHeaderFilter.doFilter(wrappedRequest, response, filterChain)
    }
}

@Configuration
class HateoasConfiguration {

    @Bean
    fun forwardedHeaderFilter(): FilterRegistrationBean<CustomForwardedHeaderFilter> {
        val customFilter = CustomForwardedHeaderFilter()
        val bean = FilterRegistrationBean(customFilter)
        bean.order = Ordered.HIGHEST_PRECEDENCE
        return bean
    }
}
