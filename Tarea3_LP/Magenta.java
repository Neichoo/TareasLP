public class Magenta extends Pikinim{

    /*
     *** Parámetros:
     * No recibe ningún parámetro, debido a que sus valores son fijos.
     *** Retorno:
     * No retorna nada, debido a que es el constructor de la subclase Magenta.
     *** public Amarillo:
     * Es el constructor de la subclase Magenta, tiene sus atributos por default (ataque, capacidad y cantidad).
     */
    public Magenta(){
        setAtaque(2);
        setCapacidad(1);
        setCantidad(10);
    }

    /*
     *** Parámetros:
     * int cantidad: La cantidad con la que se va a usar el método multiplicar.
     *** Retorno:
     * No retorna nada, debido a que es un método de tipo void.
     *** public void multiplicar(int cantidad):
     * Es un método personalizado para esta subclase, heredado de la super clase Pikinim, lo que hace este método es
     * multiplicar la cantidad de Pikinims Magenta utilizando su propia fórmula.
     */
    @Override
    public void multiplicar(int cantidad){
        int cantidad_m = getCantidad();
        int ataque_m = getAtaque();
        cantidad_m += cantidad_m * ataque_m;
        setCantidad(cantidad_m);
    }
}