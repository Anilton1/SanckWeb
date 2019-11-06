package br.com.rexpress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.rexpress.util.DAOGenerico;
import br.com.rexpress.util.EntityManagerUtil;
import br.com.rexpress.valuesObjects.Item;
import br.com.rexpress.valuesObjects.ItemMapaHist;

public class ItemMapaHistDAO extends DAOGenerico<ItemMapaHist> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager aEntityManager;
	
//	@SuppressWarnings("unchecked")
//	public List<Item> listarTudo(){
//		aEntityManager = EntityManagerUtil.getEntityManager();
//		Query q = aEntityManager.createNamedQuery("ItemMapaHist.findAll");
//		List<Item> retornoConsulta = q.getResultList();
//		return retornoConsulta;
//	}
	
	public List<ItemMapaHist> listarSeExiste(ItemMapaHist pItem){
		aEntityManager = EntityManagerUtil.getEntityManager();
		Query q = aEntityManager.createNamedQuery("ItemMapaHist.seExiste");
		q.setParameter("descricao", pItem.getDescricaoItem());
		q.setParameter("grama", pItem.getGrama());
		q.setParameter("percapita", pItem.getPercapita());
		List<ItemMapaHist> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}

	public Item buscarPorId(Integer pId){
		return aEntityManager.find(Item.class, pId);
	}
}