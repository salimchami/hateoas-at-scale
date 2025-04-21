package com.hateoasatscale.cart.domain.entities

import com.hateoasatscale.cart.domain.share.network.Link
import java.math.BigDecimal

data class UserCartProduct(val name: String, val totalPrice: BigDecimal, val quantity: Int, val links: List<Link>) {
}