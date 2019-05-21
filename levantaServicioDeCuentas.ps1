cd C:\_dev\_code\sts_netec_microservicios\com-netec-c-microservicios-spring-cloud-ribbon
java -DPORT=9020 -jar .\servicio-de-cuentas\target\servicio-de-cuentas-0.0.0.1-SNAPSHOT.jar
java -D"server.port=9021" -jar .\servicio-de-cuentas\target\servicio-de-cuentas-0.0.0.1-SNAPSHOT.jar
