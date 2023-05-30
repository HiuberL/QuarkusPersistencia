# Utiliza una imagen base de PostgreSQL
FROM postgres:latest

# Variables de entorno para configurar la base de datos
ENV POSTGRES_USER=admin
ENV POSTGRES_PASSWORD=admin

# Copia un script SQL para inicializar la base de datos
COPY init.sql /docker-entrypoint-initdb.d/

# Exponer el puerto por defecto de PostgreSQL (5432)
EXPOSE 5432