package com.hateoasatscale.cart

import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder

class EndpointCaller(val mockmvc: MockMvc) {
    fun perform(request: MockHttpServletRequestBuilder): ResultActions {
        return this.mockmvc.perform(
            request
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE),
        )
    }
}
