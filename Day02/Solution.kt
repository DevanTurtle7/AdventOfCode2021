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

fun parseData(filename: String): List<Instruction> {
    val inputStream: InputStream = File(filename).inputStream()
    val instructions = mutableListOf<Instruction>()

    inputStream.bufferedReader().forEachLine {
        val trimmed = it.trim()
        val tokens: List<String> = trimmed.split(" ")
        val command = tokens[0]
        val amount = tokens[1].toInt()

        val instruction = Instruction(command, amount)
        instructions.add(instruction)
    } 

    return instructions
}

fun solution1(instructions: List<Instruction>): Int {
    val position = Position(0, 0)

    instructions.forEach{
        val instruction = it
        position move instruction
    }

    return position.finalPosition()
}

fun solution2(instructions: List<Instruction>): Int {
    val sub = Sub(0, 0, 0)

    instructions.forEach{
        val instruction = it
        sub move instruction
    }

    return sub.finalPosition()
}

fun main() {
    val instructions = parseData("./input.txt")
    val result1 = solution1(instructions)
    println("Solution 1: $result1")

    val result2 = solution2(instructions)
    println("Solution 2: $result2")
}