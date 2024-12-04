/*
  Look for all mul(Int,Int) where Int is a 1-3 digit Int
*/

import java.io.File
import java.io.InputStream
import kotlin.math.abs

fun main() {
    part1("input.txt")
    part2("input.txt")
}

fun readFile(input:String): String {
    val inputStream: InputStream = File(input).inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText() }
    return inputString

}

fun part1(input: String) {
    val lineList = readFile(input)

    val regex = Regex(pattern = "mul\\([0-9]+,[0-9]+\\)", options = setOf(RegexOption.IGNORE_CASE))

    val matches = regex.findAll(lineList)

    var total = 0

    for (match in matches) {
        val numbers = Regex("[0-9]+").findAll(match.value)
            .map(MatchResult::value)
            .toList()
        
        total += numbers[0].toInt() * numbers[1].toInt()
    }
    println(total)

}

fun part2(input: String) {
    val lineList = readFile(input)

    val regex = Regex(pattern = "mul\\([0-9]+,[0-9]+\\)|do\\(\\)|don't\\(\\)", options = setOf(RegexOption.IGNORE_CASE))
    val matches = regex.findAll(lineList)

    var enabled: Boolean = true
    var result = 0

    for (match in matches) {
        if (match.value == "do()"){
            enabled = true
        } else if (match.value == "don't()") {
            enabled = false
        } else {
            if (enabled) {
                val numbers = Regex("[0-9]+").findAll(match.value)
                    .map(MatchResult::value)
                    .toList()
        
                result += numbers[0].toInt() * numbers[1].toInt()
            }
        }
    }
    println(result)

}