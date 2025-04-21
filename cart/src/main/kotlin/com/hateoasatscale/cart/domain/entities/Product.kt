package com.hateoasatscale.cart.domain.entities

import com.hateoasatscale.cart.domain.share.network.Link
import java.math.BigDecimal

data class Product(val name: String, val price: BigDecimal, val quantity: Int, val links: List<Link>)