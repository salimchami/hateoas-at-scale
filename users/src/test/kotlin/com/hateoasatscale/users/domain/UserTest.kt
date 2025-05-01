package com.hateoasatscale.users.domain

import com.hateoasatscale.roles.Role
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class UserTest {

    @ParameterizedTest
    @MethodSource("userFromRoleParams")
    fun `should create a user with permissions depending on its role`(
        role: Role,
        expectedPermissions: List<Permission>
    ) {
        val user = User(role, "username", "firstname", "lastname")
        assertThat(user.permissions).isEqualTo(expectedPermissions)
    }

    companion object {
        @JvmStatic
        fun userFromRoleParams(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Role.ADMIN, Permission.entries),
                Arguments.of(Role.CUSTOMER, listOf(Permission.READ_OWN_USER, Permission.READ_CART, Permission.READ_PRODUCTS)),
                Arguments.of(Role.ANONYMOUS, listOf<Permission>()),
            )
        }
    }
}