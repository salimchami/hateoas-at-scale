package com.hateoasatscale.cart.domain

import com.hateoasatscale.cart.domain.entities.Cart
import com.hateoasatscale.cart.domain.entities.Product
import com.hateoasatscale.cart.domain.entities.User
import com.hateoasatscale.cart.domain.entities.UserCart
import com.hateoasatscale.cart.domain.spi.CartsRepository
import com.hateoasatscale.cart.domain.spi.ProductsRepository
import com.hateoasatscale.cart.domain.spi.UsersRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.math.BigDecimal

class CartSearchTest {
    val adalovelace = "ada.lovelace"
    val apple = "apple"
    val pineapple = "pineapple"
    lateinit var usersRepository: UsersRepository
    lateinit var productsRepository: ProductsRepository
    lateinit var cartsRepository: CartsRepository
    lateinit var sut: CartSearch

    @BeforeEach
    fun setUp() {
        usersRepository = mock()
        productsRepository = mock()
        cartsRepository = mock()
        sut = CartSearch(cartsRepository, productsRepository, usersRepository)
    }

    @Test
    fun `should find cart for a user`() {
        `when`(cartsRepository.findBy(anyString())).thenReturn(
            Cart(
                adalovelace, listOf(
                    apple, pineapple, pineapple
                )
            )
        )
        `when`(usersRepository.findBy(anyString())).thenReturn(User(adalovelace, emptyList()))
        `when`(productsRepository.findBy(listOf(apple, pineapple))).thenReturn(
            listOf(
                Product(apple, BigDecimal.TEN, 0, listOf()),
                Product(pineapple, BigDecimal.TWO, 0, listOf()),
            )
        )
        val cart = sut.findBy("ada.lovelace")
        assertThat(cart).isEqualTo(
            UserCart(
                adalovelace,
                listOf(Product(apple, BigDecimal.TEN, 1, listOf()), Product(pineapple, BigDecimal.TWO, 0,listOf()))
            )
        )
    }
}