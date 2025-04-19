package com.hateoasatscale.users.infrastructure.driven

class FakeDbUsers {
    companion object {
        val users = listOf(
            DbUser("ada.lovelace", "Ada", "Lovelace"),
            DbUser("alan.turing", "Alan", "Turing"),
            DbUser("charles.darwin", "Charles", "Darwin"),
            DbUser("karen.spence", "Karen", "Spence"),
            DbUser("martin.curie", "Martin", "Curie"),
            DbUser("richard.stallman", "Richard", "Stallman"),
            DbUser("samuel.jackson", "Samuel", "Jackson"),
        )
    }
}