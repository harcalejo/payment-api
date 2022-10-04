# **Payment API**

Payment API hace parte del proyecto challenge de prestamos, parte de las responsabilidades de Payment tiene que ver
con registro y la consulta de pagos relacionados a los prestamos.

<br/>

# Solucion
![Payment HLD](resources/PaymentAPIHLD.png)

Para la solucion se plantea un modelo multi-capa donde separamos las responsabilidades de los componentes asociados a cada una de ellas.

## Controller
En esta capa ubicamos los componentes que exponen las capcidades de nuestra API Restfull al cliente. Para esta implementacion usamos API Restfull.

## Service
En esta capa ubicamos la logica especializada de negocio, de esta forma mantenemos esta logica oculta del cliente y nos ayuda a generar desacoplamiento con la capa de presentacion(Controller).

## Domain
En esta capa ubicamos los componentes relacionados al dominio de datos y la interaccion con la base de datos para soportar la logica de negocio.

# Tools
* ## Java version 11
* ## Framework Spring Boot
    * ### spring-boot-starter-web
    * ### spring-boot-starter-data-jpa
    * ### lombok
    * ### spring-boot-starter-test

El framework principal que usamos para desarrollar la solucion es SpringBoot y nos apoyamos de las librerias listadas
anteriormente.
Cada una de ellas soportando las diferentes capas y requerimientos del challenge, entre ellas tenemos el starter-web,
que uno de sus principales aportes es soportar la capa controller para la exposicion de las API Restfull. Por otro lado
tenemos el starter-data-jpa que nos permite interactuar con mas facilidad con la base de datos y nos brinda notacion y
consultas prediseñadas para reducir la complejidad en la implementacion.
Lombok por su parte es una libreria que nos ayuda con el clean code, reduciendo la cantidad de lineas de codigo
repetitivas que escribimos en algunos componentes. Por ultimo pero no menos importante, starter-test nos aporta las
librerias de junit y mockito para la implementacion de las pruebas unitarias realizadas.

# Features
## Registro de Pagos realizados
Permite registrar un pago asociado a un prestamo, pero el pago no puede superar el valor total de la deuda actual.

#### /payments/loan/{loanId}
###### Request body
```json
{
  "amount": 1000.00
}
```

###### Response body
```json
{
  "id": 402,
  "loanId": 1,
  "debt": 803104.02
}
```

## Listado de Pagos
Permite consultar todos los pagos asociados a un prestamo

#### GET /payments/loan/{loanId}

###### Response body
```json
[
  {
    "id": 220,
    "loanId": 1,
    "amount": 3352.24,
    "registerDate": "2022-05-05"
  },
  {
    "id": 385,
    "loanId": 1,
    "amount": 8885.07,
    "registerDate": "2021-12-19"
  }
]
```

#### GET /payments/loan/{loanId}?before=2022-04-01
El parametro before complementa esta busqueda para limitar los pagos realizados antes de la fecha dada.
###### Response body
```json
[
  {
    "id": 385,
    "loanId": 1,
    "amount": 8885.07,
    "registerDate": "2021-12-19"
  }
]
```