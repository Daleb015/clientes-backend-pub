![Build Status](https://travis-ci.org/ExampleDriven/swagger-java-spring-example.svg)

# Clientes-backend

Este es un proyecto de Backend en el lenguaje Java, haciendo uso del framework de Spring, él mismo contiene una api Restfull que permite la gestión de usuarios, clientes, productos, facturas por medio de servicios protegidos a través de jwt y roles asignados a los usuarios, la persistencia se hace con la base de datos no relaciona Mongodb a través del uso de Spring Data Mongodb, la arquitectura usada es la de base de el framework, Onion.


# Tecnologías usadas

- Java bean validation (Validación de campos programática).
- Java 11
- Spring Data Mongo ( Conexión a base administrada en Mongo atlas, documentos embebidos y relacionados por DBREF de mongo, Patrón Repository para acceso a datos, paginación, persistencia de archivos multimedia.).
- Spring Security - Authorization Server (entrega jwt por usuario y rol, encryptacion por medio de Bcrypt).
- Spring Security - Resource Server (Protección de los endpoints con CORS y JWT por medio de anotaciones y roles).
- Spring Core (Ioc, dependency injection para separar las capas del modelo onion, externalización de la configuración con Spring profile)
- Spring boot starter web (servidor de aplicaciones embebido tomcat que contiene el contexto de la aplicación y sus beans.)  

## Despliegue

El proyecto usa maven para la construcción y gestión de dependencias, para ello hace uso del plugin de maven con Spring por lo que puede ser desplegado por medio de:

- mvn spring-boot:run 
- mvn clean package install (java -jar dentro del directorio target pasando como parametros el jar generado)
- Con un archivo dockerfile:

```
FROM adoptopenjdk/openjdk11:latest
```
ARG JAR_FILE=target/*.jar
```
COPY ${JAR_FILE} app.jar
```
ENTRYPOINT ["java","-jar","/app.jar"]


La configuración en el archivo de propiedades toma las variables de entorno en primera instancia, por lo que pueden ser seteadas dentro del contenedor o pasadas como parametros en en un pod de kubernetes, también se pueden agregar como parámetros en -D[valor] para que sean seteados directo en el archivo de propiedades, otra opción es usar Spring boot actuator y modificarlas por los endpoints, sin embargo este proyecto no hace uso de actuator.


- Los ejemplos de consumo de los endpoints se encuentran en el siguiente link de postman:
https://www.getpostman.com/collections/c5b383b8a6f864d0f2bd

Los servicios se consumen haciendo uso de un token jwt que se genera en el servicio de Generar token jwt, el servicio tiene ejemplos dentro del anterior link.

Dicho servicio permite generar un token jwt, las credenciales para un usuario tipo administrador son admin:12345 o para uno de tipo Usuario son daniel:12345, el tipo de token limita las funcionalidades que puede realizar en los endpoints, ya que estos están protegidas de manera granular por el framework de spring security, lo que limita lo que se puede hacer en el backend y lo que se puede ver en el frontend.


El servicio actualmente queda desplegado en la nube de Heroku, al ser desplegado en el tier Free la aplicación se apagará y se desasignará los recursos usados cuando no se esté usando por cierto periodo de tiempo, esto implica que la foto guardada se deberá volver a cargar en el caso en que no aparecza y se debe a que el contenedor es de tipo stateless, debido al tipo de tier
