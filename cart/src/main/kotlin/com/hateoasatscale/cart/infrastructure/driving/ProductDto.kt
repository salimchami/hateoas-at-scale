package com.hateoasatscale.cart.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import java.math.BigDecimal

class ProductDto @JsonCreator constructor(val name: String, val reference: String, val price: BigDecimal)