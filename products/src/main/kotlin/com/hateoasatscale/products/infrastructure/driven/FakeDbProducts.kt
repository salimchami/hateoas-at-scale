package com.hateoasatscale.products.infrastructure.driven

class FakeDbProducts {
    companion object {
        val products = listOf(
            DbProduct(1, "apple", "RRDGHGT554346", "1.00".toBigDecimal()),
            DbProduct(2, "banana", "FFFGJU9976", "1.54".toBigDecimal()),
            DbProduct(3, "kiwi", "DDGHKBDQ221467", "100.00".toBigDecimal()),
            DbProduct(4, "orange", "DFHDHYUJJBN887644", "158.00".toBigDecimal()),
            DbProduct(5, "pineapple", "FFDFGHNN99875", "99.00".toBigDecimal()),
            DbProduct(6, "strawberry", "DDSDFHGNN8644", "56.99".toBigDecimal()),
            DbProduct(7, "watermelon", "SSFSFGHHJ8846524", "1000.00".toBigDecimal())
        )
    }
}