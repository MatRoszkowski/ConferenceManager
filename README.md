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
Project uses testing api tool SpringDoc OpenApi UI - Swagger. After running it you can see possible endpoints of the application. Just type address below in you browser:
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




