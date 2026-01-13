package utilities
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Scanner
import kotlin.io.path.appendText
import kotlin.io.path.writeText


fun printInConsole(message: String) {
    // Prints a message in the console.
    println(message)
}

fun createScanner(): Scanner {
    // Returns a scanner.
    return Scanner(System.`in`)
}

fun closeScanner(scanner: Scanner) {
    // Closes a scanner.
    scanner.close()
}


fun checkIfFileExists(fileName: String): Boolean {
    val file = File(fileName)

    // Checks if file exists
    return if (file.exists() && file.isFile) {
        true
    } else {
        false
    }
}

fun createFile(filename: String) {
    val file = Paths.get(filename)

    // Creates a file.
    Files.createFile(file)

    // Indicates the file now exists.
    val message = "The file $file now exists."
    printInConsole(message)
}

fun addToFileEnd(fileName: String) {
    // Asks for the text the user wants to add to the file.
    val message = "Insert text you want to add to $fileName in the terminal."
    printInConsole(message)

    val target = Paths.get(fileName)

    // Opens the scanner.
    val scanner = createScanner()

    // Saves the user's input in a variable.
    var userInput = scanner.nextLine()

    // Saves the variable in a file.
    while (userInput != "") {
        if (userInput == "@ESBORRA") {
            target.writeText("")
        } else {
            target.appendText("$userInput\n")
        }
        userInput = scanner.nextLine()
    }

    // Closes the scanner.
    closeScanner(scanner)
}

fun askFileName(): String {
    val scanner = createScanner()
    return scanner.next()
}

fun askLine() : Int {
    // Demana una linea.
    val message = "Insert the line you want to print in the console."
    printInConsole(message)

    val scanner = createScanner()

    val userInput : Int = scanner.nextInt()

    return userInput
}

fun printDemandedLine(filename: String) {
    val file = File(filename)

    // Demands a line.
    val userInput : Int = askLine()

    BufferedReader(FileReader(file)).use{
        println(userInput)
    }
}

fun read(fileName: String) {
    val file = fileName

    // Indicates which is the file.
    val message = "The file $fileName says:"
    printInConsole(message)

    // Prints the file's content in the screen.
    BufferedReader(FileReader(file)).use {
        it.lines().forEach {
            println(it)
        }
    }
}


fun addToFileBeginning(fileName: String) {
    // Asks for the text the user wants to add to the file.
    printInConsole("Insert text you want to add to the start of $fileName (empty line to finish):")

    val scanner = createScanner()
    val newLines = StringBuilder()

    var userInput = scanner.nextLine()
    while (userInput.isNotEmpty()) {
        newLines.append(userInput).append("\n")
        userInput = scanner.nextLine()
    }

    // Closes the scanner.
    closeScanner(scanner)

    val path = Paths.get(fileName)
    val oldContent = if (Files.exists(path)) Files.readString(path) else ""

    // Write new content FIRST, then old content
    Files.writeString(path, newLines.toString() + oldContent)
}