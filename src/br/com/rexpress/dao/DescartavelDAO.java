package br.com.rexpress.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.rexpress.util.DAOGenerico;
import br.com.rexpress.util.EntityManagerUtil;
import br.com.rexpress.valuesObjects.Descartavel;

public class DescartavelDAO extends DAOGenerico<Descartavel>{
	private EntityManager aEntityManager;
	private ArrayList<String> listaDescartaveis;
	
	public DescartavelDAO(){
		listaDescartaveis = new ArrayList<String>();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Descartavel> listarTudo(){
		aEntityManager = EntityManagerUtil.getEntityManager();
		Query q = aEntityManager.createNamedQuery("Descartavel.findAll");
		List<Descartavel> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}

	public ArrayList<String> getListaDescartavels() {
		return listaDescartaveis;
	}
	
	public Descartavel buscarPorId(Integer pId){
		return aEntityManager.find(Descartavel.class, pId);
	}

}
