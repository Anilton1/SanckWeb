package br.com.rexpress.rn;

import java.util.ArrayList;
import java.util.Iterator;

import br.com.rexpress.dao.RotaDAO;
import br.com.rexpress.valuesObjects.Rota;

public class RNRota {

	private RotaDAO dao;
	
	public RNRota() {
		dao = new RotaDAO();
	}

	public Rota valida(String pNomeRota) {
		
		ArrayList<Rota> rotaArray = (ArrayList<Rota>) dao.listarTudo();
		Rota aRota = null; 

		Iterator<Rota> ite = rotaArray.iterator();
		while (ite.hasNext()) {
			Rota pRota = ite.next();
			if ((pRota.getNome().equals(pNomeRota))) {
				aRota = pRota;
			}
		}
		return aRota;
	}
	
}
