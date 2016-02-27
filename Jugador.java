import java.util.ArrayList;

public abstract class Jugador {

	// atributos
	protected ArrayList<Ficha> fichas;
	private String nombre;

	// max num de fichas en jugador = 22

	// constructor
	public Jugador(String nom) {
		fichas = new ArrayList<Ficha>(22);
		nombre = nom;
	}

	// metodos de instancia

	// devuelve el nombre
	public String nombre() {return nombre;}
	// devuelve un booleano si or no puede jugar
	public boolean puedeJugar(Mesa m) {
		int izda = m.recogeIzda();
		int dcha = m.recogeDcha();
		boolean puede = false;
		for (Ficha temp : fichas) {
			puede = puede||temp.contieneNum(izda)||temp.contieneNum(dcha);
		} return puede;
	}
	// dice si o no puede usar una ficha especifica
	public boolean puedeUsar(Mesa m, Ficha f) {
		int izda = m.recogeIzda();
		int dcha = m.recogeDcha();
		boolean puede = f.contieneNum(izda)||f.contieneNum(dcha);
		return puede;
	}
	// devuelve el doble con el maximo valor
	public Ficha maxDoble() {
		Ficha max = new Ficha(-1,-1);
		for (Ficha temp : fichas) {
			if (temp.esDoble() && temp.valor()>max.valor()) {
				max = temp;
			}
		} return max;
	}
	// suma de las fichas
	public int puntos() {
		int sum = 0;
		for (Ficha temp : fichas) {
			sum += temp.valor();
		} return sum;
	}
	// devuelve el numero de fichas de un jugador
	public int numFichas() {return fichas.size();}
	// true si un jugador no tiene nada mas fichas, false si no
	public boolean finalizado() {return fichas.isEmpty();}
	// imprima las fichas de un jugador
	public void mostrarFichas() {
		System.out.println("Fichas de "+nombre);
		for (Ficha temp : fichas) {
			System.out.print(temp.toString()+" ");
		} System.out.print("\n");
		int tamano = fichas.size();
		for (int i=0;i<tamano;i++) {
			System.out.print("  "+i+"   ");
		} System.out.println();
	}

	// metodos abstractos
	public abstract Ficha elegirFicha(Mesa m);
	public abstract void recibirFicha(Ficha f);

}