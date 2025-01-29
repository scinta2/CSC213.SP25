# Lab 2 - Welcome to Containers!  #

Think about these scenarios

- I want you (the student) to learn Java.  I want you to have to install all the wonderful tools associated with the languge.  What could I do, say, in our lab classroom?

- I want to give a Java environment to you.  I want it all preconfigured - ready to go.  What could I do?

- I have a Java program that works with a relational database.  How could I put those two together easily?

Welcome to containers.  A solution to so so many problems.  

Oh - before we start - issue this command:

```bash
sudo usermod -aG docker <username>
```

Logout and then log back in.

## What is a Container? ##

I went over this before.  But, here is a review with a bit more detail!  This will become more concrete as you do the exercises.

Below is a **simple Dockerfile** that starts with Ubuntu, updates the package lists, and installs a few packages. It then sets Bash as the default command.

```dockerfile
# Dockerfile
FROM ubuntu:latest

# Update package lists and install some basic utilities
RUN apt-get update && apt-get install -y \
    curl \
    vim \
    # Other packages you might want
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Default command to run when the container starts
CMD ["tail", "-f", "/dev/null"]
```
A Dockerfile basically defines an operating system environment—down to the base OS, installed packages, and default commands—all in a straightforward, plain text script. Then Docker builds a container image from it, which you can run anywhere Docker is installed.  It's a **wild** and **powerful** concept!

---


## 1. Create (or Copy) Your Dockerfile

Let’s say your Dockerfile looks like this:

```dockerfile
# Dockerfile
FROM ubuntu:latest
RUN apt-get update && apt-get install -y curl
# This keeps the container running indefinitely!
CMD ["/bin/bash"]
```

1. Open a text editor.
2. Copy the above lines (or your custom Dockerfile) and **save** it as `Dockerfile` in a new folder.

---

## 2. Build an Image from the Dockerfile

Open a terminal (or command prompt) in that folder and run:

```bash
docker build -t my-ubuntu-image .
```

### Explanation
- **`-t my-ubuntu-image`** gives your image a name (`my-ubuntu-image`).
- The **`.`** (dot) at the end tells Docker to use the **current directory** (which has your Dockerfile).

When it’s done, you’ll see something like “Successfully built \[hash\]” and “Successfully tagged my-ubuntu-image:latest.”

---

## 3. Verify the Image Exists

List your local images:

```bash
docker images
```

You should see **`my-ubuntu-image`** in the output, something like:

```
REPOSITORY           TAG       IMAGE ID       CREATED          SIZE
my-ubuntu-image      latest    123abc456def   2 minutes ago    150MB
```

Think of the **image** like an **“.exe” file** in Windows: it’s a static package stored on your machine.

---

## 4. Run a Container from the Image

Start a container:

```bash
docker run -d --name my-ubuntu-container my-ubuntu-image
```

### Explanation
- **`docker run`** starts a new container from the image.
- **`-d`** (detached mode) means it will run in the background (like launching Word in the background).
- **`--name my-ubuntu-container`** gives the container a friendly name.
- **`my-ubuntu-image`** is the image you built in step 2.

---

## 5. See It “Running”

Check running containers:

```bash
docker ps
```

You should see something like:

```
CONTAINER ID    IMAGE                COMMAND        STATUS           PORTS    NAMES
abcdef123456    my-ubuntu-image      "/bin/bash"    Up 5 seconds              my-ubuntu-container
```

- This is akin to **seeing your Word process** in your operating system’s task manager.  

You can also 'bash' into the container.  Sort of like ssh'ing into it.  Like so:

```bash
docker exec -it my-ubuntu-container /bin/bash
```

Now you’re in another Bash session inside the same container. Type exit to leave that session (the container remains running until you exit the first terminal or explicitly stop it).

---

## 6. Stop the Container

Stop the container (like closing Word):

```bash
docker stop my-ubuntu-container
```

When the stop command completes:

```bash
my-ubuntu-container
```

---

## 7. Confirm It’s No Longer Running

```bash
docker ps
```
- Now you won’t see `my-ubuntu-container` in the running list.

But if you check **all** containers (including stopped ones):

```bash
docker ps -a
```

You’ll see something like:

```
CONTAINER ID    IMAGE                COMMAND       STATUS                      NAMES
abcdef123456    my-ubuntu-image      "/bin/bash"  Exited (0)  10 seconds ago  my-ubuntu-container
```

- This is similar to **“the process is no longer running,”** but the **“history”** (or container metadata) remains until you remove it.

---

## 8. Check the Image Is Still There

Even though the container (process) is stopped, the **image** is still on your system:

```bash
docker images
```

You’ll still see:

```
REPOSITORY        TAG       IMAGE ID       CREATED         SIZE
my-ubuntu-image   latest    123abc456def   4 minutes ago   150MB
```

This is **just like** how the `.exe` for Word remains on your disk even after you close the application.

---

##9. Are There Pre-Fab Containers

Yes.  100s of thousands.  Let's take a peek at this site:

