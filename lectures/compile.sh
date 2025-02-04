#!/bin/bash

# 1) Create the classes directory if it doesn't already exist
mkdir -p classes

# 2) Compile all .java files inside the src/ folder
#    -d classes tells javac to put .class files in the classes/ directory
javac -d classes $(find src -name "*.java")

echo "Compilation finished. Classes are in the 'classes' directory."

