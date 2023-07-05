### La interface

La aplicación cuenta con 14 endpoints, distribuidos en 3 controllers: PartidaController que se encarga de la generación, consultas, altas, modificaciones y bajas de partidas, JugadorController que se encarga de las consultas, altas, modificaciones y bajas de jugadores y JuegoController que se encarga de la jugabilidad.

##### PartidaController

* "/partida/get-nueva" se encarga de generar nuevas partidas usando el core de la app recibiendo por parámetro los id de los 2 jugadores y del nombre de la partida y pone en curso el juego.
* "/partida/get-bbdd/{id}" se encarga de devolver una partida que se encuentre guardada en la base de datos y pone en curso el juego.
* "/partida/get-all" se encarga de devolver la lista completa de las partidas que se encuentran en la base de datos.
* "/partida/save" se encarga de guardar las partidas en la base de datos, usando la partida en curso.
* “/partida/update” se encarga de actualizar las partidas en la base de datos, no es necesario pasarle un id, porque trabaja con la partida que está en curso.
* "/partida/delete/{id}" se encarga de eliminar las partidas de la base de datos, no es posible eliminar partidas en curso.

##### JugadorController

* "/jugador/get/{id}" se encarga de devolver un jugador que esté guardado en la base de datos.
* "/jugador/get-all" se encarga de devolver la lista completa de los jugadores que estén guardados en la base de datos.
* "/jugador/save" se encarga de guardar los jugadores en la base de datos.
* "/jugador/update/{id}" se encarga de actualizar los jugadores en la base de datos.
* "/delete/{id}" se encarga de eliminar los jugadores de la base de datos. No es posible eliminar jugadores que se encuentren en alguna partida.
 
##### JuegoController

* “/juego/get-touch/{posicionStr}” se encarga de gestionar los movimientos de las piezas, recibiendo una coordenada algebraica y devolviendo una clase con la información pertinente para que el frontend sepa qué hacer con el casillero y/o la pieza a la que se le hizo click.
* "/juego/pedido-empate" se encarga de detener el juego quedando a la espera de la respuesta del contrincante.
* "/juego/respuesta-empate/{respuesta}" se encarga de reanudar el juego en caso de que la respuesta sea “false” y de terminar el juego en empate en caso de que la respuesta sea “true”

---

### La lógica del Juego

El core del juego funciona con una matriz bidimensional de enteros de 10 x 10, debido a que se le inserta un borde, escribiendo números 1 en la primera y última fila y en la primera y última columna. Las piezas, están representadas por 2 números, con la siguiente codificación: el primer número es el color, 2 para blancas y 3 para negras; y el segundo número es la pieza, 4 para el Peón, 5 Para la Torre, 6 para el Caballo, 7 para el Alfil, 8 para la Dama y 9 para el Rey.

El método más importante es el clickCasillero() en la clase Juego que es la que nuclea a las demás clases del core. Este método, recibe una posición por parámetro para comprobar: si el casillero de la posición está vacío, si hay una pieza (codificada) del turno contrario o si hay una pieza (codificada) del turno, en este último caso, guarda el número en una variable como pieza anterior y selecciona la pieza (clase) correspondiente, para determinar los movimientos posibles, agregándole 100 (movimiento posible) a cada casillero a donde esa pieza puede moverse, inclusive a las piezas contrarias que puede capturar, por último la pieza (clase) devuelve una lista de esos movimientos posibles. Al ejecutarse de nuevo, debido al envío de otra posición, se determina si la pieza es del mismo turno, si lo es, resta los 100 de los movimientos posibles (utilizando la lista de movimientos posibles) de la pieza anterior y agrega los 100 de la pieza actual. En cambio si en el casillero de la segunda posición enviada hay un número mayor o igual a 100, realiza el movimiento y/o la captura en caso de haber una pieza contraria, para comprobar si expone al jaque a su propio rey. Si es así, el movimiento es ilegal y se vuelve atrás, hasta que se envíe una posición en la que se pueda hacer un movimiento posible y legal. Cuando se hace un movimiento posible y legal, resta los 100 de los movimientos posibles, y comprueba si pone en jaque al rey contrario de la pieza que hizo el movimiento, si no, sigue el juego en estado en curso y si lo pone en jaque, comprueba si es jaque mate, si no lo es, sigue el juego en estado de jaque y si lo es finaliza.

La clase Jaque tiene el método verJaque() que buscando desde el punto de vista del rey, ya sea del de turno o el otro, todas las amenazas posibles y devuelve una lista de amenazas, si la lista queda vacía, no hay amenazas.

