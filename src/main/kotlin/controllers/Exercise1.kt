import utilities.askFileName
import utilities.closeScanner
import utilities.createScanner
import utilities.printInConsole
import java.nio.file.Paths
import kotlin.io.path.appendText
import kotlin.io.path.writeText


fun main() {
    val fileName = askFileName()
    saveInFile(fileName)
}


fun saveInFile(fileName: String) {
    val message = "Insert text you want to save in a file in the terminal."
    printInConsole(message)

    val target = Paths.get(fileName)

    // Clears the file before writing
    target.writeText("")

    // Opens the scanner.
    val scanner = createScanner()

    // Saves the user's input in a variable.
    var userInput = scanner.nextLine()

    // Saves the variable in a file.
    while (userInput != "") {
        target.appendText("$userInput\n")
        userInput = scanner.nextLine()
    }

    // Closes the scanner.
    closeScanner(scanner)
}