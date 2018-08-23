package ru.yamoney.test

import org.junit.Test


class AppTest {

    @Test
    fun payment() {
        main(arrayOf("payment", "Вася", "25", "4455"))
    }

    @Test
    fun deposit() {
        main(arrayOf("deposit", "Вася", "123"))
    }

    @Test
    fun balance() {
        main(arrayOf("balance", "Вася"))
    }

    @Test
    fun shopInfo() {
        main(arrayOf("shop_info", "4455"))
    }
}
