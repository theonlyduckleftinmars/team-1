#!/bin/bash

cd src || exit

echo "Compiling files!"
javac Main.java

echo "Compilation successful! Running program."
java Main.java
