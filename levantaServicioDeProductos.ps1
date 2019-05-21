$psise.CurrentPowerShellTab.DisplayName = 'servicio-de-productos-9041'
$psise.CurrentPowerShellTab.DisplayName = 'servicio-de-productos-9042'
cd C:\_dev\_code\sts_netec_microservicios\com-netec-c-microservicios-spring-cloud-ribbon
java -D"server.port=9041" -jar .\servicio-de-productos\target\servicio-de-productos-0.0.0.1-SNAPSHOT.jar
java -D"server.port=9042" -jar .\servicio-de-productos\target\servicio-de-productos-0.0.0.1-SNAPSHOT.jar
