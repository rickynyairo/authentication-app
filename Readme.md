## Backend API with authentication

### Built using Java Spring Boot

### Postman Collection

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/4a5464c0bb1cea5cd6b1)

#### API URL

[Link to api](https://tracom-spring-api.herokuapp.com)

### Endpoints

`POST: /api/auth/signup`
```
payload = {
    name : 'string',
    username : 'string',
    email : 'string',
    password : 'string'
}

response [201] = {
    success: true,
    message: "User created successfully"
} 

response [400] = {
    success: false,
    message: "Username is already taken"
} 
```

`POST: /api/auth/signin`
```
payload = {
    usernameOrEmail: 'string',
    password : 'string'
}

response [200] = {
    success: true,
    message: "User loggged in successfully"
} 

response [401] = {
    success: false,
    message: "Bad credentials"
} 
```