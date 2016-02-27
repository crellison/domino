public class Ficha {

	// atributos
	private int izda;
	private int dcha;

	// constructor
	public Ficha(int a, int b) {
		izda = a;
		dcha = b;
	}

	// metodos de instancia

	// cambia izda con dcha
	public void girar() {
		int temp=izda;
		izda=dcha;
		dcha=temp;
	}
	// devuelve el valor
	public int valor() {return izda+dcha;}
	// valor funcion especifica a la clase Maquino()
	// usado para ordenar sus fichas para el AI
	public int maquinoValor() {
		if (esDoble()) {return valor()+4;}
		else {return valor();}
	}
	// true si el num esta en la ficha, false si no
	public boolean contieneNum(int n) {return n==izda || n==dcha;}
	// true si izda=dcha
	public boolean esDoble() {return izda==dcha;}
	// toString() metodo para mostrar la ficha
	public String toString() {
		return ("["+izda+"|"+dcha+"]");
	}
	// devuelve izda o dcha
	public int izda() {return izda;}
	public int dcha() {return dcha;}

	public static void main(String[] args) {
		if (args.length !=2) { // toma dos valores
            System.out.println("\nusage: java Ficha \'command-string\' ");
            System.out.println("\ncommand-string es un String de dos ints");
            System.out.println("ej. 2 4 -> [2|4]\n");
        } else {
        	Ficha miFicha = new Ficha(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        	System.out.println(miFicha.toString());
        	System.out.println("Es doble? "+miFicha.esDoble());
        	System.out.println("Contiene 6? "+miFicha.contieneNum(6));
        	System.out.println("Valor? "+miFicha.valor());
        	miFicha.girar();
        	System.out.println("Girado: "+miFicha);
        }
	}
}