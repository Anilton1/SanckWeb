package br.com.rexpress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.rexpress.util.DAOGenerico;
import br.com.rexpress.util.EntityManagerUtil;
import br.com.rexpress.valuesObjects.Merenda;

public class MerendaDAO extends DAOGenerico<Merenda> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager aEntityManager;

	public MerendaDAO() {
	}

	@SuppressWarnings("unchecked")
	public List<Merenda> listarTudo() {
		aEntityManager = EntityManagerUtil.getEntityManager();
		Query q = aEntityManager.createNamedQuery("Merenda.findAll");
		List<Merenda> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}

}
