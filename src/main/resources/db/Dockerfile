FROM galam/oracle-xe-12c:latest

WORKDIR /scripts/
COPY script.sql ./

EXPOSE 1521

# Autor
LABEL maintainer="Jorge Gomez"

RUN mkdir -p /docker-entrypoint-initdb.d

ADD script.sql /docker-entrypoint-initdb.d/
