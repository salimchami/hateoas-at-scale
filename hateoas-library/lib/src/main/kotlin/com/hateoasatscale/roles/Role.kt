package com.hateoasatscale.roles

/**
 * Represents the various roles that a user can have within the system.
 *
 * The roles determine the level of access and permissions granted to the user.
 * - `ANONYMOUS`: A user who has not logged in or does not have any specific role.
 * - `ADMIN`: A user with administrative privileges, capable of managing system settings and other users.
 * - `CUSTOMER`: A regular user with permissions limited to customer-related features.
 */
enum class Role {
    ANONYMOUS,
    ADMIN,
    CUSTOMER
}