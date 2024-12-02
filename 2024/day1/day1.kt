import java.io.File
import java.io.InputStream
import kotlin.math.abs

fun main() {
    part1("p1data.csv")
    part2("p2data.csv")
}

fun readFile(input:String): MutableList<String> {
    val inputStream: InputStream = File(input).inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine {
        lineList.add(it)
    }

    return lineList

}

fun splitLines(input:String): List<String> {
    var values = input.split("   ").toList()
    return values
}

fun findRepeatedValues(value: Int, list: List<Int>): Int {
    var count = 0
    for (i in 0 until list.size) {
        if (list[i] == value) {
            count ++
        }
    }

    return count
}

fun part1(input: String) {
    val lineList = readFile(input)

    var count = 0
    var countOfPairs: Int = 0
    var values = listOf<String>()
    var leftList = mutableListOf<Int>()
    var rightList = mutableListOf<Int>()
    var rowDistance: Int
    var total: Int = 0

    lineList.forEach {
        values = splitLines(it)
        leftList.add(values[0].toInt())
        rightList.add(values[1].toInt())
        count++
    }

    println("Left List: $leftList")
    println("Right List: $rightList")
    println("Count: $count")

    val sortedLeftList = leftList.sorted()
    val sortedRightList = rightList.sorted()

    println("Sorted Left List: $sortedLeftList")
    println("Sorted Right List: $sortedRightList")

    if (sortedLeftList.size == sortedRightList.size) {
        countOfPairs = sortedLeftList.size
    } else {
        println("We got a problem with the number of pairs")
        println("Sorted Left List Size: ${sortedLeftList.size}")
        println("Sorted Right List Size: ${sortedRightList.size}")
    }

    for (i in 0..countOfPairs-1) {
        rowDistance = sortedLeftList[i] - sortedRightList[i]
        println("$i Row Distance: ${sortedLeftList[i]} - ${sortedRightList[i]}: $rowDistance\n")
        total += abs(rowDistance)
    }

    println(total)
}

fun part2 (input:String) {
    val lineList = readFile(input)

    var count = 0
    var values = listOf<String>()
    var leftList = mutableListOf<Int>()
    var rightList = mutableListOf<Int>()
    var similarityScore = 0

    lineList.forEach {
        values = splitLines(it)
        leftList.add(values[0].toInt())
        rightList.add(values[1].toInt())
    }

    leftList.forEach {
        count = findRepeatedValues(it, rightList)
        similarityScore += it * count
    }
    println(similarityScore)
}