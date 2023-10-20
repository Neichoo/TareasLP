import java.util.Scanner;
public class Juego {

    /*
     *** Parámetros:
     * Pikinim[] pikinims: Un arreglo que contiene las 3 especies de Pikinims.
     *** Retorno:
     * No retorna nada, debido a que es una función de tipo void.
     *** private static void estados_pikinims(Pikinim[] pikinims):
     * Es una función que imprime el estado de todos los Pikimins, recorre el arreglo de pikimins, revisa los atributos
     * de cada especie y se los imprime al usuario.
     */
    private static void estados_pikinims(Pikinim[] pikinims){
        System.out.println("Acá tienes el estado del equipo de Pikinims!\n");
        String color_pikinim = "";
        int contador = 0;
        int ataque_pikinims;
        int capacidad_pikinims;
        int cantidad_pikinims;
        for(Pikinim un_pikinim : pikinims){
            ataque_pikinims = un_pikinim.getAtaque();
            capacidad_pikinims = un_pikinim.getCapacidad();
            cantidad_pikinims = un_pikinim.getCantidad();
            switch(contador){
                case 0:
                    color_pikinim = "cyans";
                    break;
                case 1:
                    color_pikinim = "magentas";
                    break;
                case 2:
                    color_pikinim = "amarillos";
                    break;
            }
            System.out.println(">> Pikinims "+color_pikinim+" << :\n*Ataque "+color_pikinim+": "+ataque_pikinims +"; *Capacidad "+color_pikinim+": "+capacidad_pikinims+"; *Cantidad "+color_pikinim+": "+cantidad_pikinims);
            contador += 1;
        }
    }

