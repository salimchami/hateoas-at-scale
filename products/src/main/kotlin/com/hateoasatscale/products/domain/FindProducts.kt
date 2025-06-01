package com.hateoasatscale.products.domain

interface FindProducts {
    fun all(): List<Product>
    fun some(names: List<String>): List<Product>
}
