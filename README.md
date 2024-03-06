# **Continuous Integration (Github Actions + Dockerhub)**

This project is an example of a Java Spring Boot API configured to perform continuous integration. Each time the 'main' branch is updated via push or pull_request, Github Actions runs the continuous integration script, which will generate a new API image and publish it to the [Dockerhub](https://hub.docker.com/) repository. The important points of how this continuous integration was implemented will be detailed below.

&nbsp;

## The manual way

Continuous integration is understood to be an automated process. Before explaining how this process was implemented, let's first remember how the manual process works.

1. Generate the app JAR file
```
$ mvn clean package
```
2. Verify if the Dockerfile exists
3. Verify if the docker-compose.yml responsible for building the app image exists
4. Docker login
```
$ docker login
```
5. Docker push
```
$ docker push repository/image-name:latest
```

However, this process is repetitive and prone to errors. Fortunately, we can automate this process with Github Actions.

&nbsp;

## Github Actions

We can summarize the Github Actions process as follows:

1. Developer: pushes code to Github
2. Github Actions: builds docker image and pushes to Docker Hub
3. Image available at Docker Hub

### Repository secrets

Continuous integration requires login to dockerhub. Therefore, it is good practice to add login credentials as secrets. To store this information, go to:

Repository main page >> Settings >> Secrets and variables >> Actions >> New repository secret

### Actions script

To create the continuous integration script, go to:

Repository main page >> Actions

You can create a script from scratch, or use an existing one. In my case, I used the Java with Maven script (By GitHub Actions).

The script developed for this project was divided into the following steps:

1. Trigger rule (update 'main' branch via push)
2. Operating system used for the jobs
3. Github actions version (v3)
4. Docker Login (using repository secrets)
5. Setting the Java JDK version
6. Build the application JAR file
7. Run docker-compose that builds the image (using Dockerfile)
8. Publish the images to the Docker Hub repository

The script is in the .github/workflows directory

### Build

By accessing the Actions page, you can follow each step of the script execution. If all steps are carried out successfully, the images will be available in the Docker Hub repository.

### Testing the generated image

To run published images, use docker-compose.yml from the resources/dockerhub directory. Unlike the docker-compose file at the project root, this one does not execute the 'build' command. It will just fetch the published image from the Docker Hub repository and run it on your machine.

&nbsp;

## **Project Dependencies**

- Docker
- Docker compose
- Java JDK 17
- Maven 3.9.3+