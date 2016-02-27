import java.util.Random;
import java.util.ArrayList;
/* use nextInt(int n) metodo de instancia
 * Random miRand = new Random();
 * miRand.nextInt(28) <- devuelve un nuevo numero al azar entre 0 y 27 
 */

public class Mesa {

	// atributos
	private ArrayList<Ficha> monton;
	private DCola<Ficha> colocadas;

	// int NUM_DOTS = 6;
	// int NUM_FICHAS = ((NUM_DOTS*NUM_DOTS +(3*NUM_DOTS)+2)/2);

	// constructor
	public Mesa() {
		//int num_fichas = ((num_dots*num_dots +(3*num_dots)+2)/2);
		monton = new ArrayList<Ficha>(28);
		colocadas = new DCola<Ficha>();
		for (int i=0;i<=6;i++) {
			for (int j=0;j<=i;j++) {
				Ficha temp = new Ficha(i,j);
				monton.add(temp); // anade la ficha al monton
			}
		}
	}

	// metodos

	// anade la Ficha f a Colocadas
	// n=0 -> izda || else -> dcha
	public void anadirColocadas(Ficha f, int n) {
		if (n==0) {
			colocadas.anadirEncima(f);
		} else {
			colocadas.anadirAbajo(f);
		}
	}
	// recoge un ficha al azar del monton de fichas
	public Ficha sacarRobar() {
		Random rand = new Random();
		int lenMonton = monton.size();
		int index = rand.nextInt(lenMonton);
		// mira si hay elemento en index
		while (monton.get(index) == null) {
			index = rand.nextInt(lenMonton);
		}
		Ficha temp = monton.get(index);
		monton.remove(index);
		return temp;
	}
	// true si esta vacia el monton, false si no
	public Boolean vaciaRobar() {return monton.isEmpty();}
	// true si esta vacia los colocadas, false si no
	public Boolean vaciaColocadas() {return colocadas.estaVacia();}
	// devuelve el numero mas a la izquierda de las colocadas
	public int recogeIzda() {
		Ficha primero = colocadas.encima();
		return primero.izda();
	}
	// devuelve el numero mas a la derecha de las colocadas
	public int recogeDcha() {
		Ficha ultimo = colocadas.abajo();
		return ultimo.dcha();
	}
	// imprima las colocadas
	public void mostrarColocadas() {colocadas.mostrar();}

	public static void main(String[] args) {
		Mesa miMesa = new Mesa();
		for (int i=0;i<28;i++) {
			System.out.println(miMesa.monton.get(i).toString());
		}
	}
}