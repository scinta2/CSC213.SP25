## Explanation ##

```bash
mkdir -p classes
```

The -p option ensures the directory is created only if it doesn’t already exist; no error if it’s there.

```bash
javac -d classes $(find src -name "*.java")
```

-d classes: Output the compiled .class files to the classes/ directory. Java will auto-create subdirectories matching the package structure (csc213/lecture7) inside classes/.
$(find src -name "*.java"): This uses the find command to gather all .java files under src/. Then it expands that list as arguments to javac.

```bash
echo "Compilation finished..."
```

Just a friendly message to indicate the script completed.

Remember to make the script executable - like so:

```bash
chmod +x compile.sh
```

## Run It!  ##

Let's talk classpath:

```bash
java -cp classes csc213.lecture7.Main
```
