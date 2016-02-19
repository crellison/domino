public class Maquino extends Jugador {

	// constructor
	public Maquino(String nom) {super(nom)}
	// NB - super() llama el constructor del superclass (Jugador)

	// metodos de instancia
	public Ficha elegirFicha(Mesa m) {}
	public void recibirFicha(Ficha f) {}
}