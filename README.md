# Basic File Handling

## 1. Introduction & Motivation

Programs exist primarily to manipulate data, which can come from user input, sensors, or external files. However, data stored in variables is lost once the program terminates.

### Why use files?

There are two fundamental reasons for using files:

- **Persistence**  
  Guarantees data availability after the program closes or the computer is turned off.

- **Data Volume**  
  It is often not feasible or efficient to load massive amounts of data into memory for specific operations.

**Example:**  
You do not need to load every client into memory just to generate a single invoice.

---

## 2. The File System

To work with files, programs must interact with the system's directory structure. We use specific libraries (like `java.nio.file.Path` or `kotlin.io.path`) to manage paths.

### Path Types

- **Absolute Path**  
  The full path from the root directory  
  Example: `/home/philip/ocean/clam`

- **Relative Path**  
  The path relative to the current working directory  
  Example: `clam`

### Common Operations

Using the `Path` library, you can perform several checks and operations:

- **Check Path Type**  
  - Verify if a path is absolute using `.isAbsolute`  
  - Convert to an absolute path using `.toAbsolutePath`

- **Check Existence**  
  - Verify if a file exists using `.exists`

#### File Management

- `copyTo(target)`  
  Copies a source file to a target destination.

- `moveTo`  
  Moves a file.

- `deleteIfExists`  
  Deletes a file if it exists.

- `listDirectoryEntries()`  
  Lists the contents of a directory.

---

## 3. Reading Files

There are multiple libraries available for reading data (`File`, `Scanner`, `BufferedReader`) depending on the requirements.

### Methods for Small Files (Class `Path`)

> **Warning:** Only use these methods if you are certain the file is small.

- `readText()`  
  Returns the entire content as a single `String`.

- `readLines()`  
  Returns the content as a `List<String>`.

### Methods for Iterative Reading

- **Class `File`**  
  - Using `.forEachLine { ... }` allows processing the file line by line.

- **Class `Scanner`**  
  - Useful for reading line by line using `.hasNextLine()` and `.nextLine()`, similar to reading keyboard input.

- **Class `BufferedReader`**  
  - A robust alternative for reading streams of data.

---

## 4. Writing to Files

Writing modes are critical. In some languages, you must specify if you are opening a file for reading, writing, or both.

### Overwriting

If a file exists and you write to it using standard write methods, the previous content is lost (overwritten).

- **Method:**  
  `writeText("content")`

### Appending

To add content without deleting existing data, use append methods. This adds data after the last character of the current file.

- **Method:**  
  `appendText("content")`

- **Option:**  
  `StandardOpenOption.APPEND` can also be used with `writeText`.
