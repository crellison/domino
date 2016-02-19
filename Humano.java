public class Humano extends Jugador {

	// constructor
	public Humano(String nom) {super(nom)}
	// NB - super() llama el constructor del superclass (Jugador)

	// metodos de instancia
	public Ficha elegirFicha(Mesa m) {}
	public void recibirFicha(Ficha f) {}
}