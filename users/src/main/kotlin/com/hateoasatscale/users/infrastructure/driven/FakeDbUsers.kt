package com.hateoasatscale.users.infrastructure.driven

import com.hateoasatscale.roles.Role

class FakeDbUsers {
    companion object {
        val users = listOf(
            DbUser("ada.lovelace", Role.ADMIN, "Ada", "Lovelace"),
            DbUser("alan.turing", Role.CUSTOMER, "Alan", "Turing"),
            DbUser("charles.darwin", Role.CUSTOMER, "Charles", "Darwin"),
            DbUser("karen.spence", Role.CUSTOMER, "Karen", "Spence"),
            DbUser("martin.curie", Role.CUSTOMER, "Martin", "Curie"),
            DbUser("richard.stallman", Role.CUSTOMER, "Richard", "Stallman"),
            DbUser("samuel.jackson", Role.ANONYMOUS, "Samuel", "Jackson"),
        )
    }
}