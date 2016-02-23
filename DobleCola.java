import java.util.NoSuchElementException;

public class DobleCola<E> {

	// atributos
	private Nodo anterior; // igual a primero/izda
	private Nodo posterior; // igual a ultimo/dcha
	private int tamano;

	// class de ayuda
	private class Nodo<E> {
		protected E dato;
		protected Nodo izquierda;
		protected Nodo derecha;
		// constructor
		public Nodo(E dato,Nodo izquierda,Nodo derecha) {
			dato = dato;
			izquierda = izquierda;
			derecha = derecha;
		}
		// metodo ayudante a mostrarDcha()
		public void mostrarReves() {
			if (izquierda != null) {izquierda.mostrarReves();}
			if (dato != null) {System.out.print(dato.toString()+" ");}
		}
		public E dato() {return dato;}
	}

		// constructor
	public DobleCola() {tamano = 0;}

	// Metodos de Instancia

	// devuelve tamano
	public int tamano() {return tamano;}
	// devuelve booleano 'true' si la doble cola esta vacia
	public boolean estaVacia() {return tamano==0;}
	// revuelve el primero elemento
	public <E> E primero() {return (E)anterior.dato();}
	// revuelve el ultimo elemento
	public <E> E ultimo() {return (E)posterior.dato();}

	// anade un elemento al empieza de la Doble cola
	public void anadirPrim(E elemento) {
		Nodo temp = new Nodo(elemento,null,anterior);
		anterior = temp;
		if (posterior == null) { // pone el elemento en posterior si posterior esta vacia
			posterior = new Nodo(elemento,null,null);
		}
		tamano++; // anade uno a tamano
		System.out.println("Anadiendo a primero "+elemento);
	}
	// anade un elemento al termino de la Doble cola
	public void anadirUlt(E elemento) {
		Nodo temp = new Nodo(elemento,posterior,null);
		posterior = temp;
		if (anterior == null) { // pone el elemento en anterior si anterior esta vacia
			anterior = new Nodo(elemento,null,null);
		}
		tamano++; // anade uno a tamano
		System.out.println("Anadiendo a ultima "+elemento);
	}	
	// elimina el primero elemento
	public void eliminarPrim() {
		if (tamano != 0) {
			anterior = anterior.derecha;
			//anterior.izquierda = null;
			tamano--;
			System.out.println("Eliminado primero");
		}
	}
	// elimina el ultimo elemento
	public void eliminarUlt() {
		if (tamano != 0) {
			posterior = posterior.izquierda;
			//posterior.derecha = null;
			tamano--;
			System.out.println("Eliminado ultima");
		}
	}
	// imprima la media izquierda de la cola 
	public void mostrarIzda() {
		//if (tamano == 1) {return;} // sale temprano si solo hay un elemento
		Nodo temp = anterior;
		while (temp.derecha != null) {
			System.out.print(temp.dato().toString()+" ");
			temp = temp.derecha;
		}
	}
	// imprima la media derecha de la cola
	public void mostrarDcha() {
		Nodo temp = posterior;
		if (temp != null) {temp.mostrarReves();}
	}
	// imprima todo de la cola
	public void mostrar() {
		//System.out.print("Current: ");
		if (tamano != 0) {
			mostrarIzda();
			//System.out.print("||");
			mostrarDcha();
		} else {System.out.print("Vacia");}
		System.out.println();
	}

	// Main metodo para probar los metodos
	public static void main(String[] args) {
		DobleCola miCola = new DobleCola();
		miCola.mostrar();
		miCola.anadirPrim(10);
		miCola.mostrar();
		miCola.anadirPrim(23);
		miCola.mostrar();
		miCola.anadirPrim(16);
		miCola.mostrar();
		System.out.println("Primero: "+miCola.primero());
		miCola.eliminarPrim();
		miCola.mostrar();
		miCola.eliminarUlt();
		miCola.mostrar();
		miCola.anadirUlt(89);
		miCola.mostrar();
		System.out.println("Ultimo: "+miCola.ultimo());
		System.out.println(miCola.estaVacia());
		miCola.eliminarPrim();
		miCola.mostrar();
		miCola.eliminarUlt();
		miCola.mostrar();

	}
}

