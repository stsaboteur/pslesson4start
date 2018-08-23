package ru.yamoney.test

import java.lang.IllegalArgumentException
import java.math.BigDecimal

const val USAGE_MESSAGE = """
java -jar app.jar [COMMAND] [ARGUMENTS]
            Commands:
            payment %USER% %SUM% %SHOP_ID%
            deposit %USER% %SUM%
            balance %USER%
            shop_info %SHOP_ID%
"""

val billing: Billing = BillingImpl()

fun main(args: Array<String>) {
    try {
        when (args.command()) {
            "payment" -> billing.addOperation(Payment(BigDecimal(args[2]), args[1], args[3]))
            "deposit" -> billing.addOperation(Deposit(BigDecimal(args[2]), args[1]))
            "balance" -> billing.getUserBalance(args[1])
            "shop_info" -> billing.getShopIdOperations(args[1])
            else -> throw IllegalArgumentException("Unknown command")

        }
    } catch (e: Throwable) {
        e.printStackTrace()
        println(USAGE_MESSAGE)
    }
}

private fun Array<String>.command(): String = this[0].toLowerCase()

