public class Partida {

	// atributos
	private Mesa m;
	private ArrayList<Jugador> jugadores;

	// constructor
	public Partida(int numJugadores, int numMaquinos) {}

	// metodos de instancia
	public void Turno(int playerNum) {}
	public void cicloDeTurnos() {}
	public boolean terminado() {}
	public void mostrarPuntos() {}
	public void hacerJuego() {}

	public static void main(String[] args) {
		Partida miPartida = new Partida(args[0].parseInt(),args[1].parseInt());
		miPartida.hacerJuego();
	}
}