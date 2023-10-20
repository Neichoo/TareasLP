import java.util.Scanner;
public class Enemigo extends Zona implements ILevantable{
    private int vida;
    private int peso;
    private int ataque;

    /*
     *** Parámetros:
     * int vida: La vida que tendrá el enemigo, tipo entero.
     * int peso: El peso del enemigo, tipo entero.
     * int ataque: El ataque que tiene el enemigo, tipo entero.
     *** Retorno:
     * No retorna nada, debido a que es el constructor de la sublclase Enemigo.
     *** public Enemigo:
     * Es el constructor de la subclase Enemigo, al momento de crearla se le deben entregar los parámetros
     * correspondientes.
     */
    public Enemigo(int vida, int peso, int ataque){
        setVida(vida);
        setPeso(peso);
        setAtaque(ataque);
    }

    /*
     *** Parámetros:
     * No requiere de ningun parámetro.
     *** Retorno:
     * Retorna el peso del enemigo, tipo entero.
     *** public int getPeso():
     * Es el getter de peso, es el método por el que se obtiene el valor de peso.
     */
    public int getPeso() {
        return peso;
    }

    /*
     *** Parámetros:
     * int peso: El nuevo peso que se le asignará al enemigo, tipo entero.
     *** Retorno:
     * No retorna nada, debido a que es un método de tipo void.
     *** public int setPeso():
     * Es el setter de peso, es el método con el que se le asigna un nuevo valor al peso del enemigo.
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

    /*
     *** Parámetros:
     * No requiere de ningun parámetro.
     *** Retorno:
     * Retorna el ataque del enemigo, tipo entero.
     *** public int getAtaque():
     * Es el getter de ataque, es el método por el que se obtiene el valor de ataque.
     */
    public int getAtaque() {
        return ataque;
    }

    /*
     *** Parámetros:
     * int ataque: El nuevo ataque que se le asignará al enemigo, tipo entero.
     *** Retorno:
     * No retorna nada, debido a que es un método de tipo void.
     *** public int setAtaque():
     * Es el setter de ataque, es el método con el que se le asigna un nuevo valor al ataque del enemigo.
     */
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    /*
     *** Parámetros:
     * No requiere de ningun parámetro.
     *** Retorno:
     * Retorna la vida del enemigo, tipo entero.
     *** public int getVida():
     * Es el getter de vida, es el método por el que se obtiene el valor de la vida del enemigo.
     */
    public int getVida() {
        return vida;
    }

    /*
     *** Parámetros:
     * int vida: La nueva vida que se le asignará al enemigo, tipo entero.
     *** Retorno:
     * No retorna nada, debido a que es un método de tipo void.
     *** public int setVida():
     * Es el setter de vida, es el método con el que se le asigna un nuevo valor a la vida del enemigo.
     */
    public void setVida(int vida) {
        this.vida = vida;
    }

    /*
     *** Parámetros:
     * Pikinim[] pikinims: Un arreglo que contiene las 3 especies de Pikinims.
     *** Retorno:
     * No retorna nada, debido a que es un método de tipo void.
     *** public void interactuar(Pikinim[] pikinims):
     * Es un método personalizado para esta subclase, heredado de la super clase Zona, lo que hace este método es
     * verificar si la zona está completada, si es así, se imprime un mensaje diciendo que no hay nada más que hacer en
     * esta zona, de lo contrario se llama a Pelear(pikinims), luego si el enemigo es derrotado se imprime un mensaje,
     * si no es derrotado, se imprime otro.
     */
    @Override
    public void interactuar(Pikinim[] pikinims) {
        if (!getCompletada()){
            Pelear(pikinims);
            if(getVida() > 0){
                System.out.println("El enemigo es más fuerte que nosotros cuidado Pikinims! Tendremos que intentarlo más tarde.\n");
            } else if(getVida() == 0){
                System.out.println("Fue pan comido, sabía que los Pikinims eran mucho más fuerte que el enemigo, bien hecho Pikinims!\n");
            }
        } else{
            System.out.println("No hay nada que hacer aqui, ya completamos esta zona.\n");
        }
    }

