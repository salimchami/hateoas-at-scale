package com.hateoasatscale.products.domain

fun interface FindProduct {
    fun by(id: Long): Product
}
