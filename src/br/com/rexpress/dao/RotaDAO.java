package br.com.rexpress.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.rexpress.util.DAOGenerico;
import br.com.rexpress.util.EntityManagerUtil;
import br.com.rexpress.valuesObjects.Rota;

public class RotaDAO extends DAOGenerico<Rota> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager aEntityManager;
	private ArrayList<String> listaRotas;
	
	public RotaDAO(){
		listaRotas = new ArrayList<String>();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Rota> listarTudo(){
		aEntityManager = EntityManagerUtil.getEntityManager();
		Query q = aEntityManager.createNamedQuery("Rota.findAll");
		List<Rota> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}

	public ArrayList<String> getListaRotas() {
		return listaRotas;
	}

}
