# Console Tools

This is a Java console application tool that contains two applications and provides two primary functionalities.
1. **Caesar Cipher Tool**: uses Caesar Cipher algorithm to encryption or decryption of text.
2. **Arithmetic Expression Evaluator**: Parses and evaluates complex mathematical expressions.

---

##  Features
### Caesar Cipher

*  Encrypt and decrypt entered text using Caesar Cipher algorithm
*  Accept text input in Russian and English languages from console
*  Accept text input in Russian and English languages from a file
*  Case-sensitive: keeps uppercase/lowercase
*  Non-alphabetic characters (spaces, punctuation, numbers) should remain unchanged
*  Decrypt without knowing the shift
*  Auto-detects language for decryption

### Arithmetic Expression Evaluator

*  Parse and evaluate arithmetic expressions
*  Support basic operations: addition (+), subtraction (-), multiplication (*), division (/)
*  Support parentheses for operation precedence
*  Return the calculated result
*  Throws meaningful errors for invalid syntax or division by zero

## Requirements

* Java 8+
* Terminal/Command prompt

##  How to Compile and Run

### Compile:

```bash
javac ConsoleTools.java
```

###  Run:

```bash
java ConsoleTools
```
---

##  Example Usage
```
Welcome to Gehtsoft Technical Assessment
Please choose an option:
1. Caesar Cipher Encryption
2. Caesar Cipher Decryption
3. Arithmetic Expression Evaluation
4. Exit
```
### Encrypt
```
Caesar Cipher (Encrypt mode)
Choose 1 to use Console
Choose 2 to use data from the file
Choose 3 to exit
Enter your choice: 1
Encrypt mode:
Enter text to encrypt: Hello World
Enter the used shift (can be positive and negative): 3
Your entered text: Hello World, shift: 3
New Text: Khoor Zruog
Continue? (y/n):
```

###  Decrypt (known shift)
```
Caesar Cipher (Decrypt mode)
Choose 1 to use Console
Choose 2 to use data from the file
Choose 3 to exit
Enter your choice: 1
Decrypt mode:
Enter text to decrypt: Khoor Zruog
Enter the used shift (can be positive and negative): 3
Your entered text: Khoor Zruog, shift: 3
New Text: Hello World
Continue? (y/n): 
```

###  Decrypt (unknown shift)

```
Caesar Cipher (Decrypt mode)
Choose 1 to use Console
Choose 2 to use data from the file
Choose 3 to exit
Enter your choice: 1
Decrypt mode:
Enter text to decrypt: Khoor Zruog
Enter the used shift (can be positive and negative): 
Possible variants without known shift (LATIN):
Used Shift: 1, Decrypted text: Jgnnq Yqtnf
Used Shift: 2, Decrypted text: Ifmmp Xpsme
....
```

---

##  Example File Usage

```
Enter your choice: 2
Encrypt mode:
Enter file path: /path/to/input.txt
```
---
