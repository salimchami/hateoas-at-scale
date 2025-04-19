package com.hateoasatscale.products.domain

fun interface FindProducts {
    fun all(): List<Product>
}
