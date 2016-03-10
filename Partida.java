import java.util.Scanner;
import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;
import java.lang.NumberFormatException;

public class Partida {

	// atributos
	private Mesa m;
	private ArrayList<Jugador> jugadores;

	// constructor
	public Partida(int numJugadores, int numMaquinos) {
		if (numJugadores>4 || numJugadores<1) {
			throw new IndexOutOfBoundsException("\nEl numero de jugadores debe ser entre 1 y 4");
		}
		if (numJugadores<numMaquinos || numMaquinos<0) {
			throw new IndexOutOfBoundsException("\nEl numero de maquinos debe ser "+
			"entre 0 y el numero de jugadores");
		}
		m = new Mesa();
		jugadores = new ArrayList<Jugador>(numJugadores);
		Scanner input = new Scanner(System.in);
		for (int i=numJugadores-numMaquinos;i>0;i--) {
			System.out.print("Nombre: ");
			String nom = input.next();
			Humano jugador = new Humano(nom);
			jugadores.add(jugador);
		}
		for (int i=0;i<numMaquinos;i++) {
			String nom = "Maquino"+i;
			Maquino jugador = new Maquino(nom);
			jugadores.add(jugador);
		}
	}

	// metodos de instancia

	// un turno normal de un jugador
	public void turno(int jugadorNum) {
		mostrarPartida();
		Jugador temp = jugadores.get(jugadorNum);
		jugadores.remove(jugadorNum);
		temp.mostrarFichas();
		int elegida = 0;
		if (temp.nombre().contains("Maquino")) {
			Maquino maquino = (Maquino)temp;
			m = maquino.jugar(m);
			jugadores.add(jugadorNum,maquino);
		} else {
			Humano humano = (Humano)temp;
			m = humano.jugar(m);
			jugadores.add(jugadorNum,humano);
		}
		System.out.println("\n\n\n");
	}
	// hace el primer turno con la regla especial de los dobles
	// devuelve el indice del primer jugador
	public int primerTurno() {
		int primerIndice = 0;
		int numJugadores = jugadores.size();
		Ficha primer = new Ficha(-1,-1);
		for (int i=0;i<numJugadores;i++) {
			Ficha temp = jugadores.get(i).maxDoble();
			if (primer.valor()<temp.valor()) {
				primerIndice = i;
			}
		} 
		if (primer == new Ficha(-1,-1)) {return 0;}
		return primerIndice;
	}
	// hace un ciclo de turnos 
	public void cicloDeTurnos(int indice) {
		int len = jugadores.size();
		for (int i=0;i<len;i++) {
			Jugador temp = jugadores.get(i);
			turno(i);
			if (temp.finalizado()) {break;}
			if (empate() == true) {
				System.out.println("La partida esta un empate\n");
				mostrarPuntos();
				break;
			}
		}
	}
	// devuelve un booleano true si algun jugador ha ganado
	public boolean terminado() {
		boolean terminado = false;
		for (Jugador temp : jugadores) {
			terminado = terminado||temp.finalizado();
		} return terminado;
	}
	// imprima el nombre del ganador y una lista de los puntos de los demas
	public void ganador() {
		int indice = -1;
		int current = 0;
		if (terminado()) {
			for (Jugador temp : jugadores) {
				if (temp.finalizado()) {indice = current;}
				current++;
			}
		} 
		Jugador ganador = jugadores.get(indice);
		mostrarPartida();
		System.out.println(ganador.nombre()+" ha ganado\n");
		mostrarPuntos();
	}
	// imprima los puntos de los jugadores
	public void mostrarPuntos() {
		System.out.println("Puntos de Jugadores");
		for (Jugador temp : jugadores) {
			System.out.print(temp.nombre()+": ");
			System.out.println(temp.puntos());
		}
	}
	// metodo mayor, hace el juego
	public void hacerJuego() {
		int primerIndice = primerTurno();
		cicloDeTurnos(primerIndice);	
		while (!terminado()) {
			cicloDeTurnos(0);
			if (empate()) {return;}
		}
		if (terminado()) {ganador();}
	}
	// devueve un booleano si la juega esta un empate
	public boolean empate() {
		boolean puedenJugar = true;
		for (Jugador temp : jugadores) {
			puedenJugar = puedenJugar && temp.puedeJugar(m);
		}
		return m.vaciaRobar() && !puedenJugar;
	}
	// imprima los colocadas
	public void mostrarPartida() {
		System.out.println("Las Colocadas");
		System.out.println();
		m.mostrarColocadas();
		System.out.println();
	}
	// inicia el juego
	// distribuye siete fichas a cada jugador
	public void iniciar() {
		int numJugadores = jugadores.size();
		for (int i=0;i<7;i++) {
			for (int j=0;j<numJugadores;j++) {
				Jugador temp = jugadores.get(j);
				jugadores.remove(j);
				Ficha current = m.sacarRobar();
				if (temp.nombre().contains("Maquino")) {
					Maquino maquino = (Maquino)temp;
					maquino.recibirFicha(current);
					jugadores.add(j,maquino);
				} else {
					Humano humano = (Humano)temp;
					humano.recibirFicha(current);
					jugadores.add(j,humano);
				}
			}
		}
	}

	public static void main(String[] args) {
		if (args.length !=2) { // toma dos valores
			throw new IndexOutOfBoundsException("\n\nIntentala otra vez.\n"+
            "\nusage: java Partida \'command-string\'"+
            "\ncommand-string es un String de dos enteros"+
            "\nPrimero entero determina el numero de jugadores (1,2,3,4)"+
            "\nSegundo entero determina el numero de maquinos (0, ..., numJugadores)\n");
        } else if (args.length==2) {
        	int entrada;
        	try {
        		entrada = Integer.parseInt(args[0]);
    		} catch (NumberFormatException e) {
		        System.err.println("Error: Entrada " + args[0] + " debe ser entero.");
		        System.exit(1);
		    }
		    try {
        		entrada = Integer.parseInt(args[1]);
    		} catch (NumberFormatException e) {
		        System.err.println("Error: Entrada " + args[1] + " debe ser entero.");
		        System.exit(1);
		    }
			Partida miPartida = new Partida(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
			miPartida.iniciar();
			miPartida.hacerJuego();
		}
	}
}