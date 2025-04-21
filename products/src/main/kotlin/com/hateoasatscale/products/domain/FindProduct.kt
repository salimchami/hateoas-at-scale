package com.hateoasatscale.products.domain

fun interface FindProduct {
    fun by(name: String): Product
}
