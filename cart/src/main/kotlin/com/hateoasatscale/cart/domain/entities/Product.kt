package com.hateoasatscale.cart.domain.entities

import com.hateoasatscale.cart.domain.share.network.Link
import java.math.BigDecimal

class Product(val name: String, val reference: String, val price: BigDecimal, val links: List<Link>)