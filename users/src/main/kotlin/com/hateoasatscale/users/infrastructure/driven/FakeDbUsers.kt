package com.hateoasatscale.users.infrastructure.driven

class FakeDbUsers {
    companion object {
        val users = listOf(
            DbUser(1, "ada.lovelace", "Ada", "Lovelace"),
            DbUser(2, "alan.turing", "Alan", "Turing"),
            DbUser(3, "charles.darwin", "Charles", "Darwin"),
            DbUser(4, "karen.spence", "Karen", "Spence"),
            DbUser(5, "martin.curie", "Martin", "Curie"),
            DbUser(6, "richard.stallman", "Richard", "Stallman"),
            DbUser(7, "samuel.jackson", "Samuel", "Jackson"),
        )
    }
}