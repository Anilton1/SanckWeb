package br.com.rexpress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.rexpress.util.DAOGenerico;
import br.com.rexpress.util.EntityManagerUtil;
import br.com.rexpress.valuesObjects.Merenda;
import br.com.rexpress.valuesObjects.MerendaMapaHist;

public class MerendaMapaHistDAO extends DAOGenerico<MerendaMapaHist> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager aEntityManager;

	public MerendaMapaHistDAO() {
	}

	@SuppressWarnings("unchecked")
	public List<Merenda> listarTudo() {
		aEntityManager = EntityManagerUtil.getEntityManager();
		Query q = aEntityManager.createNamedQuery("MerendaMapaHist.findAll");
		List<Merenda> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}
	
	@SuppressWarnings("unchecked")
	public List<MerendaMapaHist> listarSeExiste(MerendaMapaHist pMerenda) {
		aEntityManager = EntityManagerUtil.getEntityManager();
		Query q = aEntityManager.createNamedQuery("MerendaMapaHist.seExiste");
		q.setParameter("descricao", pMerenda.getDescricaoMerenda());
		List<MerendaMapaHist> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}

}
