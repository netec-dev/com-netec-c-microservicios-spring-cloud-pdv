
# servicio-de-descubrimiento
cmd /c start powershell -noexit -command "java -jar .\servicio-de-descubrimiento\target\servicio-de-descubrimiento-0.0.0.1-SNAPSHOT.jar"
Start-Sleep -s 5

# servicio-de-gateway
cmd /c start powershell -noexit -command "java -jar .\servicio-de-gateway\target\servicio-de-gateway-0.0.0.1-SNAPSHOT.jar"
Start-Sleep -s 5

# servicio-de-clientes zona1
cmd /c start powershell -noexit -command "java -D'spring.profiles.active=zona1' -jar .\servicio-de-clientes\target\servicio-de-clientes-0.0.0.1-SNAPSHOT.jar"
Start-Sleep -s 5
# servicio-de-clientes zona2
cmd /c start powershell -noexit -command "java -D'spring.profiles.active=zona2' -jar .\servicio-de-clientes\target\servicio-de-clientes-0.0.0.1-SNAPSHOT.jar"
Start-Sleep -s 5

# servicio-de-cuentas zona1-9020
cmd /c start powershell -noexit -command "java -D'spring.profiles.active=zona1' -jar .\servicio-de-cuentas\target\servicio-de-cuentas-0.0.0.1-SNAPSHOT.jar"
Start-Sleep -s 5
# servicio-de-cuentas zona2-9021
cmd /c start powershell -noexit -command "java -D'spring.profiles.active=zona2' -jar .\servicio-de-cuentas\target\servicio-de-cuentas-0.0.0.1-SNAPSHOT.jar"
Start-Sleep -s 5

# servicio-de-pedidos zona1-9010
cmd /c start powershell -noexit -command "java -D'spring.profiles.active=zona1' -jar .\servicio-de-pedidos\target\servicio-de-pedidos-0.0.0.1-SNAPSHOT.jar"
Start-Sleep -s 5
# servicio-de-pedidos zona2-9011
cmd /c start powershell -noexit -command "java -D'spring.profiles.active=zona2' -jar .\servicio-de-pedidos\target\servicio-de-pedidos-0.0.0.1-SNAPSHOT.jar"
Start-Sleep -s 5

# servicio-de-productos  zona1-9040
cmd /c start powershell -noexit -command "java -D'spring.profiles.active=zona1' -jar .\servicio-de-productos\target\servicio-de-productos-0.0.0.1-SNAPSHOT.jar"
Start-Sleep -s 5
# servicio-de-productos  zona2-9041
cmd /c start powershell -noexit -command "java -D'spring.profiles.active=zona2' -jar .\servicio-de-productos\target\servicio-de-productos-0.0.0.1-SNAPSHOT.jar"
Start-Sleep -s 5
