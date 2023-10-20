public abstract class Zona{
    protected boolean completada;
    public Zona(){
        setCompletada(false);
    }
    public abstract void interactuar(Pikinim[] pikinims);
    public boolean getCompletada(){
        return completada;
    }
    public void setCompletada(boolean completada){
        this.completada = completada;
    }
    public abstract String toString();
}