    /*
     *** Parámetros:
     * String[] args: Es la firma para poder entrar en ejecución.
     *** Retorno:
     * No retorna nada, debido a que es una función de tipo void.
     *** public static void main(String[] args):
     * Este es el main, a grandes rasgos, es el núcleo del código, necesario para poder crear el juego.
     */
    public static void main(String[] args){
        //Se crea el arreglo de Pikinims, introduciendo las 3 especies.
        Pikinim[] pikinims = new Pikinim[3];
        Cyan cyan = new Cyan();
        Magenta magenta = new Magenta();
        Amarillo amarillo = new Amarillo();
        pikinims[0] = cyan;
        pikinims[1] = magenta;
        pikinims[2] = amarillo;
        //Se crea el arreglo de zonas, introduciendo todas las zonas correspondientes al mapa con sus respectivos parámetros.
        //Se crea un arreglo de strings, para obtener qué tipo de zona es, prefiero esto a getClass() porque lo encuentro más rápido.
        Zona[] mapa = new Zona[11];
        String[] nombre_zona = new String[11];
        Pieza pieza1 = new Pieza(50);
        Enemigo enemigo1 = new Enemigo(130, 20, 25);
        Enemigo enemigo2 = new Enemigo(50, 10, 15);
        Pildora pildora1 = new Pildora(25);
        Muralla muralla1 = new Muralla(50);
        Pieza pieza2 = new Pieza(100);
        Enemigo enemigo3 = new Enemigo(45, 8, 10);
        Pieza pieza3 = new Pieza(35);
        Pildora pildora2 = new Pildora(15);
        Enemigo enemigo4 = new Enemigo(75, 15, 20);
        Muralla muralla2 = new Muralla(150);
        mapa[0] = pieza1;
        nombre_zona[0] = "pieza";
        mapa[1] = enemigo1;
        nombre_zona[1] = "enemigo";
        mapa[2] = enemigo2;
        nombre_zona[2] = "enemigo";
        mapa[3] = pildora1;
        nombre_zona[3] = "pildora";
        mapa[4] = muralla1;
        nombre_zona[4] = "muralla";
        mapa[5] = pieza2;
        nombre_zona[5] = "pieza";
        mapa[6] = enemigo3;
        nombre_zona[6] = "enemigo";
        mapa[7] = pieza3;
        nombre_zona[7] = "pieza";
        mapa[8] = pildora2;
        nombre_zona[8] = "pildora";
        mapa[9] = enemigo4;
        nombre_zona[9] = "enemigo";
        mapa[10] = muralla2;
        nombre_zona[10] = "muralla";
        //Se declaran las variables que se necesitarán para la lógica del juego.
        boolean muralla_destruida;
        boolean todas_las_piezas = false;
        String direccion = "";
        int piezas_totales = 3;
        int piezas_levantadas = 0;
        int input;
        int ultimo_input;
        int turno = 1;
        int pos = 5;
        int pos_posibled = 6;
        int pos_posiblei = 4;
        Scanner scanner = new Scanner(System.in);
        //Historia del juego, se explica cómo llegó al planeta y que necesita hacer de ahora en adelante.
        System.out.println("\n*Estabas dando un viaje de rutina y tu nave empieza a tener fallos técnicos, chocas con un asteroide que mide 10 veces el tamaño de tu nave*");
        System.out.println("Tú: Ahh... que ha pasado? Donde estoy? Oh no!, mi nave se ha dañado, pero parece que no es un daño total.\n*Revisando* Creo que si encuentro las partes que se desacoplaron de la nave podré utilizarla de nuevo (Presiona Enter para continuar...)");
        scanner.nextLine();
        System.out.println("*Escuchas sonidos detrás tuyo y volteas*\nTú: Hey! Quién anda ahí? Hay alguien que pueda ayudarme?");
        System.out.println("*Sientes un golpe en el tobillo*\nTú: Woah! Que susto, eh... (Presiona Enter para continuar...)");
        scanner.nextLine();
        System.out.println("Pero qué son ustedes? Me pueden entender?\nUn pikinim de color cyan te entrega unos auriculares (Presiona Enter para continuar...)");
        scanner.nextLine();
        System.out.println("Tú: Y estos auriculares?\n*Procedes a colocartelos*\n*Escuchas murmullos*\nPikinim cyan: Puedes escucharnos? Finalmente luego de mucho tiempo tenemos visitantes, que alegría!\nEste dispositvo sirve para que puedas entender y hablar nuestra lengua (Presiona Enter para continuar...)");
        scanner.nextLine();
        System.out.println("Tú: Wow, la verdad no he venido de visita, se me ha dañado la nave y ahora tengo que encontrar las piezas de mi nave.\n*Revisa la capacidad del tanque de oxígeno*\nTú: Ugh, solo me quedan alrededor de 30 horas para poder encontrarlas, qué haré ahora! (Presiona Enter para continuar...)");
        scanner.nextLine();
        System.out.println("Pikinim Magenta: Nosotros podemos ayudarte!, será todo un placer, también te entregaremos esta tablet por si quieres revisar nuestras estadísticas.\nLos amarillos fabricaron esta tableta e implementaron el mapa acá dentro, una locura no? (Presiona Enter para continuar...)");
        scanner.nextLine();
        System.out.println("*Tomas la tableta*\nTú: Bien, pues en marcha, no hay tiempo que perder!\n*Mientras tanto los Piknim amarillos te explican las zonas que hay en el planeta graficado en la tableta, puesto que ellos se dedican a mantener un registro geográfico del planeta.");
        System.out.println("Pikinim Amarillo: Ten en cuenta que cada zona nos tomará una hora, entre el viaje y la investigación.\nLos cyans irán informando el estado del equipo entero, así que no te preocupes de eso. (Presiona Enter para continuar...)\n");
        scanner.nextLine();
        //Lógica del juego, dentro de un bucle que acabará al terminar el turno 30.
        while(turno < 31){
            //Distintas líneas de texto dependiendo de la situación, para no hacer la experiencia monótona.
            if(pikinims[0].getCantidad() > 0){
                System.out.println("Pikinim Cyan: Cyan reportando situación actual del equipo:");
                estados_pikinims(pikinims);
                System.out.println("\nPikinim Cyan: Mi reporte ha terminado!\n");
            } else if((pikinims[0].getCantidad() == 0) && (pikinims[1].getCantidad() > 0)){
                System.out.println("Pikinim Magenta: Todos los cyans, están abatidos así que yo reportaré la situación actual del equipo:");
                estados_pikinims(pikinims);
                System.out.println("\nPikinim Magenta: Mi reporte ha terminado!\n");
            } else if((pikinims[0].getCantidad() == 0) && (pikinims[1].getCantidad() == 0) && (pikinims[2].getCantidad() > 0)){
                System.out.println("Pikinim Amarillo: Solo quedamos nosotros, los Pikinims amarillos!, acá tienes el informe:");
                estados_pikinims(pikinims);
                System.out.println("\nPikinim Amarillo: Mi reporte ha terminado!\n");
            }
            //Distintas líneas de texto dependiendo de si actualmente se está en una Zona Muralla, distintos casos se evalúan, con distintas opciones.
            if(!nombre_zona[pos].equals("muralla")){
                System.out.println("¿Que deberíamos hacer ahora? (Turno: " + turno + ")\n1.- Quedarse en la zona actual("+ mapa[pos].toString()+")\n2.- Moverse hacia la derecha("+ mapa[pos_posibled].toString()+")\n3.- Moverse hacia la izquierda("+ mapa[pos_posiblei].toString()+")");
            } else {
                muralla_destruida = mapa[pos].getCompletada();
                if (muralla_destruida) {
                    System.out.println("¿Que deberíamos hacer ahora?\n1.- Quedarse en la zona actual(" + mapa[pos].toString() + ")\n2.- Moverse hacia la derecha(" + mapa[pos_posibled].toString() + ")\n3.- Moverse hacia la izquireda(" + mapa[pos_posiblei].toString() + ") ");
                } else {
                    if (direccion.equals("derecha")) {
                        System.out.println("¿Que deberíamos hacer ahora?\n1.- Quedarse en la zona actual(" + mapa[pos].toString() + ")\n2.- No te puedes mover a la derecha, la muralla no está destruida!\n3.- Moverse hacia la izquierda(" + mapa[pos_posiblei].toString() + ")");
                    } else if (direccion.equals("izquierda")) {
                        System.out.println("¿Que deberíamos hacer ahora?\n1.- Quedarse en la zona actual(" + mapa[pos].toString() + ")\n2.- Moverse hacia la derecha(" + mapa[pos_posibled].toString() + ")\n3.- No te puedes mover a la izquierda, la muralla no está destruida!");
                    }
                }
            }
            //Lectura de input y almacenamiento de la dirección.
            input = scanner.nextInt();
            System.out.println("\n");
            ultimo_input = input;
            if(ultimo_input == 2){
                direccion = "derecha";
            } else if (ultimo_input == 3){
                direccion = "izquierda";
            }
            ////Distintas líneas de texto dependiendo de la situación, para no hacer la experiencia monótona.
            if(input == 1){
                System.out.println("*Se quedan en la zona actual*");
                mapa[pos].interactuar(pikinims);
            } else if(input == 2){
                pos += 1;
                pos_posibled = pos + 1;
                pos_posiblei = pos - 1;
                if(pos > 10){
                    pos = 0;
                }
                if(pos_posibled > 10){
                    pos_posibled = 0;
                }
                switch (nombre_zona[pos]){
                    case "pieza":
                        System.out.println("*Caminando hacia la zona de la derecha...*\n*De lejos se ve una pieza de la nave, los pikinims empiezan a saltar de la felicidad!*");
                        mapa[pos].interactuar(pikinims);
                        break;
                    case "enemigo":
                        System.out.println("*Caminando hacia la zona de la derecha...*\n*De la nada salta una sombra desde la oscuridad* *Aparece un enemigo, los Pikinims parecen listos para luchar!*");
                        mapa[pos].interactuar(pikinims);
                        break;
                    case "pildora":
                        System.out.println("*Caminando hacia la zona de la derecha...*\n*Nos encontramos con una píldora, los Pikinims magentas saben lo que es, llevan años investigando estas pildoras, son muy raras de encontrar y hacen que se multiplique la cantidad de Pikinims de una especie! Parece que estamos de suerte*");
                        mapa[pos].interactuar(pikinims);
                        break;
                    case "muralla":
                        System.out.println("*Caminando hacia la zona de la derecha...*\n*Oh no! Hay una muralla bloqueando el paso, que están tratando de hacer los Pikinims? Están uniendo fuerzas, al parecer intentarán destruir la muralla que nos corta el paso!*");
                        mapa[pos].interactuar(pikinims);
                        break;
                }
            } else if(input == 3){
                pos -= 1;
                pos_posibled = pos + 1;
                pos_posiblei = pos - 1;
                if(pos < 0){
                    pos = 10;
                }
                if(pos_posiblei < 0){
                    pos_posiblei = 10;
                }
                turno += 1;
                switch (nombre_zona[pos]){
                    case "pieza":
                        System.out.println("*Caminando hacia la zona de la izquierda...*\n*De lejos vemos una pieza de la nave, los pikinims empiezan a saltar de la felicidad!*");
                        mapa[pos].interactuar(pikinims);
                        break;
                    case "enemigo":
                        System.out.println("*Caminando hacia la zona de la izquierda...*\n*De la nada salta una sombra desde la oscuridad* *Aparece un enemigo, los Pikinims parecen listos para luchar!*");
                        mapa[pos].interactuar(pikinims);
                        break;
                    case "pildora":
                        System.out.println("*Caminando hacia la zona de la izquierda...*\n*Nos encontramos con una píldora, los Pikinims magentas saben lo que es, llevan años investigando estas pildoras, son muy raras de encontrar y hacen que se multiplique la cantidad de Pikinims de una especie! Parece que estamos de suerte*");
                        mapa[pos].interactuar(pikinims);
                        break;
                    case "muralla":
                        System.out.println("*Caminando hacia la zona de la izquierda...*\n*Oh no! Hay una muralla bloqueando el paso, que están tratando de hacer los Pikinims? Están uniendo fuerzas, al parecer intentarán destruir la muralla que nos corta el paso!*");
                        mapa[pos].interactuar(pikinims);
                        break;
                }
            }
            //Se evalúa la condición de victoria, encontrar todas las piezas de la nave, con distintas líneas de texto dependiendo de la situación actual.
            if(nombre_zona[pos].equals("pieza")){
                if(mapa[pos].getCompletada()){
                    piezas_levantadas += 1;
                    if(piezas_levantadas == piezas_totales){
                        if(pikinims[2].getCantidad() > 0){
                            System.out.println("Pikinim Amarillo: Bien hecho! Encontramos todas las piezas, ahora te puedes ir a tu casa");
                        } else if((pikinims[1].getCantidad() == 0) && (pikinims[1].getCantidad() > 0)){
                            System.out.println("Pikinim Magenta: Lo hicimos! Encontramos todas las piezas, ahora te puedes de este planeta");
                        } else if((pikinims[0].getCantidad() == 0) && (pikinims[1].getCantidad() == 0) && (pikinims[2].getCantidad() > 0)) {
                            System.out.println("Pikinim Cyan: Las tenemod todas! No nos falta ni una pieza, es momento de que te vayas y te salves!");
                        }
                        System.out.println("*Le das las gracias a los Pikinims y prometes volver a visitarlos en un futuro*");
                        System.out.println("Fin :), presiona 'Enter' para salir del juego.");
                        scanner.nextLine();
                        break;
                    }
                }
            }
            //Se evalúa una condición de derrota, quedarse sin ningún Pikimin en el equipo, fin del juego.
            if((pikinims[0].getCantidad() == 0) && (pikinims[1].getCantidad() == 0) && (pikinims[2]).getCantidad() == 0){
                System.out.println("Oh no! Por qué los mandé a esas batallas ahora me he quedado completamente solo!");
                System.out.println("*Mueres por no haber cumplido tu objetivo, debido a que no conocías la zona y no podías pasar de ellas por tu cuenta*");
                System.out.println("Game Over, presiona 'Enter' para salir del juego.");
                scanner.nextLine();
                break;
            }
            turno += 1;
        }
        //Se evalúa una condición de derrota, quedarse sin turnos y no haber recolectado todas las piezas, con distintas líneas de texto dependiendo de la situación actual.
        if((turno == 31) && (!todas_las_piezas)){
            System.out.println("*Ya se está por cumplir el plazo de las 30 horas, solo quedan minutos, deberia despedirme de los Pikinims...*");
            if(pikinims[0].getCantidad() > 0){
                System.out.println("*Un Pikinim cyan viene y te dispara por la espalda*");
            } else if((pikinims[1].getCantidad() == 0) && (pikinims[1].getCantidad() > 0)){
                System.out.println("*Un Pikinim magenta viene y te pega una patada que te deja inconsciente");
            } else if((pikinims[2].getCantidad() == 0) && (pikinims[1].getCantidad() == 0) && (pikinims[2].getCantidad() > 0)) {
                System.out.println("*Un Pikinim amarillo viene y pulsa un botón, la tableta comienza a hacer un ruido, te electrocutas con ella.");
            }
            System.out.println("Lo del asteroide fue solo un plan nuestro para divertirnos con viajeros extraviados, si lograbas encontrar las piezas te dejaríamos ir, de lo contrario solo te esperaba la muerte, adiós.");
            System.out.println("Game Over, presiona 'Enter' para salir del juego.");
            scanner.nextLine();
        }
    }
}

