package com.hateoasatscale.products.domain

import java.math.BigDecimal

class Product(
    val id: Long,
    val name: String,
    val reference: String,
    val price: BigDecimal
)
