package cinema

import kotlin.math.ceil
import kotlin.math.floor

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seatsPerRow = readln().toInt()
    
    val seatsTotal = rows * seatsPerRow

    val cinema = MutableList(rows) { MutableList(seatsPerRow) { 'S' } }

    var countTicketsBought = 0
    var countIncome = 0

    fun printSeats() {
        print("\nCinema:\n  ")
        for (i in 1..seatsPerRow) print("$i ")
        for (i in 1..rows) print("\n$i ${ cinema[i - 1].joinToString(" ") }")
        println()
    }

    fun buyTicket() {
        println("\nEnter a row number:")
        val row = readln().toInt()
        println("Enter a seat number in that row:")
        val seat = readln().toInt()

        if (row > rows || seat > seatsPerRow) {
            println("Wrong input!")
            buyTicket()
            return
        }
        if (cinema[row - 1][seat - 1] == 'B') {
            println("That ticket has already been purchased!")
            buyTicket()
            return
        }

        val price = if (seatsTotal <= 60 || row <= floor(rows.toDouble() / 2)) 10 else 8
        println("\nTicket price: $$price")

        cinema[row - 1][seat - 1] = 'B'
        countTicketsBought++
        countIncome += price
    }

    fun printStatistics() {
        println("""
            
            Number of purchased tickets: $countTicketsBought
            Percentage: ${"%.2f".format((countTicketsBought.toDouble() / seatsTotal) * 100)}%
            Current income: $$countIncome
            Total income: $${(if (seatsTotal <= 60) seatsTotal * 10 else floor((seatsTotal / 2).toDouble()) * 10 + ceil((seatsTotal / 2).toDouble()) * 8).toInt()}
        """.trimIndent())
    }

    while (true) {
        println("""
            
            1. Show the seats
            2. Buy a ticket
            3. Statistics
            0. Exit
        """.trimIndent())

        when (readln()) {
            "1" -> printSeats()
            "2" -> buyTicket()
            "3" -> printStatistics()
            "0" -> break
        }
    }
}