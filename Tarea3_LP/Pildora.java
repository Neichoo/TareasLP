import java.util.Scanner;
public class Pildora extends Zona {
    private int cantidad;

    /*
     *** Parámetros:
     * No requiere de ningun parámetro.
     *** Retorno:
     * Retorna la cantidad de la pildora, tipo entero.
     *** public int getCantidad():
     * Es el getter de cantidad, es el método por el que se obtiene el valor de la cantidad de la pildora.
     */
    public int getCantidad() {
        return cantidad;
    }

    /*
     *** Parámetros:
     * int cantidad: La nueva cantidad que se le asignará a la pildora, tipo entero.
     *** Retorno:
     * No retorna nada, debido a que es un método de tipo void.
     *** public int setCantidad():
     * Es el setter de cantidad, es el método con el que se le asigna un nuevo valor a la cantidad de la pildora.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /*
     *** Parámetros:
     * int cantidad: La cantidad que tendrá la pildora, tipo entero.
     *** Retorno:
     * No retorna nada, debido a que es el constructor de la sublclase Pildora.
     *** public Pildora:
     * Es el constructor de la subclase Pildora, al momento de crearla se le deben entregar los parámetros
     * correspondientes.
     */
    public Pildora(int cantidad){
        setCantidad(cantidad);
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
     * esta zona, de lo contrario se pide un input al usuario para ver cual especie de Pikimin quiere multiplicar, luego
     * se imprime por pantalla la cantidad de Pikimins que aumentaron y la nueva cantidad total.
     */
    @Override
    public void interactuar(Pikinim[] pikinims) {
        if(!getCompletada()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Luego de que los Pikinims más experimentados en medicamentos analizaran las pildoras, concluyen que tienen un efecto beneficioso, mutliplica la cantidad de Pikinims de una especie!");
            System.out.println("Ahora es tu turno, debes elegir que especie quieres multiplicar");
            System.out.println("1.- Cyan\n2.- Magenta\n3.- Amarillo");
            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    int cantidad_antigua1 = pikinims[0].getCantidad();
                    pikinims[0].multiplicar(getCantidad());
                    setCompletada(true);
                    System.out.println("La cantidad de cyans aumentó en " + (pikinims[0].getCantidad() - cantidad_antigua1) + ", nueva cantidad: " + pikinims[0].getCantidad());
                    break;
                case 2:
                    int cantidad_antigua2 = pikinims[1].getCantidad();
                    pikinims[1].multiplicar(getCantidad());
                    setCompletada(true);
                    System.out.println("La cantidad de cyans aumentó en " + (pikinims[0].getCantidad() - cantidad_antigua2) + ", nueva cantidad: " + pikinims[0].getCantidad());
                    break;
                case 3:
                    int cantidad_antigua3 = pikinims[2].getCantidad();
                    pikinims[2].multiplicar(getCantidad());
                    setCompletada(true);
                    System.out.println("La cantidad de cyans aumentó en " + (pikinims[0].getCantidad() - cantidad_antigua3) + ", nueva cantidad: " + pikinims[0].getCantidad());
                    break;
            }
        } else {
            System.out.println("No hay nada que hacer aqui, ya completamos esta zona.\n");
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
            return("Pildora: Cantidad = " + getCantidad());
        }
        return ("No hay nada que hacer aqui, zona completada.");
    }
}
