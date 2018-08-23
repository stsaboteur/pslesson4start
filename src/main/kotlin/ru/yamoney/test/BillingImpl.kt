package ru.yamoney.test

import com.google.gson.Gson
import java.io.File
import java.math.BigDecimal
import java.math.BigDecimal.ZERO

class BillingImpl : Billing {
    private val file = File("operations")
    private val gson = Gson()

    override fun getShopIdOperations(shopId: String) {
        println("Payments to shop $shopId:")
        file.readLines()
                .map { it.toOperation() }
                .filter { it is Payment && it.shopId == shopId }
                .forEach {
                    println("User ${it.user} paid ${it.sum}")
                }
    }

    override fun getUserBalance(user: String) {
        var userBalance: BigDecimal = ZERO
        file.readLines()
                .map { it.toOperation() }
                .filter { it.user == user }
                .forEach {
                    userBalance = it.calculate(userBalance)
                }
        println(userBalance)
    }

    override fun addOperation(operation: Operation) {
        file.appendText("${operation::class.java.name}||${gson.toJson(operation)}\n")
    }

    private fun String.toOperation(): Operation {
        val type = this.substringBefore("||")
        val json = this.substringAfter("||")
        return gson.fromJson(json, Class.forName(type)) as Operation
    }
}