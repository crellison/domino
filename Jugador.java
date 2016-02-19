import java.util.ArrayList;

public abstract class Jugador {

	// atributos
	private ArrayList<Ficha> fichas;
	private String nombre;

	// max num de fichas en jugador = 22

	// constructor
	public Jugador(String nom) {
		fichas = new ArrayList<Ficha>(22);
		nombre = nom;
	}

	// metodos de instancia
	public boolean puedeJugar(Mesa m) {
		int izda = m.recogeIzda();
		int dcha = m.recogeDcha();
		boolean puede = true;
		for (Ficha temp : fichas) {
			puede = puede||temp.contieneNum(izda)||temp.contieneNum(dcha);
		} return puede;
	}
	public int sumaFichas() {
		int sum = 0;
		for (Ficha temp : fichas) {
			sum += temp.valor();
		} return sum;
	}
	public int numFichas() {return fichas.size();}
	public boolean finalizado() {return fichas.isEmpty();}

	// metodos abstractos
	public abstract Ficha elegirFicha(Mesa m);
	public abstract void recibirFicha(Ficha f);

}