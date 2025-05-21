package com.hateoasatscale.users

import com.hateoasatscale.users.domain.Role

enum class UserMock(
    val sub: String,
    val givenName: String,
    val familyName: String,
    val roles: Array<String>
) {
    ADA("ada.lovelace", "Ada", "Lovelace", arrayOf(Role.ROLE_ADMIN.name)),
    ALAN("alan.turing", "Alan", "Turing", arrayOf(Role.ROLE_CUSTOMER.name)),
    CHARLES("charles.darwin", "Charles", "Darwin", arrayOf(Role.ROLE_CUSTOMER.name)),
    KAREN("karen.spence", "Karen", "Spence", arrayOf(Role.ROLE_CUSTOMER.name)),
    MARTIN("martin.curie", "Martin", "Curie", arrayOf(Role.ROLE_CUSTOMER.name)),
    RICHARD("richard.stallman", "Richard", "Stallman", arrayOf(Role.ROLE_CUSTOMER.name)),
    SAMUEL("samuel.jackson", "Samuel", "Jackson", arrayOf(Role.ROLE_ANONYMOUS.name)),
}
