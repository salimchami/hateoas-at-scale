import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor

@Configuration
class WebConfiguration {

    companion object {
        val FORWARDED_HEADERS = listOf(
            "X-Forwarded-Host",
            "X-Forwarded-Port",
            "X-Forwarded-Proto",
            "X-Forwarded-For",
            "X-Forwarded-Prefix",
        )
        const val AUTHORIZATION_HEADER = "Authorization"
    }

    @Bean
    fun webRequestInterceptor(): WebRequestInterceptor {
        return HeaderForwardingInterceptor()
    }

    private fun forwardHeaders(webRequest: WebRequest) {
        val requestAttributes = RequestContextHolder.getRequestAttributes()
        if (requestAttributes is ServletRequestAttributes) {
            val request = requestAttributes.request

            FORWARDED_HEADERS.forEach { headerName ->
                val headerValue = request.getHeader(headerName)
                if (!headerValue.isNullOrBlank()) {
                    webRequest.setAttribute(headerName, headerValue, WebRequest.SCOPE_REQUEST)
                }
            }

            val authorizationHeader = request.getHeader(AUTHORIZATION_HEADER)
            if (!authorizationHeader.isNullOrBlank()) {
                webRequest.setAttribute(AUTHORIZATION_HEADER, authorizationHeader, WebRequest.SCOPE_REQUEST)
            }
        }
        println("#######################################################")
        println("carts web client conf : ")
        println(webRequest.headerNames.forEach { headerName -> println("$headerName: ${webRequest.getHeader(headerName)}") })
    }

    private inner class HeaderForwardingInterceptor : WebRequestInterceptor {
        override fun preHandle(request: WebRequest) {
            forwardHeaders(request)
        }

        override fun postHandle(
            request: WebRequest,
            model: ModelMap?
        ) {
        }

        override fun afterCompletion(
            request: WebRequest,
            ex: Exception?
        ) {
        }
    }
}
