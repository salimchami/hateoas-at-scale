package com.hateoasatscale.users.infrastructure.driven

import com.hateoasatscale.users.domain.Role

class FakeDbUsers {
    companion object {
        val users = listOf(
            DbUser("ada.lovelace", Role.ROLE_ADMIN, "Ada", "Lovelace"),
            DbUser("alan.turing", Role.ROLE_CUSTOMER, "Alan", "Turing"),
            DbUser("charles.darwin", Role.ROLE_CUSTOMER, "Charles", "Darwin"),
            DbUser("karen.spence", Role.ROLE_CUSTOMER, "Karen", "Spence"),
            DbUser("martin.curie", Role.ROLE_CUSTOMER, "Martin", "Curie"),
            DbUser("richard.stallman", Role.ROLE_CUSTOMER, "Richard", "Stallman"),
            DbUser("samuel.jackson", Role.ROLE_ANONYMOUS, "Samuel", "Jackson"),
        )
    }
}
