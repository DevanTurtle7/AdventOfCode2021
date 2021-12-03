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

    fun finalPosition() = horiz * depth
}

class Sub(var horiz: Int, var depth: Int, var aim: Int) {
    infix fun move(instruction: Instruction) {
        when(instruction.command) {
            "down" -> aim += instruction.amount
            "up" -> aim -= instruction.amount
            "forward" -> {
                horiz += instruction.amount
                depth += aim * instruction.amount
            }
        }
    }

    fun finalPosition() = horiz * depth
}

fun parseDataPart1(filename: String): Position {
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

fun parseDataPart2(filename: String): Sub {
    val inputStream: InputStream = File(filename).inputStream()
    val sub = Sub(0, 0, 0)

    inputStream.bufferedReader().forEachLine {
        val trimmed = it.trim()
        val tokens: List<String> = trimmed.split(" ")
        val command = tokens[0]
        val amount = tokens[1].toInt()

        val instruction = Instruction(command, amount)
        sub move instruction
    }

    return sub
}

fun main() {
    val finalPosition = parseDataPart1("./input.txt")
    val horiz = finalPosition.horiz
    val depth = finalPosition.depth
    val result = finalPosition.finalPosition()

    println("Horiz: $horiz, Depth: $depth, Mult: ${result}")
}