[https://hub.docker.com](https://hub.docker.com)

---

## Summary so Far

- **Docker Image** → Think of it as an **“.exe”** file on Windows. It **sits on disk**, ready to be run.
- **Docker Container** → Think of it as the **process** that runs when you double-click the `.exe`.  
  - **Starting** the container is like **launching the program**.  
  - **Stopping** the container is like **closing the program**.  
  - The “process” may stop, but the “exe” (the Docker image) **remains** on your system.

## What Are Docker Layers?

Docker images are built in **layers**. Each instruction in your Dockerfile (`FROM`, `RUN`, `COPY`, etc.) creates a **new layer**. Layers are essentially **read-only snapshots** of the filesystem at each build step. The final Docker image is composed of **all** these stacked layers.

**Why does Docker do this?**  
- **Reusability**: When you build a new image that reuses the same layers, Docker doesn’t need to rebuild or re-download them—it caches them for faster builds and smaller downloads.  
- **Efficiency**: Only new or changed layers need to be transferred or rebuilt when your Dockerfile updates.

![](https://miro.medium.com/v2/resize:fit:1302/format:webp/1*AkZf5G5bfV7vq9XT9b83vQ.png)
---

## Where Are Layers Stored?

On a typical Linux Docker host, layers are stored under:
```
/var/lib/docker/overlay2/
```
*(assuming you’re using the **overlay2** storage driver.)*  

You’ll see a bunch of directories named with **hex** IDs—these map to the **layer IDs**. Docker manages these under the hood. Each directory roughly corresponds to a layer, but it’s not trivial to navigate them directly because Docker merges (overlays) them into a single file system view for your container.

If you want to **“get a gander”** (inspect) at the layers in a more human-readable way, try:

```bash
docker history <image-name-or-id>
```
This shows a list of layers in the image, which Dockerfile command created each layer, and the size of that layer. For example:

```bash
docker history ubuntu:latest
```
Might output something like:

```
IMAGE               CREATED             CREATED BY                                      SIZE        COMMENT
<hash>              3 days ago          /bin/sh -c #(nop)  CMD ["bash"]                0B
<hash>              3 days ago          /bin/sh -c #(nop)  ADD file:................   72.8MB
...
```

Each line corresponds to a **layer**. That’s generally more illuminating than looking at the raw overlay2 directories.

Below is a **step-by-step guide** to demonstrate the **build/run/stop** lifecycle of a Docker container—emphasizing how a container is **like a process** that you can start, see running, then stop, while the **image** remains intact on your system (much like the “.exe” in Windows remains on disk even after you close the application).

---


## 10. Lab Exercise

Here is your lab challenge!  You will need to do this part yourself!  When we ran the Ubuntu container the command we ran was:

```bash
CMD ["/bin/bash"]
```

Which keeps it running.  Like a server.  However, you can have a container that runs, performs a task, and once it completes it stops.  For example - run from the command line:

```bash
docker run hello-world
```

All it does is print out a message.  So - you will need to create a container that does this!  

---

## Lab Assignment: Build and Run a Simple Java Program in a Docker Container

### Objectives

- Learn how to use an **existing Docker base image** (OpenJDK 17).  
- Practice **copying** files into the container.  
- **Compile** and **run** a Java program inside Docker.  
- Demonstrate a working container by capturing its **output**.

---

### 1. Dockerfile Template

Below is the Dockerfile **template**. Three steps (`COPY`, `RUN`, `CMD`) are missing, labeled `<< YOU FIGURE OUT THE CODE! >>`.

```dockerfile
# 1) Start from an OpenJDK 17 image on Docker Hub
FROM openjdk:17

# 2) Set a working directory for your app (optional, but good practice)
WORKDIR /app

# 3) Copy your Java source file into the container
<< YOU FIGURE OUT THE CODE! >>

# 4) Compile your Java source code
<< YOU FIGURE OUT THE CODE! >>

# 5) Define the default command to run your compiled program. 
<< YOU FIGURE OUT THE CODE! >>
```

**Your Task**: 
- Fill in the **three missing lines** (for `COPY`, `RUN`, and `CMD`) so that the container can **compile** and **run** your Java program.

**Hints**:  

- Use the `COPY` instruction to copy your source code from the **host** (the folder with your Dockerfile) into `/app` **inside** the container.  

- Use the `RUN` instruction to compile your Java code.  
- Use `CMD` (or `ENTRYPOINT`) to **run** your Java program.
- The program just needs to say something like "Hello World, CSC 213 is great!"

---

### 2. Build Your Docker Image

1. You will probably want to place your **Dockerfile** and your **Java source file(s)** in the **same directory**.  
2. Open a terminal in that directory.  
3. Run:

   ```bash
   docker build -t my-java-app .
   ```
   - `my-java-app` is a **name** (tag) for your image.  
   - `.` (dot) means “look for the Dockerfile here.”

---

### 3. Verify the Image

After a successful build, list your images:

```bash
docker images
```

You should see `my-java-app` in the list.

---

### 4. Run Your Container

Run the container and watch it print your program’s output:

```bash
docker run --rm my-java-app
```

- `--rm` tells Docker to remove the container once it’s finished (helpful for a short-lived program).  
- You should see the **output** of your Java program in the terminal (e.g., “Hello World!”), after which the container will exit.

---

### 5. Submit Your Work

A D2L dropbox has been established.  Include:

1. **Dockerfile**: Show how you completed the missing lines (`COPY`, `RUN`, `CMD`).  
2. **Java source code** (e.g., `HelloWorld.java`).  
3. **Proof of success**: 
   - A screenshot or copy/paste of the terminal output from running:

     ```bash
     docker build -t my-java-app .
     docker run --rm my-java-app
     ```
   - The output should include your “Hello World!” (or similar) text from the Java program.

