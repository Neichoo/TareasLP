public class Muralla extends Zona{
    private int vida;

    /*
     *** Parámetros:
     * No requiere de ningun parámetro.
     *** Retorno:
     * Retorna la vida de la muralla, tipo entero.
     *** public int getVida():
     * Es el getter de vida, es el método por el que se obtiene el valor de la vida.
     */
    public int getVida() {
        return vida;
    }

    /*
     *** Parámetros:
     * int vida: La nueva vida que se le asignará a la muralla, tipo entero.
     *** Retorno:
     * No retorna nada, debido a que es un método de tipo void.
     *** public int setVida():
     * Es el setter de vida, es el método con el que se le asigna un nuevo valor a la vida de la muralla.
     */
    public void setVida(int vida) {
        this.vida = vida;
    }

    /*
     *** Parámetros:
     * int vida: La vida que tendrá la muralla, tipo entero.
     *** Retorno:
     * No retorna nada, debido a que es el constructor de la sublclase Muralla.
     *** public Muralla:
     * Es el constructor de la subclase Muralla, al momento de crearla se le deben entregar los parámetros
     * correspondientes.
     */
    public Muralla(int vida){
        setVida(vida);
        setCompletada(false);
    }

    /*
     *** Parámetros:
     * Pikinim[] pikinims: Un arreglo que contiene las 3 especies de Pikinims.
     *** Retorno:
     * No retorna nada, debido a que es un método de tipo void.
     *** public void interactuar(Pikinim[] pikinims):
     * Es un método personalizado para esta subclase, heredado de la super clase Zona, lo que hace este método es
     * verificar si la zona está completada, si es así, se imprime un mensaje diciendo que no hay nada más que hacer en
     * esta zona, de lo contrario se llama a TryRomper(pikinims), luego se imprime por pantalla el daño que se le hizo
     * a la muralla y la vida restante, si la vida de la muralla es 0, entonces se marca como completada.
     */
    @Override
    public void interactuar(Pikinim[] pikinims){
        if(!getCompletada()){
            int vida_muralla_antigua = getVida();
            TryRomper(pikinims);
            int vida_muralla = getVida();
            System.out.println("Al ataque contra la muralla!!! Daño realizado: " + (vida_muralla_antigua - vida_muralla) + ", nueva vida de la muralla: " + vida_muralla);
            if(vida_muralla == 0){
                setCompletada(true);
            }
        } else{
            System.out.println("No hay nada que hacer aqui, zona completada.");
        }
    }

    /*
     *** Parámetros:
     * Pikinim[] pikinims: Un arreglo que contiene las 3 especies de Pikinims.
     *** Retorno:
     * No retorna nada, debido a que es un método de tipo void.
     *** public void TryRomper(Pikinim[] pikinims):
     * Es un método en el que se intenta romper la muralla, tomando en cuenta el ataque total que hacen los Pikimins.
     */
    public void TryRomper(Pikinim[] pikinims){
        int ataque_total = 0;
        for(Pikinim un_pikinim : pikinims){
            ataque_total += un_pikinim.getCantidad() * un_pikinim.getAtaque();
        }
        int vida_muralla = getVida();
        vida_muralla -= ataque_total;
        if(vida_muralla < 0){
            vida_muralla = 0;
        }
        setVida(vida_muralla);
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
            return("Muralla: Vida = " + getVida());
        }
        return ("No hay nada que hacer aqui, zona completada.");
    }
}