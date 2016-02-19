import java.util.Random;
import java.util.ArrayList;
/* use nextInt(int n) metodo de instancia
 * Random miRand = new Random();
 * miRand.nextInt(28) <- devuelve un nuevo numero al azar entre 0 y 27 
 */

public class Mesa {

	// atributos
	private ArrayList<Ficha> monton;
	private DobleCola<Ficha> colocadas;

	// int NUM_DOTS = 6;
	// int NUM_FICHAS = ((NUM_DOTS*NUM_DOTS +(3*NUM_DOTS)+2)/2);

	// constructor
	public Mesa(int num_dots) {
		monton = new ArrayList<Ficha>(28);
		colocadas = new DobleCola<Ficha>();
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
			colocadas.anadirPrim(f);
		} else {
			colocadas.anadirUlt(f);
		}
	}
	public Ficha sacarRobar() {
		Random rand = new Random();
		int index = rand.nextInt(28);
		// mira si hay elemento en index
		while (monton.get(index) == null) {
			index = rand.nextInt(28);
		}
		Ficha temp = monton.get(index);
		monton.remove(index);
		return temp;
	}
	public Boolean vaciaRobar() {return monton.isEmpty();}
	public Boolean vaciaColocadas() {return colocadas.estaVacia();}
	public int recogeIzda() {
		Ficha primero = colocadas.primero();
		return primero.izda();
	}
	public int recogeDcha() {
		Ficha ultimo = colocadas.ultimo();
		return ultimo.dcha();
	}
	public void mostrarColocadas() {colocadas.mostrar();}

	public static void main(String[] args) {
		Mesa miMesa = new Mesa(6);
		for (int i=0;i<28;i++) {
			System.out.println(miMesa.monton.get(i).toString());
		}
	}
}