import utilities.addToFileEnd
import utilities.askFileName
import utilities.checkIfFileExists
import utilities.createFile

fun main() {
    val fileName = askFileName()

    if (!checkIfFileExists(fileName)) {
        createFile(fileName)
    }

    addToFileEnd(fileName)
}
