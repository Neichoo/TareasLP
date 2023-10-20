public abstract class Pikinim {
    //Atributos
    protected int ataque;
    protected int capacidad;
    protected int cantidad;
    //Metodos
    public Pikinim(){
        this.ataque = 0;
        this.capacidad = 0;
        this.cantidad = 0;
    }
    public int getAtaque(){
        return ataque;
    }
    public void setAtaque(int ataque){
        this.ataque = ataque;
    }
    public int getCapacidad(){
        return capacidad;
    }
    public void setCapacidad(int capacidad){
        this.capacidad = capacidad;
    }
    public int getCantidad(){
        return cantidad;
    }
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    //Metodos a implementar
    public void disminuir(int cantidad){
        this.cantidad -= cantidad;
    }
    public abstract void multiplicar(int cantidad);
}
