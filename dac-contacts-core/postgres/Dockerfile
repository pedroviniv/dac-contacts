FROM postgres
MAINTAINER Pedro Arthur <pfernandesvasconcelos@gmail.com>
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD 12345
ENV POSTGRES_DB dac-contacts
COPY create.sql /docker-entrypoint-initdb.d/
COPY data.sql /docker-entrypoint-initdb.d/