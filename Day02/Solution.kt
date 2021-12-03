package Day02;

import java.io.File
import java.io.InputStream

class Instruction(val command: String, val amount: Int)

open class Sub(var horiz: Int, var depth: Int) {
    open infix fun move(instruction: Instruction) {
        when(instruction.command) {
            "down" -> depth += instruction.amount
            "up" -> depth -= instruction.amount
            "forward" -> horiz += instruction.amount
        }
    }

    fun finalPosition() = horiz * depth
}

class AimingSub(horiz: Int, depth: Int, var aim: Int): Sub(horiz = horiz, depth = depth) {
    override infix fun move(instruction: Instruction) {
        when(instruction.command) {
            "down" -> aim += instruction.amount
            "up" -> aim -= instruction.amount
            "forward" -> {
                horiz += instruction.amount
                depth += aim * instruction.amount
            }
        }
    }
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

fun moveSub(instructions: List<Instruction>, sub: Sub): Int {
    instructions.forEach{
        val instruction = it
        sub move instruction
    }

    return sub.finalPosition()
}

fun main() {
    val instructions = parseData("./input.txt")
    val sub = Sub(0, 0)
    val result1 = moveSub(instructions, sub)
    println("Solution 1: $result1")

    val aimingSub = AimingSub(0, 0, 0)
    val result2 = moveSub(instructions, aimingSub)
    println("Solution 2: $result2")
}