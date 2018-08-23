package ru.yamoney.test

import java.math.BigDecimal

interface Operation {
    val sum: BigDecimal
    val user: String
    fun calculate(oldBalance: BigDecimal): BigDecimal
}

data class Deposit(
        override val sum: BigDecimal,
        override val user: String
) : Operation {
    override fun calculate(oldBalance: BigDecimal): BigDecimal {
        return oldBalance.subtract(sum)
    }
}

data class Payment(
        override val sum: BigDecimal,
        override val user: String,
        val shopId: String
) : Operation {
    override fun calculate(oldBalance: BigDecimal): BigDecimal {
        return oldBalance.add(sum)
    }
}