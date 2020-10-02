FROM openjdk:8

WORKDIR /login/bin

ENV PORT 6000
ENV TZ America/Bogota

COPY /target/classes /login/bin/classes
COPY /target/dependency /login/bin/dependency
RUN mkdir keystores
COPY keystores/* /login/bin/keystores/

CMD ["java","-cp","./classes:./dependency/*","edu.escuelaing.arep.SparkWebApp"]