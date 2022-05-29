# ConferenceManager

![image](https://user-images.githubusercontent.com/25953723/170649002-0e41b521-37f8-4764-ba19-17005c4a7773.png)

# How to run project
## 1.Run Spring Boot app with java -jar command
Download, unpack and navigate to project directory. First use the maven command to create executable jar file in target directory.
```
.\mvnw package
```

Then use the below command to run your Spring Boot app with java -jar command:
```
java -jar target/ConferenceManager-0.0.1-SNAPSHOT.jar
```


## 2.Run Spring Boot app using Maven
You can also use Maven plugin to run your Spring Boot app. Download, unpack and navigate to project directory. Then use the below command to run your Spring Boot app with Maven plugin:
```
.\mvnw spring-boot:run
```

To stop running the server type ``` Ctrl + c ``` in your command line

# How to test application
Project uses testing api tool SpringDoc OpenApi UI - Swagger. After running it you can see possible endpoints of the application and simple documentation. Just type address below in you browser:
```
http://localhost:8080/swagger-ui/index.html#/
```
You can also use H2 database query tool in you browser to check the database structure. Default credentials: username: "sa", password: ""
```
localhost:8080/h2-console/login.jsp
```
Application has default lectures and test users already created. 

Users             |  Lectures
:-------------------------:|:-------------------------:
![image](https://user-images.githubusercontent.com/25953723/170862517-7a2c0f67-3248-4519-987d-31d5e23ebee3.png)  |  ![image](https://user-images.githubusercontent.com/25953723/170862562-1488dc4c-56a8-4164-90d4-2958a4a3442f.png)

# Sample requests
## Get all users
Returns all users in the system.

![image](https://user-images.githubusercontent.com/25953723/170864657-88b22e4d-ae65-4469-bac8-3a57a1a7cdd5.png)

## Create user
Using this endpoint you can create new user in the system. User username and email must be unique. If user with this username or emails exists in the system response contains a message about it.

Create user             |  Create user with existing username | Create user with existing email
:-------------------------:|:-------------------------:|:-------------------------:
![image](https://user-images.githubusercontent.com/25953723/170864815-dff8efd0-859d-4ff0-8592-709311a8c00d.png)  |  ![image](https://user-images.githubusercontent.com/25953723/170864882-68de0b9d-0a0a-4522-abfb-f37606673a01.png) | ![image](https://user-images.githubusercontent.com/25953723/170864931-b2c2f57d-55a1-4ca2-896d-5ac336d3d58b.png)

## Update email
Using this endpoint you can update user's email address. System checks if user with given username exists. If not the message about it is returned.

Update email for existing user |  Update email for not existing user
:-------------------------:|:-------------------------:
![image](https://user-images.githubusercontent.com/25953723/170865186-ec52fa22-2b9a-48da-84d3-def66c70cbe6.png) | ![image](https://user-images.githubusercontent.com/25953723/170865211-c6c255aa-4d9c-4589-ba6a-a17d157a38f4.png)

## Get all lectures
Returns a list of lectures.

![image](https://user-images.githubusercontent.com/25953723/170865304-e5ff867a-e923-4c30-bd74-dd366d07a9d5.png)

## Get user lectures

![image](https://user-images.githubusercontent.com/25953723/170865533-693e4d59-fa49-4330-a517-549b8b0ee001.png)

## Reserve spot in lecture
Using this endpoint you can register user to chosen lecture. method takes username, email and lecture id as parameters. Checks if user with username and email exists, if username and email match each other, if user is already registered to given lecture and if lecture is already full. Returns a message if any of this conditions is not fulfilled. Also sends 'email' to file named 'powiadomienia.txt'.

Registering user to lecture|  User not found 
:-------------------------:|:-------------------------:
![image](https://user-images.githubusercontent.com/25953723/170867466-a8eccea5-1a5a-4afc-b172-01d91658db82.png) | ![image](https://user-images.githubusercontent.com/25953723/170867523-64f9d6d1-8b88-4bd3-abb9-8f67aaebe4a0.png)

Email does not match username|  Lecture does not exist
:-------------------------:|:-------------------------:
![image](https://user-images.githubusercontent.com/25953723/170867571-637f98a6-4363-4d8d-b960-9aee736e5dcd.png) | ![image](https://user-images.githubusercontent.com/25953723/170867656-e1c10321-9fd8-4203-8097-ab1474ee4999.png)

User already registered to lecture|  User registered to lecture at this time
:-------------------------:|:-------------------------:
![image](https://user-images.githubusercontent.com/25953723/170867717-522d1f90-bdb2-47bf-8e87-b2727f4b84b8.png) | ![image](https://user-images.githubusercontent.com/25953723/170867758-9d71e180-4870-4b1a-805a-07130c9bb44e.png)

Lecture is full |  Saving email in file
:-------------------------:|:-------------------------:
![image](https://user-images.githubusercontent.com/25953723/170867929-2169670e-7d57-4410-beae-28e3d591c2f3.png) | ![image](https://user-images.githubusercontent.com/25953723/170868157-ddc37dc6-8847-4653-955b-be0333a7e7cf.png)










