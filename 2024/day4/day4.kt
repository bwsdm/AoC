import java.io.File
import java.io.InputStream
import kotlin.math.abs

fun main() {
    part1("example.csv")
    //part2("p1data.csv")
}

fun readFile(input:String): MutableList<String> {
    val inputStream: InputStream = File(input).inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine {
        lineList.add(it)
    }

    return lineList

}

fun lookAround(fileOutput:MutableList<String>, lineIndex:Int, letterIndex:Int, lineCount:Int, letterCount:Int, letter:String): MutableList<Any> {
    var checkPotential = mutableListOf<Any>()
    if (lineIndex == 0 && letterIndex == 0) {
        // dont look up or left
        //println("Dont look up or left")
        checkPotential.addAll(listOf(lookDown(fileOutput, lineIndex, letterIndex, letter), "Down").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookDownRight(fileOutput, lineIndex, letterIndex, letter), "DownRight").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookRight(fileOutput, lineIndex, letterIndex, letter), "Right").filterNotNull() ?: emptyList())
    } else if (lineIndex == lineCount - 1 && letterIndex == 0) {
        // dont look down or left
        //println("Dont look down or left")
        checkPotential.addAll(listOf(lookUp(fileOutput, lineIndex, letterIndex, letter), "Up").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookUpRight(fileOutput, lineIndex, letterIndex, letter), "UpRight").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookRight(fileOutput, lineIndex, letterIndex, letter), "Right").filterNotNull() ?: emptyList())
    } else if (lineIndex == 0 && letterIndex == letterCount - 1) {
        // dont look up or right
        //println("Dont look up or right")
        checkPotential.addAll(listOf(lookDown(fileOutput, lineIndex, letterIndex, letter), "Down").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookLeft(fileOutput, lineIndex, letterIndex, letter), "Left").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookDownLeft(fileOutput, lineIndex, letterIndex, letter), "DownLeft").filterNotNull() ?: emptyList())
    } else if (lineIndex == lineCount - 1 && letterIndex == letterCount - 1) {
        // dont look right or down
        //println("Dont look right or down")
        checkPotential.addAll(listOf(lookLeft(fileOutput, lineIndex, letterIndex, letter), "Left").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookUp(fileOutput, lineIndex, letterIndex, letter), "Up").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookUpLeft(fileOutput, lineIndex, letterIndex, letter), "UpLeft").filterNotNull() ?: emptyList())
    } else if (lineIndex == 0) {
        // dont look up
        //println("Dont look up")
        checkPotential.addAll(listOf(lookDown(fileOutput, lineIndex, letterIndex, letter), "Down").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookDownLeft(fileOutput, lineIndex, letterIndex, letter), "DownLeft").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookDownRight(fileOutput, lineIndex, letterIndex, letter), "DownRight").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookLeft(fileOutput, lineIndex, letterIndex, letter), "Left").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookRight(fileOutput, lineIndex, letterIndex, letter), "Right").filterNotNull() ?: emptyList())
    } else if (letterIndex == 0) {
        // dont look left
        //println("Dont look left")
        checkPotential.addAll(listOf(lookUp(fileOutput, lineIndex, letterIndex, letter), "Up").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookUpRight(fileOutput, lineIndex, letterIndex, letter), "UpRight").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookDownRight(fileOutput, lineIndex, letterIndex, letter), "DownRight").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookDown(fileOutput, lineIndex, letterIndex, letter), "Down").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookRight(fileOutput, lineIndex, letterIndex, letter), "Right").filterNotNull() ?: emptyList())
    } else if (lineIndex == lineCount - 1) {
        // dont look down
        //println("Dont look down")
        checkPotential.addAll(listOf(lookUp(fileOutput, lineIndex, letterIndex, letter), "Up").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookUpLeft(fileOutput, lineIndex, letterIndex, letter), "UpLeft").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookUpRight(fileOutput, lineIndex, letterIndex, letter), "UpRight").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookLeft(fileOutput, lineIndex, letterIndex, letter), "Left").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookRight(fileOutput, lineIndex, letterIndex, letter), "Right").filterNotNull() ?: emptyList())
    } else if (letterIndex == letterCount - 1) {
        // dont look right
        //println("Dont look right")
        checkPotential.addAll(listOf(lookUp(fileOutput, lineIndex, letterIndex, letter), "Up").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookUpLeft(fileOutput, lineIndex, letterIndex, letter), "UpLeft").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookDownLeft(fileOutput, lineIndex, letterIndex, letter), "DownLeft").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookDown(fileOutput, lineIndex, letterIndex, letter), "Down").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookLeft(fileOutput, lineIndex, letterIndex, letter), "Left").filterNotNull() ?: emptyList())
    } else {
        //println("Else")
        checkPotential.addAll(listOf(lookUp(fileOutput, lineIndex, letterIndex, letter), "Up").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookDown(fileOutput, lineIndex, letterIndex, letter), "Down").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookLeft(fileOutput, lineIndex, letterIndex, letter), "Left").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookRight(fileOutput, lineIndex, letterIndex, letter), "Right").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookDownLeft(fileOutput, lineIndex, letterIndex, letter), "DownLeft").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookDownRight(fileOutput, lineIndex, letterIndex, letter), "DownRight").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookUpLeft(fileOutput, lineIndex, letterIndex, letter), "UpLeft").filterNotNull() ?: emptyList())
        checkPotential.addAll(listOf(lookUpRight(fileOutput, lineIndex, letterIndex, letter), "UpRight").filterNotNull() ?: emptyList())
    }
    return checkPotential
}

