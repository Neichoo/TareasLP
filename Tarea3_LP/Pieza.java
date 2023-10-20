public class Pieza extends Zona implements ILevantable{
    private int peso;

    /*
     *** Parámetros:
     * No requiere de ningun parámetro.
     *** Retorno:
     * Retorna el peso de la pieza, tipo entero.
     *** public int getPeso():
     * Es el getter de peso, es el método por el que se obtiene el valor de peso.
     */
    public int getPeso() {
        return peso;
    }

    /*
     *** Parámetros:
     * int peso: El nuevo peso que se le asignará a la pieza, tipo entero.
     *** Retorno:
     * No retorna nada, debido a que es un método de tipo void.
     *** public int setPeso():
     * Es el setter de peso, es el método con el que se le asigna un nuevo valor al peso de la pieza.
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

    /*
     *** Parámetros:
     * int peso: El peso que tendrá la pieza, tipo entero.
     *** Retorno:
     * No retorna nada, debido a que es el constructor de la sublclase Pieza.
     *** public Muralla:
     * Es el constructor de la subclase Pieza, al momento de crearla se le deben entregar los parámetros
     * correspondientes.
     */
    public Pieza(int peso){
        setPeso(peso);
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
     * esta zona, de lo contrario se llama a Levantar(pikimins).
     */
    @Override
    public void interactuar(Pikinim[] pikinims){
        if (!getCompletada()){
            Levantar(pikinims);
        } else {
            System.out.println("No hay nada que hacer aqui, zona completada.\n");
        }
    }

    /*
     *** Parámetros:
     * Pikinim[] pikinims: Un arreglo que contiene las 3 especies de Pikinims.
     *** Retorno:
     * No retorna nada, debido a que es un método de tipo void.
     *** public void Levantar(Pikinim[] pikinims):
     * Es un método implementado desde la interfaz pública ILevantable, si se cumplen las condiciones necesarias
     * para poder levantar la pieza, entonces se imprime por pantalla un mensaje señalando que la pieza fue levantada,
     * de lo contrario se imprime un mensaje señalando lo contrario.
     */
    @Override
    public void Levantar(Pikinim[] pikinims){
        int capacidad_total = 0;
        for(Pikinim un_pikinim : pikinims){
                capacidad_total += un_pikinim.getCapacidad() * un_pikinim.getCantidad();
        }
        if(capacidad_total >= getPeso()){
            System.out.println("Pieza levantada!");
            setCompletada(true);
        } else{
            System.out.println("No hay suficiente capacidad");
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
            return("Pieza: Peso = " + getPeso());
        }
        return ("No hay nada que hacer aqui, zona completada.");
    }
}