La clase JaqueMate tiene el método verJaqueMate() que revisa 3 posibilidades, perímetro si el rey se puede escapar, luego si alguna de las piezas de del rey amenazado pude capturar a la amenaza y por último si alguna de las piezas puede tapar a la amenaza, en cualquiera de los casos devuelve “verdadero” si se pone a salvo y “falso” sí no.

##### Piezas

2 = Blancas
3 = Negras

4 = Peón
5 = Torre
6 = Caballo
7 = Alfil
8 = Dama
9 = Rey

ej: 25 es una Torre blanca, 38 es una Dama negra

##### Otros valores

1 = Borde
100 = Casillero posible al que se puede mover la pieza
ej: 135 = Casillero posible, más captura de la pieza que se encuentra ahí

---

### La Jugabilidad

Para generar una nueva partida deben existir, en la base de datos, al menos 2 jugadores. Una vez creada la partida es posible comenzar a jugar, si no, no. Al primer turno lo tienen las blancas. Para seleccionar una pieza se debe enviar la coordenada de notación algebraica del casillero donde está la pieza y luego la coordenada del casillero a donde se quiere mover. 

La API responderá a cada una de los envíos de coordenadas con un objeto que tiene la siguiente información: las acciones, el turno, la pieza seleccionada o el movimiento en notación algebraica y el estado del juego.

Las Acciones: antes de seleccionar una pieza, si se envía una coordenada de un casillero con una pieza del turno la acción será 1, con una coordenada de un casillero vacío la acción será 6 y con una coordenada de una pieza contraria la acción será 7. Luego de seleccionada una pieza del turno y al enviar una coordenada de un movimiento posible y legal la acción será 2 y con una coordenada de un movimiento imposible o ilegal la acción será 3. Cuando hay un pedido de empate, no hay ninguna acción posible, hasta que no haya una respuesta, esto se codifica como 0.
El Turno: este atributo indica si el turno es de las blancas enviando un 2 o de las negras enviando un 3.

La Notación Algebraica: en el caso de que no haya pieza seleccionada, esta queda como una cadena vacía, una vez seleccionada una pieza, se indica la pieza junto al casillero en la que se encuentra y una vez que se realiza el movimiento, pasa a contener la notación algebraica del mismo.

El Estado: en el momento que se genera el juego el estado se presenta como estado en curso, con un 10, si se produce un jaque este cambia a 40, en caso de haber un jaque mate el estado pasa a 50 y finaliza el juego. Si un jugador pide el empate el estado se pone en pausa y se codifica como 90, en este estado no se puede hacer ningún movimiento, hasta que no haya una respuesta; si la respuesta es “falsa” el estado vuelve a estado en curso y si la respuesta es “verdadera” se produce el estado de empate, codificado como 11 e inmediatamente produce el estado finalizado con el 99 al igual que en el jaque mate.

##### Acciones

0 = Ninguna acción
1 = Pieza seleccionada
2 = Movimiento permitido
3 = Movimiento prohibido
6 = No es posible seleccionar, casillero vacío
7 = No es posible seleccionar, pieza del turno contrario

##### Turnos

2 = Turno de las Blancas
3 = Turno de las Negras

##### Notación Algebraica

P =  Peón
T = Torre
C = Caballo
A = Alfil
D = Dama
R = Rey
a - h = Columnas del tablero
1 - 8 = Filas del tablero
x = Captura
\+ = Jaque
\# = Jaque Mate

ej pieza: “Cg1” el Caballo que se encuentra en la casilla g1
ej movimiento: “Pc7 c6” Peón en c7 que se mueve a c6
ej captura: “Dd6 x Ph2” Dama en d6 que Captura a Peón en h2
ej jaque mate: “Th5 e5 #” Torre en h5 que se mueve a e5 y produce el Jaque Mate

##### Estados

10 = Juego en curso
11 = Empate => Fin del juego
40 = Jaque
50 = Jaque mate => Fin del juego
90 = Juego detenido
99 = Juego finalizado

---

### El Funcionamiento

La API está configurada para trabajar con H2 como base de datos, también está la configuración (comentada) para Microsoft SQL Server.

En cuanto al uso, es posible realizar todas las acciones accediendo desde la API pero no del frontend debido a que no está todo implementado en este último. Con el frontend es posible iniciar una nueva partida (hardcodeada) para probar el core del juego, pero no se pueden consultar, guardar, modificar y eliminar partidas y/o jugadores, ni armar partidas nuevas eligiendo los jugadores, ni usar una partida guardada en la base de datos, ni realizar pedido y respuesta de empadre.

Luego de iniciada la aplicación y para poder operar con Swagger, se debe ingresar en el navegador: “http://localhost:8080/swagger-ui.html”.

Es recomendable descargar el proyecto en la raíz del disco (C:\), para no tener problemas con las rutas de las imágenes del frontend.
