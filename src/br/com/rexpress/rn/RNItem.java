package br.com.rexpress.rn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.rexpress.dao.ItemDAO;
import br.com.rexpress.valuesObjects.Item;

public class RNItem {

	private ItemDAO dao;

	public RNItem() {
		dao = new ItemDAO();
	}

	public Item valida(String pDescricaoItem) {
		List<Item> tamanhoItemArray;
		ArrayList<Item> itemArray;
		itemArray = new ArrayList<Item>();

		Item aItem = null;
		tamanhoItemArray = dao.listarTudo();

		for (int i = 0; i < tamanhoItemArray.size(); i++) {
			itemArray.add((Item) tamanhoItemArray.get(i));
		}

		Iterator<Item> ite = itemArray.iterator();
		
		while (ite.hasNext()) {
			Item pItem = ite.next();
			if ((pItem.getDescricaoItem().equals(pDescricaoItem))) {
				aItem = pItem;
			}
		}
		return aItem;
	}
}
