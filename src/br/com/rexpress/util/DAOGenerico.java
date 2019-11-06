package br.com.rexpress.util;

import javax.persistence.EntityManager;

import br.com.rexpress.valuesObjects.MerendaMapaHist;
import br.com.rexpress.valuesObjects.TurnoMapa;

public class DAOGenerico<T extends VOGenerico> {

	private EntityManager aEntityManager;

	public DAOGenerico() {
		aEntityManager = EntityManagerUtil.getEntityManager();
	}

	public boolean inserir(T pEntidade) {
		boolean resposta = false;
		try {
			aEntityManager.getTransaction().begin();
			if(pEntidade.getClass() == MerendaMapaHist.class || pEntidade.getClass() == TurnoMapa.class){
				if (pEntidade.getId() == (null)) {
					aEntityManager.merge(pEntidade);
				}
			}
			
			if (pEntidade.getId() == (null) && pEntidade.getClass() != MerendaMapaHist.class && pEntidade.getClass() != TurnoMapa.class) {
				aEntityManager.persist(pEntidade);
			}
			aEntityManager.getTransaction().commit();
			resposta = true;
		} catch (Exception e) {
			if (aEntityManager.getTransaction().isActive() == false) {
				aEntityManager.getTransaction().begin();
			}
			aEntityManager.getTransaction().rollback();
			resposta = false;
		}
		return resposta;
	}

	public boolean alterar(T pEntidade) {
		boolean resposta = false;
		try {
			aEntityManager.getTransaction().begin();
			if (pEntidade != null) {
				aEntityManager.merge(pEntidade);
			}
			aEntityManager.getTransaction().commit();
			resposta = true;
		} catch (Exception e) {
			if (aEntityManager.getTransaction().isActive() == false) {
				aEntityManager.getTransaction().begin();
			}
			aEntityManager.getTransaction().rollback();
			resposta = false;
		}
		return resposta;
	}

	public boolean deletar(T pEntidade) {
		boolean resposta = false;
		try {
			aEntityManager.getTransaction().begin();
			aEntityManager.remove(pEntidade);
			aEntityManager.getTransaction().commit();
			resposta = true;
		} catch (Exception e) {
			if (aEntityManager.getTransaction().isActive() == false) {
				aEntityManager.getTransaction().begin();
			}
			aEntityManager.getTransaction().rollback();
			resposta = false;
		}
		return resposta;
	}

}