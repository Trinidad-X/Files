# M3 – Programming: File Management

This document is a complete and structured **README.md** that synthesizes the technical, theoretical, and practical information contained in the three PDF documents (*Basic Files*, *Binary Files*, and *Record Files*).

All code is adapted to **Kotlin**, as shown in the original examples.

---

## 📋 Table of Contents

1. Introduction and Motivation  
2. Types of Files  
3. File System Management (File and Path classes)  
4. Text Files (Records)  
5. Binary Files  

---

## 1. Introduction and Motivation

Data manipulation is the main function of applications. However, data stored in memory is lost when the program finishes. File usage is necessary for two fundamental reasons:

- **Persistence**: Ensures that data remains available after shutting down the computer or closing the program.
- **Volume**: Manages data that does not fit into RAM or is inefficient to load entirely (e.g., loading a single customer from a very large database).

The basic process (often called the *protocol*) always follows three steps:

**Open → Process → Close**

---

## 2. Types of Files

There are two main categories depending on their internal encoding:

### A. Text Files

- **Content**: ASCII / Unicode data readable by humans.
- **Structure**: Require separators (spaces, tabs, line breaks) or fixed widths to distinguish elements.
- **Example**: The numbers `12` and `100` must be separated (`12 100`) to avoid confusion with `12100`.
- **Efficiency**: Less efficient. The number `12` uses 2 bytes (`'1'` + `'2'`).

### B. Binary Files

- **Content**: Encoded data not directly readable by humans.
- **Structure**: No separators required. The data type defines its size (e.g., an `Int` always occupies 4 bytes).
- **Efficiency**: More compact and faster (no format conversion needed).  
  Example: The number `12` stored as a byte uses only 1 byte.

---

## 3. File System Management

To manage paths, folders, and file properties (without reading their contents), the main classes used are:

- `java.io.File`
- `java.nio.file.Path`

### Common Operations (File class)

```kotlin
import java.io.File

val f = File("test.txt")

// Checks
if (f.exists()) { ... }      // File/directory exists
if (f.isFile) { ... }        // Is a file
if (f.isDirectory) { ... }   // Is a directory

// Management
f.createNewFile()             // Creates an empty file
f.mkdir()                     // Creates a directory
f.delete()                    // Deletes the file
f.list()                      // Returns Array<String> with directory contents
f.renameTo(File("new.txt"))   // Rename
```

## Modern Operations (Path class – Kotlin extensions)

```kotlin
import kotlin.io.path.*

val path = Path("./txt/file.txt")

// Paths
val isAbsolute = path.isAbsolute
val absolute = path.toAbsolutePath()

// Copying
val source = Path("./source.txt")
val target = Path("./target.txt")
source.copyTo(target)
```

## 4. Text Files (Records)

There are several ways to read and write text files, from simple Kotlin utilities to classic Java IO classes.

---

### Reading

#### Option A: Kotlin Utilities (Small files)

```kotlin
import kotlin.io.path.*

val path = Path("data.txt")
val text: String = path.readText()          // Entire content as String
val lines: List<String> = path.readLines()  // List of lines
```
### Option B: BufferedReader (Large files / Line by line)

```kotlin
import java.io.*

val file = File("data.txt")
BufferedReader(FileReader(file)).use { buffer ->
    buffer.lines().forEach { line ->
        println(line)
    }
}
```

### Option C: Scanner

```kotlin
import java.io.File
import java.util.Scanner

val sc = Scanner(File("data.txt"))
while (sc.hasNextLine()) {
    val line = sc.nextLine()
    println(line)
}
```
## Writing

### Option A: Simple Kotlin

```kotlin
import java.io.File

val file = File("output.txt")
file.writeText("Hello world")      // Overwrites
file.appendText("\nNew line")      // Appends
```

### Option B: PrintWriter / FileWriter

Allows writing similarly to console output (`print`, `println`).

---

## 5. Binary Files

Binary files are handled using **Streams**. The typical architecture wraps a `FileOutputStream` inside a `DataOutputStream` to easily handle primitive data types (`Int`, `Double`, etc.).

---

### Binary Writing (DataOutputStream)

```kotlin
import java.io.*

val file = File("data.bin")
// 'true' enables append mode, 'false' overwrites
val fos = FileOutputStream(file, false)
val dos = DataOutputStream(fos)

val number: Int = 10
val price: Double = 3.14
val text: String = "Test"

dos.writeInt(number)       // Writes 4 bytes
dos.writeDouble(price)     // Writes 8 bytes
dos.writeUTF(text)         // Writes String with its length first

dos.flush() // Forces buffer flush
dos.close() // Closes the stream
```

**Note:**  
To write strings with special encodings or manual control, you can use:

```kotlin
fos.write(text.toByteArray())
```

### Binary Reading (DataInputStream)

The reading order **must match exactly** the writing order.

```kotlin
import java.io.*

val file = File("data.bin")
val fis = FileInputStream(file)
val dis = DataInputStream(fis)

// Read in the same order as written
val n = dis.readInt()
val d = dis.readDouble()
val s = dis.readUTF()

println("Read: $n, $d, $s")

dis.close()
```