fun lookUp(fileOutput:MutableList<String>, lineIndex:Int, letterIndex:Int, letter:String): List<Int> {
    if (fileOutput[lineIndex-1][letterIndex].toString() == letter) {
        //println("We found $letter at index [${lineIndex-1}][$letterIndex]")
        return listOf(lineIndex-1,letterIndex)
    } else {
        return emptyList()
    }
}

fun lookDown(fileOutput:MutableList<String>, lineIndex:Int, letterIndex:Int, letter:String): List<Int> {
    if (fileOutput[lineIndex+1][letterIndex].toString() == letter) {
        //println("We found $letter at index [${lineIndex+1}][$letterIndex]")
        return listOf(lineIndex+1,letterIndex)
    } else {
        return emptyList()
    }
}

fun lookLeft(fileOutput:MutableList<String>, lineIndex:Int, letterIndex:Int, letter:String): List<Int> {
    if (fileOutput[lineIndex][letterIndex-1].toString() == letter) {
        //println("We found $letter at index [$lineIndex][${letterIndex-1}]")
        return listOf(lineIndex,letterIndex-1)
    } else {
        return emptyList()
    }
}

fun lookRight(fileOutput:MutableList<String>, lineIndex:Int, letterIndex:Int, letter:String): List<Int> {
    if (fileOutput[lineIndex][letterIndex+1].toString() == letter) {
        //println("We found $letter at index [$lineIndex][${letterIndex+1}]")
        return listOf(lineIndex,letterIndex+1)
    } else {
        return emptyList()
    }
}

fun lookUpLeft(fileOutput:MutableList<String>, lineIndex:Int, letterIndex:Int, letter:String): List<Int> {
    if (fileOutput[lineIndex-1][letterIndex-1].toString() == letter) {
        //println("We found $letter at index [${lineIndex-1}][${letterIndex-1}]")
        return listOf(lineIndex-1,letterIndex-1)
    } else {
        return emptyList()
    }
}

fun lookUpRight(fileOutput:MutableList<String>, lineIndex:Int, letterIndex:Int, letter:String): List<Int> {
    if (fileOutput[lineIndex-1][letterIndex].toString() == letter) {
        //println("We found $letter at index [${lineIndex-1}][${letterIndex+1}]")
        return listOf(lineIndex-1,letterIndex)
    } else {
        return emptyList()
    }
}

fun lookDownLeft(fileOutput:MutableList<String>, lineIndex:Int, letterIndex:Int, letter:String): List<Int> {
    if (fileOutput[lineIndex+1][letterIndex-1].toString() == letter) {
        //println("We found $letter at index [${lineIndex+1}][${letterIndex-1}]")
        return listOf(lineIndex+1,letterIndex-1)
    } else {
        return emptyList()
    }
}

fun lookDownRight(fileOutput:MutableList<String>, lineIndex:Int, letterIndex:Int, letter:String): List<Int> {
    if (fileOutput[lineIndex+1][letterIndex+1].toString() == letter) {
        //println("We found $letter at index [${lineIndex+1}][${letterIndex+1}]")
        return listOf(lineIndex+1,letterIndex+1)
    } else {
        return emptyList()
    }
}
/*
    Get X, get M, get direction, get A, get S
*/

fun part1(input:String) {
    val fileOutput = readFile(input)
    var direction: String
    var lineCount: Int
    var letterCount: Int
    var leads = mutableListOf<Any>()
    var i: Int = 0
    fileOutput.forEachIndexed { index, line ->
        line.forEachIndexed{ innerIndex, letter ->
            if (letter.toString() == "X") {
                leads = lookAround(fileOutput, index, innerIndex, fileOutput.size, line.length, "M")
                while(i < leads.size-2) {
                    if (leads[i].isNotEmpty()) {
                        direction = leads[i+1]
                    }
                    i+2
                }
            }
        }
    }
    println(leads)
}