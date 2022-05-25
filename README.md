# JavaSpace
JavaSpace is a high-level statically-typed object-oriented general-purpose programming language.
The syntax of JavaSpace is greatly inspired by Java but improved to provide less verbose coding experience.

## Features
- Primitive and class types with type inference
- Control statements like ‘for’ loop and ‘if’ statement
- Prohibition of static members of any kind
- Declaring classes and functions without corresponding keywords
- Functions default parameters
- Named parameters support

## How to run
1. Build JavaSpace compiler

```bash
mvn clean package
```
2. Compile any .javaspace file (for examples check ./JavaSpaceExample folder)

```bash
java -jar .\compiler\target\compiler-1.0-SNAPSHOT-jar-with-dependencies.jar .\JavaSpaceExamples\HelloWorld.javaspace
```

3. Run compiled .javaspace program

```bash
java HelloWorld
```