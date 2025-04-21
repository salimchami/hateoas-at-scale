package com.hateoasatscale.cart.domain

import com.hateoasatscale.cart.domain.entities.*
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
                    CartProduct(apple, 1), CartProduct(pineapple, 2)
                )
            )
        )
        `when`(usersRepository.findBy(anyString())).thenReturn(User(adalovelace))
        `when`(productsRepository.findBy(listOf(apple, pineapple))).thenReturn(
            listOf(
                Product(apple, BigDecimal.TEN, listOf()),
                Product(pineapple, BigDecimal.TWO, listOf()),
            )
        )
        val cart = sut.by("ada.lovelace")
        assertThat(cart).isEqualTo(
            UserCart(
                adalovelace,
                listOf(UserCartProduct(apple, BigDecimal.TEN,1,  listOf()),
                    UserCartProduct(pineapple, BigDecimal.valueOf(4), 2, listOf()))
            )
        )
    }
}