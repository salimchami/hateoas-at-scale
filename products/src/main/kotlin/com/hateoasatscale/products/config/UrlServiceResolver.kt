package com.hateoasatscale.products.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.stereotype.Component

@Component
class UrlServiceResolver(@Autowired private val discovery: DiscoveryClient) {
    fun getServiceUrl(serviceName: String): String {
        val instance = discovery.getInstances(serviceName)[0]
        return "${instance.host}:${instance.port}"
    }
}