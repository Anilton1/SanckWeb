package br.com.rexpress.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.rexpress.util.DAOGenerico;
import br.com.rexpress.util.EntityManagerUtil;
import br.com.rexpress.valuesObjects.Instituicao;
import br.com.rexpress.valuesObjects.Rota;
import br.com.rexpress.valuesObjects.Turno;

public class InstituicaoDAO extends DAOGenerico<Instituicao> {
	private EntityManager aEntityManager;

	public InstituicaoDAO() {
	}

	@SuppressWarnings("unchecked")
	public List<Instituicao> listarTudo() {
		aEntityManager = EntityManagerUtil.getEntityManager();
		Query q = aEntityManager.createNamedQuery("Instituicao.findAll");
		List<Instituicao> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}

	@SuppressWarnings("unchecked")
	public List<Instituicao> listarInstituicaoPorRota(Rota pRota){
		aEntityManager = EntityManagerUtil.getEntityManager();
		Query q = aEntityManager.createNamedQuery("Instituicao.instituicaoPorRota");
		q.setParameter("rota", pRota.getId());
		List<Instituicao> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}
	
	@SuppressWarnings("unchecked")
	public Instituicao listarInstituicaoPorNome(Instituicao pInstituicao){
		aEntityManager = EntityManagerUtil.getEntityManager();
		Query q = aEntityManager.createNamedQuery("Instituicao.instituicaoPorNome");
		q.setParameter("nome", pInstituicao.getNomeInstituicao());
		List<Instituicao> retornoConsulta = q.getResultList();
		return retornoConsulta.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Instituicao> listarEscolas() {
		aEntityManager = EntityManagerUtil.getEntityManager();

		Query q = aEntityManager.createNamedQuery("Instituicao.listarEscolas");
		List<Instituicao> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}

	@SuppressWarnings("unchecked")
	public List<Instituicao> listarCreches() {
		aEntityManager = EntityManagerUtil.getEntityManager();

		Query q = aEntityManager.createNamedQuery("Instituicao.listarCreches");
		List<Instituicao> retornoConsulta = q.getResultList();

		return retornoConsulta;
	}

	@SuppressWarnings("unchecked")
	public List<Turno> listarCafeDaManha() {
		aEntityManager = EntityManagerUtil.getEntityManager();

		Query q = aEntityManager.createNamedQuery("Turno.turnoA");
		List<Turno> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}

	@SuppressWarnings("unchecked")
	public List<Turno> listarDesjejum() {
		aEntityManager = EntityManagerUtil.getEntityManager();

		Query q = aEntityManager.createNamedQuery("Turno.turnoAA");
		List<Turno> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}

	@SuppressWarnings("unchecked")
	public List<Turno> listarLancheManha() {
		aEntityManager = EntityManagerUtil.getEntityManager();

		Query q = aEntityManager.createNamedQuery("Turno.turnoAAA");
		List<Turno> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}

	@SuppressWarnings("unchecked")
	public List<Turno> listarAlmoco() {
		aEntityManager = EntityManagerUtil.getEntityManager();

		Query q = aEntityManager.createNamedQuery("Turno.turnoB");
		List<Turno> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}

	@SuppressWarnings("unchecked")
	public List<Turno> listarLancheTarde() {
		aEntityManager = EntityManagerUtil.getEntityManager();

		Query q = aEntityManager.createNamedQuery("Turno.turnoBB");
		List<Turno> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}

	@SuppressWarnings("unchecked")
	public List<Turno> listarJantar() {
		aEntityManager = EntityManagerUtil.getEntityManager();

		Query q = aEntityManager.createNamedQuery("Turno.turnoC");
		List<Turno> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}

	public boolean inserirTurnos(Instituicao pInstituicao) {
		TurnoDAO dao = new TurnoDAO();
		boolean resposta = true;
		for (Turno turnos : pInstituicao.getTurnos()) {
			if (turnos != null) {
				turnos.setInstituicao(pInstituicao);
				resposta = dao.inserir(turnos);
				if(resposta == false){
					return resposta;
				}
			}
		}
		return resposta;

	}
}
