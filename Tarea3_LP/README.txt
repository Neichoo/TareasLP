Nombre: Ignacio Gómez González
Paralelo: 200

Para lograr una exitosa ejecución del programa, se debe contar con javac 11, luego en la terminal uno se debe dirigir al
directorio en donde guardó el archivo, se debe descomprimir y después se deben tener en cuenta lo siguientes comandos:
"make juego": Este comando compilará todos los archivos para que pueda funcionar correctamente.
"make run": Este comando iniciará el juego. (Debe realizarse el comando anterior primero)
"make remove": Este comando eliminará el juego de su computadora. (Debe haber un juego creado)

El juego consiste en recolectar todas las piezas de la nave antes de completar 30 turnos, una vez recolectadas todas
las piezas, el juego termina satisfactoriamente, si por el contrario no se encuentran las piezas en 30 turnos, se pierde.
También se pierde si se mueren todos los Pikinims antes de encontrar las piezas y antes de acabar los 30 turnos.
Intenté hacer lo mejor que pude en el ámbito de la creatividad, introduciendo diálogos en algunas partes, que pueden ir
cambiando dependiendo de las acciones que se tomen en la partida.

Consideraciones:
- Inputs siempre correctos: Tal como se conversó en el foro, se asume que los inputs ingresados siempre serán correctos,
por lo que se deben respetar las frases como "No te puedes mover a (dirección), la muralla no está destruida!".
- Murallas: Si uno viene desde la derecha y se encuentra con una muralla, no puede seguir hacia la derecha porque está
la muralla bloqueando el camino, pero si podría devolverse por donde vino, ya que ahí no hay obstáculo. La misma lógica
aplica para el otro sentido.
- Ataques de Enemigo: Se considera que si un enemigo ataca a los Pikinim, pero el random selecciona una especie de
Pikinim que ya no tiene "cantidad", entonces debe redirigir el ataque a otra especie ya que no tendría sentido atacar
algo que no existe.
- Estrategias del usuario: No sería recomendable elegir multiplicar una cantidad 0, ya que siempre dará 0, pero lo dejo
disponible por si ayuda a testear el caso en el que se pierde por falta de Pikinims.
