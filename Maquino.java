public class Maquino extends Jugador {

	// constructor
	public Maquino(String nom) {super(nom);	}
	// NB - super() llama el constructor del superclass (Jugador)

	// metodos de instancia

	// esta funcion solo esta usada si el juga
	public Ficha elegirFicha(Mesa m) {
		int izda = m.recogeIzda();
		int dcha = m.recogeDcha();
		for (Ficha temp : fichas) {
			if (temp.contieneNum(izda)||temp.contieneNum(dcha)) {
				fichas.remove(temp);
				return temp;
			}
		} return new Ficha(-1,-1);
	}
	/* pone un ficha en la ArrayList de fichas
	 / el orden determina la intelegencia artificial del maquino
	 / usando Ficha.maquinoValor(), recoge un valor de la ficha
	 / entonces la ficha esta puesta en la lista segun los valores
	 / de las otras fichas en el ArrayList
	*/ 
	public void recibirFicha(Ficha f) {
		int valor = f.maquinoValor();
		int indice = 0;
		for (Ficha temp : fichas) {
			if (temp.maquinoValor()>=valor) {
				indice++;
			}
		}
		fichas.add(indice,f);
	}
	public Mesa jugar(Mesa m) {
		if (m.vaciaColocadas()) { // si es el primer turno, juega su doble de mayor valor
			Ficha elegida = maxDoble();
			if (elegida.valor()<0) {
				elegida = fichas.get(0);
				fichas.remove(0);
			} else {fichas.remove(maxDoble());}
			m.anadirColocadas(elegida,0);
			return m;
		}
		if (!puedeJugar(m)) {
			if (!m.vaciaRobar()) {recibirFicha(m.sacarRobar());}
			return m;
		}
		Ficha ficha = elegirFicha(m);
		int izda = m.recogeIzda();
		int dcha = m.recogeDcha();
		if (ficha.contieneNum(izda)) {
			if (ficha.dcha() != izda) {
				ficha.girar();
			} m.anadirColocadas(ficha,0);
		} else {
			if (ficha.izda() != dcha) {
					ficha.girar();
			} m.anadirColocadas(ficha,1);
		}
		return m;
	}
}