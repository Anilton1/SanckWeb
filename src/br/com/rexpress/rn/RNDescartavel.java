package br.com.rexpress.rn;

import java.util.ArrayList;
import java.util.Iterator;

import br.com.rexpress.dao.DescartavelDAO;
import br.com.rexpress.valuesObjects.Descartavel;

public class RNDescartavel {

	private DescartavelDAO dao;
	
	public RNDescartavel() {
		dao = new DescartavelDAO();
	}

	public Descartavel valida(String pNomeDescartavel) {
		
		ArrayList<Descartavel> rotaArray = (ArrayList<Descartavel>) dao.listarTudo();
		Descartavel aDescartavel = null; 

		Iterator<Descartavel> ite = rotaArray.iterator();
		while (ite.hasNext()) {
			Descartavel pDescartavel = ite.next();
			if ((pDescartavel.getDescricao().equals(pNomeDescartavel))) {
				aDescartavel = pDescartavel;
			}
		}
		return aDescartavel;
	}
	
}
