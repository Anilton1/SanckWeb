package br.com.rexpress.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.rexpress.util.DAOGenerico;
import br.com.rexpress.util.EntityManagerUtil;
import br.com.rexpress.valuesObjects.ItemMapaHist;
import br.com.rexpress.valuesObjects.TurnoMapa;

public class TurnoMapaDAO extends DAOGenerico<TurnoMapa>{
	private EntityManager aEntityManager;
	
	public TurnoMapaDAO(){
	}
	
	@SuppressWarnings("unchecked")
	public List<TurnoMapa> listarTudo(){
		aEntityManager = EntityManagerUtil.getEntityManager();
		Query q = aEntityManager.createNamedQuery("TurnoMapa.findALL");
		List<TurnoMapa> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}

	public TurnoMapa listarSeExiste(TurnoMapa pTurnoMapa){
		aEntityManager = EntityManagerUtil.getEntityManager();
		Query q = aEntityManager.createNamedQuery("TurnoMapa.seExiste");
		q.setParameter("id_instituicao", pTurnoMapa.getMapa().getInstituicao().getId());
		q.setParameter("id_merenda", pTurnoMapa.getMerendaMapaHist().getId());
		q.setParameter("id_mapa", pTurnoMapa.getMapa().getId());
		List<TurnoMapa> retornoConsulta = q.getResultList();
		return retornoConsulta.get(0);
	}
}
