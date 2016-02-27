@SuppressWarnings("unchecked")
public class DCola<E> {
	
	private Nodo cabeza; // ultimo/encima
	private Nodo coda; // ultimo/abajo
	private int tamano;

	private class Nodo<E> { // mas como una pila

		private E dato;
		private Nodo ultimo;

		public Nodo(E d, Nodo u) {
			dato = d;
			ultimo = u;
		}
		// constructor
		public Nodo(E d) {dato = d;}

		// metodos de instancia

		// devuelve el dato
		public E dato() {return dato;}
		// devuelve el Nodo ultimo
		public Nodo ultimo() {return ultimo;}
		// muestra desde encima hacia abajo
		public void mostrar() { 
			if (ultimo!=null) {
				System.out.print(dato.toString()+" ");
				ultimo.mostrar();
			}
		}
		// muestra desde abajo hacia encima
		public void mostrarReves() { 
			if (ultimo!=null) {ultimo.mostrarReves();}
			System.out.print(dato.toString()+" ");
		}
	}
	// constructor
	public DCola() {tamano = 0;}

	// metodos de instancia

	// devuelve el tamano
	public int tamano() {return tamano;}
	// devuelve el booleano true si el DCola esta vacia, false si no
	public boolean estaVacia() {return tamano == 0;}
	// devuelve el primer elemento de la DCola
	public <E> E encima() {return (E)cabeza.dato();}
	// devuelve el ultimo elemento de la DCola
	public <E> E abajo() {return (E)coda.dato();}
	// anade un elemento a la cumbre de la DCola
	public void anadirEncima(E elemento) {
		Nodo temp = new Nodo(elemento,cabeza);
		cabeza = temp;
		// si la coda esta vacia, el elemento esta anadido (para recogeDcha() en clase Mesa)
		if (coda == null) {coda = new Nodo(elemento);}
		tamano++;
		// System.out.println("Anadiendo a primero "+elemento.toString());
	}
	// anade un elemento al fondo de la DCola
	public void anadirAbajo(E elemento) {
		Nodo temp = new Nodo(elemento,coda);
		coda = temp;
		// si la coda esta vacia, el elemento esta anadido (para recogeIzda() en clase Mesa)
		if (cabeza == null) {cabeza = new Nodo(elemento);}
		tamano++;
		// System.out.println("Anadiendo a ultimo "+elemento.toString());
	}
	// elimina el elemento mas arriba de la DCola
	public void eliminarEncima() {
		if (tamano!=0) {
			cabeza = cabeza.ultimo();
			tamano--;
			// System.out.println("Eliminado primera");
		} // else {System.out.println("Ya esta vacia.");}
	}
	// elimina el elemento mas abajo de la DCola
	public void eliminarAbajo() {
		if (tamano!=0) {
			coda = coda.ultimo();
			tamano--;
			// System.out.println("Eliminado ultima");
		} // else {System.out.println("Ya esta vacia.");}
	}
	// imprima la DCola
	public void mostrar() {
		if (tamano != 0) {
			if (cabeza!=null) {cabeza.mostrar();}
			// System.out.print("||");
			if (coda!=null) {coda.mostrarReves();}
		} else {System.out.print("Vacia");}
		System.out.println();
	}

	public static void main(String[] args) {
		DCola miCola = new DCola();
		miCola.mostrar();
		miCola.anadirEncima(10);
		miCola.mostrar();
		miCola.anadirEncima(23);
		miCola.mostrar();
		miCola.anadirEncima(16);
		miCola.mostrar();
		System.out.println("Primero: "+miCola.encima());
		miCola.eliminarEncima();
		miCola.mostrar();
		miCola.eliminarAbajo();
		miCola.mostrar();
		miCola.anadirAbajo(89);
		miCola.mostrar();
		System.out.println("Ultimo: "+miCola.abajo());
		System.out.println("Vacia? "+miCola.estaVacia());
		miCola.eliminarEncima();
		miCola.mostrar();
		miCola.eliminarAbajo();
		miCola.mostrar();
	}
}