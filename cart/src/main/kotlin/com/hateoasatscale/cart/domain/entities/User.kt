package com.hateoasatscale.cart.domain.entities

import com.hateoasatscale.cart.domain.share.network.Link

class User(val firstname: String, val lastname: String, val links: List<Link>)