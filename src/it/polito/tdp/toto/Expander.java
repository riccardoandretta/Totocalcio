package it.polito.tdp.toto;

import java.util.ArrayList;
import java.util.List;

public class Expander { // espande la schedina dandomi tutte le combinazioni vincenti

	private List<Schedina> soluzione;

	// per generare una singola colonna (una soluzione) posso aggiungere una per
	// volta una partita alla schedina (ogni volta che scendo di livello) aprendomi
	// a tutte le combinazioni

	public List<Schedina> expand(Pronostico p) {

		soluzione = new ArrayList<Schedina>(); // la definisco qui perchè se la chiamo diverse volte voglio avere
												// soluzioni diverse

		cerca(new Schedina(p.getN()), p, 0); // questo metodo chiama cerca a livello 0 (caso iniziale)

		return soluzione;
	}

	private void cerca(Schedina parziale, Pronostico p, int livello) { // metodo ricorsivo
		// mi passano una schedina parziale, ed io devo aggiungere gli elementi dato il
		// pronostico
		// APPROCCIO COSTRUTTIVO: costruisco, livello dopo livello, le colonne

		if (livello == p.getN()) {
			// caso terminale => ho una soluzione completa
			soluzione.add(new Schedina(parziale)); // parziale in questo caso è totale; l'oggetto schedina lo devo
													// creare, altrimenti utilizzerei sempre lo stesso!! E momento dopo
													// momento il suo valore cambia, per cui ho la necessità di
													// fotografare quel momento (quindi new Schedina)
			return; // devo uscire
		}

		/*
		 * Problema parziale: Schedina 1-X, livello 2 Devo guardare pronostico p(2).
		 * Supponiamo sia X2 Allora genererò le nuove soluzioni parziali 1-X-X, 1-X-2
		 */

		Previsione mosse = p.get(livello);
		for (Risultato mossa : mosse.getValori()) {
			parziale.add(mossa); // prova la soluzione
			cerca(parziale, p, livello + 1);
			parziale.removeLast(); // backtrack
		}

	}
}
