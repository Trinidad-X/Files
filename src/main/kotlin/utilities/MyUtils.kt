package utilities

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Scanner
import kotlin.io.path.appendText
import kotlin.io.path.writeText

/**
 * Prints the given message to the console.
 *
 * @param message The text that will be printed.
 * @return Unit
 * @author Trinidad Diez
 */
fun printInConsole(message: String) {
    // Use Kotlin's println to output the message to the console.
    println(message)
}

/**
 * Creates and returns a Scanner for console input.
 *
 * @return Scanner for reading input from the console
 * @author Trinidad Diez
 */
fun createScanner(): Scanner {
    // Scanner is used for reading input from standard input (console)
    return Scanner(System.`in`)
}

/**
 * Closes a given Scanner.
 *
 * @param scanner The Scanner to be closed.
 * @return Unit
 * @author Trinidad Diez
 */
fun closeScanner(scanner: Scanner) {
    // Free up resources by closing the scanner
    scanner.close()
}

/**
 * Checks if a file exists.
 *
 * @param fileName The name (or path) of the file.
 * @return True if the file exists and is a file, false otherwise
 * @author Trinidad Diez
 */
fun checkIfFileExists(fileName: String): Boolean {
    val file = File(fileName)

    // Return true only if the file exists and it is not a directory
    return file.exists() && file.isFile
}

/**
 * Creates a new file at the specified path.
 *
 * @param fileName The name (or path) of the file to create.
 * @return Unit
 * @author Trinidad Diez
 */
fun createFile(fileName: String) {
    val file = Paths.get(fileName)

    // Creates the file on the filesystem
    Files.createFile(file)

    // Inform the user that the file has been created
    printInConsole("The file $file now exists.")
}

/**
 * Appends user input to the end of a file.
 *
 * Special command: "@ESBORRA" clears the file before adding new content.
 *
 * @param fileName The name (or path) of the file to modify.
 * @return Unit
 * @author Trinidad Diez
 */
fun addToFileEnd(fileName: String) {
    // Inform the user to input text for the file
    printInConsole("Insert text you want to add to $fileName in the terminal (empty line to finish).")

    val target = Paths.get(fileName)
    val scanner = createScanner()
    var userInput = scanner.nextLine()

    // Loop until the user enters an empty line
    while (userInput.isNotEmpty()) {
        if (userInput == "@ESBORRA") {
            // Clear the file if special command is entered
            target.writeText("")
        } else {
            // Append the user input to the file
            target.appendText("$userInput\n")
        }
        userInput = scanner.nextLine()
    }

    // Close scanner after use
    closeScanner(scanner)
}

/**
 * Asks the user for a file name.
 *
 * @return String The file name entered by the user
 * @author Trinidad Diez
 */
fun askFileName(): String {
    val scanner = createScanner()

    // Read the next input token (word) from the user
    val name = scanner.next()

    // Close scanner to avoid resource leaks
    closeScanner(scanner)
    return name
}

/**
 * Asks the user for a line number.
 *
 * @return Int The line number entered by the user
 * @author Trinidad Diez
 */
fun askLine(): Int {
    // Prompt the user for input
    printInConsole("Insert the line number you want to print in the console.")

    val scanner = createScanner()

    // Read an integer from the user (line number)
    val lineNumber = scanner.nextInt()

    // Close the scanner
    closeScanner(scanner)

    return lineNumber
}

/**
 * Prints the content of a specific line from a file.
 *
 * @param fileName The name (or path) of the file.
 * @return Unit
 * @author Trinidad Diez
 */
fun printDemandedLine(fileName: String) {
    val file = File(fileName)

    // Ask the user which line to print
    val lineNumber = askLine()

    // Read the file line by line using BufferedReader
    BufferedReader(FileReader(file)).use { reader ->
        val lines = reader.readLines() // Read all lines into a list

        if (lineNumber in 1..lines.size) {
            // Print the requested line (1-based index)
            println(lines[lineNumber - 1])
        } else {
            // If line number is out of range, notify the user
            println("Line $lineNumber does not exist.")
        }
    }
}

/**
 * Prints the entire content of a file.
 *
 * @param fileName The name (or path) of the file.
 * @return Unit
 * @author Trinidad Diez
 */
fun read(fileName: String) {
    // Inform the user which file is being read
    printInConsole("The file $fileName says:")

    // Open the file and read all lines
    BufferedReader(FileReader(fileName)).use { reader ->
        reader.lines().forEach { line ->
            println(line) // Print each line
        }
    }
}

/**
 * Adds user input to the beginning of a file.
 *
 * @param fileName The name (or path) of the file.
 * @return Unit
 * @author Trinidad Diez
 */
fun addToFileBeginning(fileName: String) {
    // Prompt the user to enter text for the start of the file
    printInConsole("Insert text you want to add to the start of $fileName (empty line to finish):")

    val scanner = createScanner()
    val newLines = StringBuilder() // Temporary storage for new lines

    // Read multiple lines until an empty line is entered
    var userInput = scanner.nextLine()
    while (userInput.isNotEmpty()) {
        newLines.append(userInput).append("\n") // Add newline character after each line
        userInput = scanner.nextLine()
    }

    // Close scanner to free resources
    closeScanner(scanner)

    val path = Paths.get(fileName)
    // Read existing file content, if it exists
    val oldContent = if (Files.exists(path)) Files.readString(path) else ""

    // Write new content first, followed by old content
    Files.writeString(path, newLines.toString() + oldContent)
}
