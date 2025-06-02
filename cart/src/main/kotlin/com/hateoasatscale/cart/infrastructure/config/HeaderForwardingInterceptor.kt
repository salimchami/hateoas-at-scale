package com.hateoasatscale.cart.infrastructure.config

import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

class HeaderForwardingInterceptor : ClientHttpRequestInterceptor {
    override fun intercept(
        request: HttpRequest,
        body: ByteArray,
        execution: ClientHttpRequestExecution
    ): ClientHttpResponse {
        try {
            val requestAttributes = RequestContextHolder.getRequestAttributes() as? ServletRequestAttributes
            val httpRequest = requestAttributes?.request

            httpRequest?.let { req ->
                val headersToForward = listOf(
                    "Authorization",
                    "X-Forwarded-For",
                    "X-Forwarded-Proto",
                    "X-Forwarded-Host",
                    "X-Real-IP",
                    "User-Agent",
                )

                headersToForward.forEach { headerName ->
                    req.getHeader(headerName)?.let { headerValue ->
                        request.headers[headerName] = headerValue
                    }
                }
            }
        } catch (e: Exception) {
            // Handle case when not in a request context
        }

        return execution.execute(request, body)
    }
}