    /*
     *** Parámetros:
     * Pikinim[] pikinims: Un arreglo que contiene las 3 especies de Pikinims.
     *** Retorno:
     * No retorna nada, debido a que es un método de tipo void.
     *** public void Pelear(Pikinim[] pikinims):
     * Es un método en el que se efectúa la pelea entre los Pikinims y el enemigo, utilizando las reglas correspondientes,
     * se imprime por pantalla la nueva cantidad de Pikinims que quedaron después del ataque. Luego se llama a
     * Levantar(pikimins) y se marca la zona como completada.
     */
    public void Pelear(Pikinim[] pikinims){
        int vida_enemigo = getVida();
        int ataque_pikinim = 0;
        for(Pikinim un_pikinim : pikinims){
            ataque_pikinim += un_pikinim.getCantidad() * un_pikinim.getAtaque();
        }
        vida_enemigo -= ataque_pikinim;
        if(vida_enemigo < 0){
            vida_enemigo = 0;
        }
        setVida(vida_enemigo);
        boolean mas_de_cero = false;
        while(!mas_de_cero){
            int color_al_azar = (int)(Math.random()*3);
            if(pikinims[color_al_azar].getCantidad() > 0){
                pikinims[color_al_azar].disminuir(getAtaque());
                mas_de_cero = true;
                if((pikinims[color_al_azar].getClass() == Cyan.class) && (pikinims[color_al_azar].getCantidad() >= 0)){
                    System.out.println("El enemigo le ha pegado a los Pikinim cyans, nueva cantidad: " + pikinims[color_al_azar].getCantidad());
                } else if((pikinims[color_al_azar].getClass() == Magenta.class) && (pikinims[color_al_azar].getCantidad() >= 0)){
                    System.out.println("El enemigo le ha pegado a los Pikinim magentas, nueva cantidad: " + pikinims[color_al_azar].getCantidad());
                } else if((pikinims[color_al_azar].getClass() == Amarillo.class) && (pikinims[color_al_azar].getCantidad() >= 0)){
                    System.out.println("El enemigo le ha pegado a los Pikinim amarillos, nueva cantidad: " + pikinims[color_al_azar].getCantidad());
                }
                if((pikinims[color_al_azar].getClass() == Cyan.class) && (pikinims[color_al_azar].getCantidad() < 0)){
                    pikinims[color_al_azar].setCantidad(0);
                    System.out.println("El enemigo le ha pegado a los Pikinim cyans, nueva cantidad: " + pikinims[color_al_azar].getCantidad());
                } else if((pikinims[color_al_azar].getClass() == Magenta.class) && (pikinims[color_al_azar].getCantidad() < 0)){
                    pikinims[color_al_azar].setCantidad(0);
                    System.out.println("El enemigo le ha pegado a los Pikinim magentas, nueva cantidad: " + pikinims[color_al_azar].getCantidad());
                } else if((pikinims[color_al_azar].getClass() == Amarillo.class) && (pikinims[color_al_azar].getCantidad() < 0)){
                    pikinims[color_al_azar].setCantidad(0);
                    System.out.println("El enemigo le ha pegado a los Pikinim amarillos, nueva cantidad: " + pikinims[color_al_azar].getCantidad());
                }
            }
        }
        if(vida_enemigo == 0){
            Levantar(pikinims);
            setCompletada(true);
        }
    }

    /*
     *** Parámetros:
     * Pikinim[] pikinims: Un arreglo que contiene las 3 especies de Pikinims.
     *** Retorno:
     * No retorna nada, debido a que es un método de tipo void.
     *** public void Levantar(Pikinim[] pikinims):
     * Es un método implementado desde la interfaz pública ILevantable, si se cumplen las condiciones necesarias
     * para poder levantar al enemigo, entonces se hace elegir al usuario una especie de Pikinim para poder multiplicar
     * la cantidad de esta misma por el peso del enemigo. Luego se imprime por pantalla cuandos Pikinims nuevos de esa
     * especie se añadieron y el nuevo total de cantidad.
     */
    @Override
    public void Levantar(Pikinim[] pikinims){
        int capacidad_total_pikinim = 0;
        for(Pikinim un_pikinim : pikinims){
            capacidad_total_pikinim += un_pikinim.getCantidad() * un_pikinim.getCapacidad();
        }
        int peso_enemigo = getPeso();
        if(capacidad_total_pikinim >= peso_enemigo){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Elige el color de Pikinim que se multiplicará por el peso del enemigo:");
            System.out.println("1.- Cyan\n2.- Magenta\n3.- Amarillo");
            int input = scanner.nextInt();
            switch(input){
                case 1:
                    int cantidad_og_c = pikinims[0].getCantidad();
                    pikinims[0].multiplicar(peso_enemigo);
                    int nueva_cantiad_c = pikinims[0].getCantidad();
                    System.out.println("Los Pikinim cyans se han multiplicado en " + (cantidad_og_c - nueva_cantiad_c ) + ", la nueva cantidad es: " + nueva_cantiad_c);
                    break;
                case 2:
                    int cantidad_og_m = pikinims[1].getCantidad();
                    pikinims[1].multiplicar(peso_enemigo);
                    int nueva_cantiad_m = pikinims[1].getCantidad();
                    System.out.println("Los Pikinim cyans se han multiplicado en " + (cantidad_og_m - nueva_cantiad_m ) + ", la nueva cantidad es: " + nueva_cantiad_m);
                    break;
                case 3:
                    int cantidad_og_a = pikinims[2].getCantidad();
                    pikinims[2].multiplicar(peso_enemigo);
                    int nueva_cantiad_a = pikinims[2].getCantidad();
                    System.out.println("Los Pikinim cyans se han multiplicado en " + (cantidad_og_a - nueva_cantiad_a ) + ", la nueva cantidad es: " + nueva_cantiad_a);
                    break;
            }
        }
    }

    /*
     *** Parámetros:
     * No requiere parámetros.
     *** Retorno:
     * Retorna un string que contiene datos relevantes en caso de que la zona no esté completada, de lo contrario imprime
     * un mensaje diciendo que ya no se puede hacer nada más
     *** public String toString():
     * Es un método visto en clases, con el que se obtiene información de los atributos correspondientes.
     */
    public String toString(){
        if(!getCompletada()){
            return("Enemigo: Vida = " + getVida() + ", Peso = " + getPeso() + ", Ataque = " + getAtaque());
        }
        return ("No hay nada que hacer aqui, zona completada.");
    }
}
