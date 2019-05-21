

# servicio-de-clientes
cmd /c start powershell -noexit -command "java -jar .\servicio-de-clientes\target\servicio-de-clientes-0.0.0.1-SNAPSHOT.jar"
# servicio-de-cuentas 9020
cmd /c start powershell -noexit -command "java -DPORT=9020 -jar .\servicio-de-cuentas\target\servicio-de-cuentas-0.0.0.1-SNAPSHOT.jar"
# servicio-de-cuentas 9021
cmd /c start powershell -noexit -command "java -DPORT=9021 -jar .\servicio-de-cuentas\target\servicio-de-cuentas-0.0.0.1-SNAPSHOT.jar"
# servicio-de-cuentas 9022
cmd /c start powershell -noexit -command "java -DPORT=9022 -jar .\servicio-de-cuentas\target\servicio-de-cuentas-0.0.0.1-SNAPSHOT.jar"
# servicio-de-pedidos 9010
cmd /c start powershell -noexit -command "java -jar .\servicio-de-pedidos\target\servicio-de-pedidos-0.0.0.1-SNAPSHOT.jar"
# servicio-de-productos  9040
cmd /c start powershell -noexit -command "java -D'server.port=9040' -jar .\servicio-de-productos\target\servicio-de-productos-0.0.0.1-SNAPSHOT.jar"
# servicio-de-productos  9041
cmd /c start powershell -noexit -command "java -D'server.port=9041' -jar .\servicio-de-productos\target\servicio-de-productos-0.0.0.1-SNAPSHOT.jar"



