## Lab 3 - Packages, Methods, & Classes ##

Learning Objectives for this lab:

* You will learn (again) about forking a GitHub repo and inviting a collaborator.
* You will learn about java packages and compartmentalization.
* You will learn how the compile process works.
* You will learn how methods are defined in java.
* You will then be asked to implement your own method.

### Step 1. Fork This Repo!  ###

You should first do the following:

1. Log into GitHub.
2. Fork this repository into your GitHub space.
3. Invite your professor ```delveccj``` as a collaborator to the forked project.
4. Clone the forked repository to the lab machine.  That is, go to your repositories on GitHub, find the forked version, and clone that!  If you clone this repo and make your changes there - you will not be able to push them back to GitHub!

This lab will reference the following folder:

```lab3```

So, ```cd``` into that directory.

### Step 2.  Review the Files ###

Here is the listing of files and folders you will find in the ```lab3``` folder.

```bash
├── .gitignore
├── README.md
├── compile.sh
├── run.sh
└── src
    └── edu
        └── canisius
            └── csc213
                ├── lab3
                │   └── DebugExample.java
                └── util
                    └── SysDebugger.java
```

```.gitignore``` - The files and directories not to include in git.

```README.md``` - The file you are reading right now!

```compile.sh``` - This will compile all the source code in the ```src``` directory and write the results to the ```classes``` folder.  Remember what the ```.gitignore``` instructed git to do!

```run.sh``` - This will run the compiled code.  

Before we go any further, let's change the permissions on the scripts (the ```.sh``` files) so they are executable.  Do the following:

```chmod a+x *.sh```

### Step 3.  Let's Peek at the Source Code ###

Cd into the following directory:

```src/edu/canisius/csc213/util```

cat the SysDebugger.java file.  Like so:

``` cat SysDebugger.java ```

Let's concentrate on the package identifier at top.  It is basically indicating the following:

"Hey, I am Java source code file.  My location (in terms of the directory hierarchy) is edu/canisius/csc213/util.  When you want to reference me - you need import that part of the directory hierarchy into your Java class."

Take a look at the *method* that is in the file.  You might not understand everything it does - but all you need to understand is the gist of what is happening.  The method finds the set of running processes (that is, all the running processes on the operating system where you run the script) and dumps them out to the console.

Note the package structure:

```edu``` - indicates this source code was created by university or college.

```canisius``` - the university is in fact our beloved Canisius. 

```csc213``` - this source code is relative to Computer Science 213.  It is easy to imagine other folders with out CSC course numbers.

```util``` - this indicates a folder that holds utilities.  These could be used by any Java class in the project.  It is a way to promote reuse and centralization of the code.

Great!  Ok - so who uses this utility class?  Cd up one directory and into the ```lab3``` folder.  Perform a ```cat``` on ```DebugExample.java```.

What this code does is (eventually) call the method ```dangerousOperation```.  It's not really that dangerous.  Every so often it will throw an exception.  When this exception is caught, it does something important.  It:

1. Finds the ```SysDebugger``` class,  
2. Finds the method ```logRunningProcesses```, and
3. Calls the method.

This will print out the running processes at the moment the exception occurred.  It is meant to simulate a logging message a developer might add into the code.  Here, the developer wanted to know else was running when the exception happened; could one of these processes contributed to the exception?

It is easy to imagine wanting to call ```logRunningProcesses``` from any number of java classes in any number of situations.  Therefore, it is promoted to the level of a utility and its source code is **defined in one location so it is repeatable and never, ever to be copied and pasted**.  That's the idea of logically named packages.

### Step 4.  Compile that Code ###

Now, cd back into the ```util``` diretory.  Once there, issue these commands:

```bash
javac *.java
ls
```
Great - you see the compiled class.  Be **sure** to remove the .class file - like so:
 
 ```bash
 rm SysDebugger.class
 ```
 
 Now, cd into the ```lab3``` directory.  Once again, issue this command:
 
```bash
javac *.java
```

Is it happy?  Why not?  Let's discuss as a class.

Here is how to compile the whole project.  Cd back into the lab3 folder.  You can do this:

```bash
cd ../../../../../
```

What did it do?  Can anyone tell me?  When you do an ```ls``` you should see the compile script.  Let's ```cat``` that file.  You will see how to compile the entire directory.  The ```find``` command recursively finds all the files with a java extension and provides them to the compiler.  The compiler is smart enough to do the linking of files and ordering of the compilation.

Run the compile script.  Like so:

```bash
./compile.sh
```

Now, cd into the ```classes``` directory.  Issue the ```tree``` command.  You will see the compiled classes!

Last piece of the puzzle.  Let's run the code.  Cat the file ```compile.sh```.  

Here is what it does:

1. **`java`**  
   This is the Java command used to run a Java application.

2. **`-cp classes`** (or `-classpath classes`)  
   The `-cp` flag specifies the classpath, which tells Java where to look for compiled `.class` files.  
   - In this case, `classes` is the directory containing the compiled Java files.

3. **`edu.canisius.csc213.lab3.DebugExample`**  
   This specifies the fully qualified class name of the Java program to run.  
   - The structure follows the package hierarchy (`edu.canisius.csc213.lab3`).  
   - `DebugExample` is the class that contains the `main` method.

This command runs the `DebugExample` class (which is inside the `edu.canisius.csc213.lab3` package or directory structure) using Java. It expects that the compiled `.class` files are in the `classes` directory.

You might have to run it a few times to get the exception to be thrown!

## Your Task ##

Here is your task.  It's pretty easy.  You need to do the following:

Add a method to ```SysDebugger.java```.  Here is the signature of the method:

```java
    public static void logCurrentDateTime() {
		// Your code goes here!
    } 
```
You should then call it where there is a comment in ```DebugExample``` that says:

```bash
// 3)Log the time of the crash
```

The output of the call should print to the console a message like the following.  Modify the body of ```logCurrentDateTime()``` so that it prints something like this to the console.  It's up to you to poke around the Java API or Google how to print the date and time in Java.

```bash
=== Current Date & Time ===
2025-02-05T10:44:01.809074
===========================
```

You may need to run the code multiple times force an error and see the date and time message.  Once you get your code to run and you are satisfied, you then need to:

1. git add
2. git commit and 
3. git push 

Your changes to the forked repo.  

A DropBox has been made on D2L for this lab.  Commit a text file that includes:

1. The URL of your forked repo, and
2. Copied output that shows the error case and proves that the time of the exception is printed as well.
