# ApartmentsServer

Este proyecto tiene como fin demostrar la conectividad entre un servidor y N clientes, utilizando JavaSockets. De modo que el servidor, en este caso, es la abstracción de una portería de un edificio, mientras que los clientes representan apartamentos de dicho edificio. Dentro del contexto establecido de un edificio, el servidor provee los servicios de:  

* **Anuncio de visita y autorización apertura de puerta:**  
Cuando el portero recibe a un visitante en la portería, digitará el nombre del visitante en la terminal, y especificará el
apartamento al que se dirige el visitante. En ese momento debe sonar un timbre en la
terminal del apartamento, y se mostrará el nombre del visitante en pantalla. El
residente del apartamento dispondrá de dos botones marcados “Admitir” y “Denegar”.
Dependiendo del botón que se oprima, el portero recibirá un mensaje indicando si
puede dejar pasar o no al visitante.

* **Conversación entre apartamentos:**  
El servidor provee un servicio de mensajería entre los apartamentos.

* **Envío de correo electrónico:**  
El servidor provee un servicio de envío de correo electrónico a un usuario previamente establecido.

## Dificultades:

Las principales dificultades que se nos presentaron radicaron en la poca información que teníamos sobre el funcionamiento de Java Sockets. Si bien contabamos con los conocimientos teóricos, no teníamos la instrucción suficiente para desarrollar un programa usando Java Sockets.

## Conclusiones:

Como conclusión de este proyecto, podemos decir que el desarrollo de servidores es importante y tiene un gran impacto en actividades cotidianas, como bien lo pudimos comprobar al desarrollar un sistema de un edificio funcional y agradecemos que tengamos conocimientos adquiridos a lo largo del curso para este fin.
