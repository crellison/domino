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

	// devuelve un booleano si or no puede jugar
	public boolean puedeJugar(Mesa m) {
		int izda = m.recogeIzda();
		int dcha = m.recogeDcha();
		boolean puede = true;
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
	// suma de las fichas
	public int sumaFichas() {
		int sum = 0;
		for (Ficha temp : fichas) {
			sum += temp.valor();
		} return sum;
	}
	public int numFichas() {return fichas.size();}
	public boolean finalizado() {return fichas.isEmpty();}
	public void mostrarFichas() {
		for (Ficha temp : fichas) {
			System.out.print(temp.toString()+" ");
		} System.out.print("\n");
		int tamano = fichas.size();
		for (int i=0;1<tamano;i++) {
			System.out.print("  "+i+"   ");
		}
	}

	// metodos abstractos
	public abstract Ficha elegirFicha(Mesa m);
	public abstract void recibirFicha(Ficha f);

}