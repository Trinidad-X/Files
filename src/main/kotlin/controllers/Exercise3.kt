import utilities.addToFileBeginning
import utilities.addToFileEnd
import utilities.askFileName
import utilities.checkIfFileExists
import utilities.createFile
import utilities.printDemandedLine
import utilities.read

fun main() {
    val fileName = askFileName()
    if (!checkIfFileExists(fileName)) {
        createFile(fileName)
    }

    addToFileEnd(fileName)

    read(fileName)

    printDemandedLine(fileName)

    addToFileBeginning(fileName)
}
