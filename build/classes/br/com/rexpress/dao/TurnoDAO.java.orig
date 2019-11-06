package br.com.rexpress.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.rexpress.util.DAOGenerico;
import br.com.rexpress.util.EntityManagerUtil;
import br.com.rexpress.valuesObjects.Turno;

public class TurnoDAO extends DAOGenerico<Turno>{
	private EntityManager aEntityManager;
	
	public TurnoDAO(){
	}
	
	@SuppressWarnings("unchecked")
	public List<Turno> listarTudo(){
		aEntityManager = EntityManagerUtil.getEntityManager();
		Query q = aEntityManager.createNamedQuery("Turno.findALL");
		List<Turno> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}

}
