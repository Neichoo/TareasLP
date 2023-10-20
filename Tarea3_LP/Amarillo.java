import static java.lang.Math.ceil;
public class Amarillo extends Pikinim{

    /*
     *** Parámetros:
     * No recibe ningún parámetro, debido a que sus valores son fijos.
     *** Retorno:
     * No retorna nada, debido a que es el constructor de la subclase Amarillo.
     *** public Amarillo:
     * Es el constructor de la subclase Amarillo, tiene sus atributos fijos (ataque, capacidad y cantidad).
     */
    public Amarillo(){
        setAtaque(1);
        setCapacidad(3);
        setCantidad(10);
    }

    /*
     *** Parámetros:
     * int cantidad: La cantidad con la que se va a usar el método multiplicar.
     *** Retorno:
     * No retorna nada, debido a que es un método de tipo void.
     *** public void multiplicar(int cantidad):
     * Es un método personalizado para esta subclase, heredado de la super clase Pikinim, lo que hace este método es
     * multiplicar la cantidad de Pikinims Amarillos utilizando su propia fórmula.
     */
    @Override
    public void multiplicar(int cantidad){
        int cantidad_a = getCantidad();
        cantidad_a += (int)Math.ceil(this.cantidad * 1.5);
        setCantidad(cantidad_a);
    }
}