import java.io.File
import java.io.InputStream
import kotlin.math.abs

fun main() {
    part1("p1data.csv")
    part2("p1data.csv")
}

fun readFile(input:String): MutableList<String> {
    val inputStream: InputStream = File(input).inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine {
        lineList.add(it)
    }

    return lineList

}

fun parse(input:String): MutableList<Int> {
    val listLength: Int
    var listToInt = mutableListOf<Int>()
    var resultList = mutableListOf<Int>()
    var values = input.split(" ").toList()
    values.forEach {
        listToInt.add(it.toInt())
    }
    listLength = listToInt.size
    for (i in 0..listLength-2) {
        resultList.add(listToInt[i] - listToInt[i+1])
    }

    return resultList
}

fun isSafe(input:MutableList<Int>): Boolean {
    if (input.all { it > 0 } && input.all { abs(it) < 4 }) {
        return true
    } else if (input.all { it < 0 } && input.all { abs(it) < 4 }) {
        return true
    } else {
        return false
    }
}

fun part1(input:String) {
    val lineList = readFile(input)
    var resultList = mutableListOf<Int>()

    var count = 0

    lineList.forEach {
        resultList = parse(it)
        if (isSafe(resultList)) {
            count++
        }
    }
    println(count)
}

fun part2(input:String) {
    val lineList = readFile(input)

    var count = 0
    var resultList = mutableListOf<Int>()

    for (it in lineList) {
        resultList = parse(it)
        if (isSafe(resultList)) {
            count++
        }

    }
    println(count)
}
