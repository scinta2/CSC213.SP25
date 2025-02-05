# Remove the content of current classes directory/folder
rm -rf classes
mkdir classes

# Compile our Java source files!
# The destination for the output is the classes directory/folder
javac -d classes $(find src -name "*.java")



