package br.com.rexpress.rn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.rexpress.dao.MerendaDAO;
import br.com.rexpress.valuesObjects.Merenda;

public class RNMerenda {

	private MerendaDAO dao;

	public RNMerenda() {
		dao = new MerendaDAO();
	}

	public Merenda valida(String pDescMerenda) {
		ArrayList<Merenda> merendaArray = (ArrayList<Merenda>) dao.listarTudo();
		Merenda aMerenda = null;
		
		Iterator<Merenda> ite = merendaArray.iterator();
		while (ite.hasNext()) {
			Merenda pMerenda = ite.next();
			if ((pMerenda.getDescricaoMerenda().equals(pDescMerenda))) {
				aMerenda = pMerenda;
			}
		}
		return aMerenda;
	}
	
	public List<Merenda> completeMerenda(String query) {
		dao = new MerendaDAO(); 
		List<Merenda> allMerendas = dao.listarTudo();
		List<Merenda> filteredMerendas = new ArrayList<Merenda>();

		for (int i = 0; i < allMerendas.size(); i++) {
			Merenda skin = allMerendas.get(i);
			if (skin.getDescricaoMerenda().toLowerCase().startsWith(query.toLowerCase())) {
				filteredMerendas.add(skin);
			}
		}
		return filteredMerendas;
	}

}
