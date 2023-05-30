# Proyecto Prueba Empleados
Para poder visualizar el swagger del proyecto acceder al siguiente link:

http://localhost:8080/api/v1/q/swagger-ui/#/

## Levantar proyecto
Es necesario tener instalado docker para poder levantar la base de datos.
Ubicarse dentro de la carpeta de este proyecto
```shell script
cd dockerfiles
docker build -t basedatos .
docker run -d --name postdb -p 5432:5432 basedatos
```
Esto levantará un contenedor donde se alojará la base de datos.

Para levantar el proyecto se recomienda realizar los siguientes pasos
```shell script
mvn clean
mvn compile quarkus:dev
```

## Empaquetado de aplicativo

Realizando la siguiente acción se creará el empaquetado
```shell script
mvn package
```
produce un archivo `quarkus-run.jar` ubicado en `target/quarkus-app/` directory.

y la aplicación se puede levantar desde `java -jar target/quarkus-app/quarkus-run.jar`.


