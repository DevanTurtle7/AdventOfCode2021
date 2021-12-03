package Day02;

import java.io.File
import java.io.InputStream

class Instruction(val command: String, val amount: Int)

class Position(var horiz: Int, var depth: Int) {
    infix fun move(instruction: Instruction) {
        when(instruction.command) {
            "down" -> depth += instruction.amount
            "up" -> depth -= instruction.amount
            "forward" -> horiz += instruction.amount
        }
    }
}

fun parseData(filename: String): Position {
    val inputStream: InputStream = File(filename).inputStream()
    val position = Position(0, 0)

    inputStream.bufferedReader().forEachLine {
        val trimmed = it.trim()
        val tokens: List<String> = trimmed.split(" ")
        val command = tokens[0]
        val amount = tokens[1].toInt()

        val instruction = Instruction(command, amount)
        position move instruction
    }

    return position
}

fun main() {
    val finalPosition = parseData("./input.txt")
    val horiz = finalPosition.horiz
    val depth = finalPosition.depth

    println("Horiz: $horiz, Depth: $depth, Mult: ${horiz * depth}")
}