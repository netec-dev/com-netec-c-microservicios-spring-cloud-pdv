$psise.CurrentPowerShellTab.DisplayName = 'servicio-de-cuentas-9021'
$psise.CurrentPowerShellTab.DisplayName = 'servicio-de-cuentas-9022'
$psise.CurrentPowerShellTab.DisplayName = 'servicio-de-cuentas-9023'
cd C:\_dev\_code\sts_netec_microservicios\com-netec-c-microservicios-spring-cloud-ribbon

java -DPORT=9021 -jar .\servicio-de-cuentas\target\servicio-de-cuentas-0.0.0.1-SNAPSHOT.jar

java -D"server.port=9022" -jar .\servicio-de-cuentas\target\servicio-de-cuentas-0.0.0.1-SNAPSHOT.jar

java -D"server.port=9023" -jar .\servicio-de-cuentas\target\servicio-de-cuentas-0.0.0.1-SNAPSHOT.jar
