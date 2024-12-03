/*
  Look for all mul(Int,Int) where Int is a 3 digit Int
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
    /*
        Do calculations until you hit a don't(), when you hit a don't() dont do
        calculations until you hit a do() and repeat the cycle until the end
    */
    val lineList = readFile(input)

    /*
        Find operational ranges 
    */

    val regex = Regex(pattern = "mul\\([0-9]+,[0-9]+\\)", options = setOf(RegexOption.IGNORE_CASE))
    val doRegex = Regex(pattern = "do\\(\\)", options = setOf(RegexOption.IGNORE_CASE))
    val dontRegex = Regex(pattern = "don't\\(\\)", options = setOf(RegexOption.IGNORE_CASE))

    var enabled: Boolean = true
    var dontMatchesRanges = mutableListOf<Int>()
    var doMatchesRanges = mutableListOf<Int>()
    var skippableRanges = mutableListOf<IntRange>()
    var j = 0

    val dontMatches = dontRegex.findAll(lineList)
    val doMatches = doRegex.findAll(lineList)

    for (match in dontMatches) {
        dontMatchesRanges.add(match.range.first)
    }

    for (match in doMatches) {
        doMatchesRanges.add(match.range.first)
    }

    println(dontMatchesRanges)
    println(doMatchesRanges)
    for (i in 0..dontMatchesRanges.size-1) {
        if (dontMatchesRanges[i] < doMatchesRanges[j]) {
            skippableRanges.add(dontMatchesRanges[i].toInt()..doMatchesRanges[j].toInt())
            j++
        } else {
            continue
        }
    }

    println(skippableRanges)

}