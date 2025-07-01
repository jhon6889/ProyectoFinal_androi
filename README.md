
## Project Aplication Androi

```bash
🔹 Clone the repository
🔹 insert the project in androi our visual studio
🔹 Run MainActivity
```
## Project Api

```bash
🔹 Clone the repository
🔹 insert the project in androi our visual studio
🔹 Run MainActivity
$ npm install @nestjs/typeorm typeorm sqlite3
```


## Compile and run the project

```bash
# development
$ npm run start

# watch mode
$ npm run start:dev

# production mode
$ npm run start:prod
```

## test on postman
```
🔹 run start:dev
🔹 Verás el mensaje: ✅ API disponible en: http://localhost:3000.

🔹 Abre Postman (you need have postman install).

🔹 Paso 2: Crear un usuario (POST)
🔹 clic en "New Request".

🔹 Configura: Método: POST

🔹 URL: http://localhost:3000/user

🔹 Headers:  Key: Content-Type | Value: application/json

🔹 Body (selecciona "raw" y tipo "JSON"):

🔹json
{
    "name": "Usuario de prueba",
    "email": "test@example.com",
    "password": "123456",
    "age": 25
}

🔹 Haz clic en Send.
🔹 Respuesta esperada (200 OK):

```

## Run tests

```bash
# unit tests
$ npm run test

# e2e tests
$ npm run test:e2e

# test coverage
$ npm run test:cov
```
