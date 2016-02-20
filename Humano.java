import java.util.Scanner;

public class Humano extends Jugador {

	// constructor
	public Humano(String nom) {super(nom);}
	// NB - super() llama el constructor del superclass (Jugador)

	// metodos de instancia

	// funcion ayudante a jugarFicha
	public Ficha elegirFicha(Mesa m) {
		Scanner input = new Scanner(System.in);
		int indice = 0;
		boolean valido = false;
		do {
			System.out.print("Escribe el indice de la ficha que quieres usar: ");
			if (input.hasNextInt()) {
				indice = input.nextInt();
				valido = true;
			} else {
				System.out.println("El indice no es valido");
				input.next();
			}
		} while (valido==false);
		return fichas.remove(indice);
	}
	// usa aportacion del jugador para elegir y jugar una ficha
	public Mesa jugarFicha(Mesa m) {
		Ficha elegida = new Ficha(7,7);
		do {elegida = elegirFicha(m);}
		while (puedeUsar(m,elegida)==false);
		Scanner input = new Scanner(System.in);
		boolean valido = false;
		int izda = m.recogeIzda();
		int dcha = m.recogeDcha();
		do { // elegir el lado
			System.out.print("Escribe el lado en que quieres jugar (i/d): ");
			char temp = input.next().charAt(0);
			// anadir a la izquierda
			if (temp=='i' && elegida.contieneNum(izda)) {
				if (elegida.dcha() != izda) {
					elegida.girar();
				} m.anadirColocadas(elegida,0);
				valido = true;
			// anadir a la derecha
			} else if (temp=='d' && elegida.contieneNum(dcha)) {
				if (elegida.izda() != dcha) {
					elegida.girar();
				} m.anadirColocadas(elegida,1);
				valido = true;
			} else {
				System.out.println("El lado no es valido");
				input.next();
			}
		} while(valido == false);
		return m;
	}
	public void recibirFicha(Ficha f) {fichas.add(f);}
}