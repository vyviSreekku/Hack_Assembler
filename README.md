# Hack_Assembler

A Java-based assembler for the Hack machine language, written as part of the Nand2Tetris course.

## Overview

This project translates Hack assembly code (`.asm`) into Hack machine code (`.hack`). The assembler processes symbolic references, handles A- and C-instructions, and outputs binary code compatible with the Hack computer platform specified in the Nand2Tetris curriculum.

## Features

- Parses Hack assembly language with support for symbolic labels and variables.
- Translates A-instructions and C-instructions to binary machine code.
- Strips out whitespace and comments for clean parsing.
- Produces `.hack` files ready to run on the Hack CPU emulator.

## Structure

- **Assembler.java**: Main entry point. Coordinates the conversion process.
- **Assembler_Classes/**
  - `Whitespace_handler.java`: Removes whitespace and comments from source files.
  - `Symbol_Variables_handler.java`: Builds and manages the symbol table for labels and variables.
  - `A_c_instruction_handler.java`: Translates A- and C-instructions into binary.
- **assembly.asm**: Example Hack assembly file (sums numbers 1 to N).
- **assembly.hack**: Assembled output (machine code) for the example.

## Usage

1. Place your `.asm` source file in a known location.
2. Update the input and output file paths in `Assembler.java`'s `main` method if needed:
   ```java
   assemblyCodeGenerator("D:\\EOC_ASSEMBLER\\assembly.asm", "D:\\EOC_ASSEMBLER\\assembly.hack");
   ```
3. Compile and run the program:
   ```sh
   javac Assembler.java Assembler_Classes/*.java
   java Assembler
   ```
4. The assembler will output the translated `.hack` file at the specified location.

## Requirements

- Java 8 or higher.

## Example

**assembly.asm**
```asm
// Program: Sum1ToN (R0 represents N)
// Computes R1 = 1 + 2 + ... + R0
// Usage: put a value >=1 in R0
@i
M=1
@sum
M=0
(LOOP)
@i
D=M
@R0
D=D-M
@STOP
D;JGT
// ... rest of program ...
```

**assembly.hack (output)**
```
0000000000010000
1110111111001000
...
```

## References

- [Nand2Tetris](https://www.nand2tetris.org/)
