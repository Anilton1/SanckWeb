package br.com.rexpress.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.rexpress.util.DAOGenerico;
import br.com.rexpress.util.EntityManagerUtil;
import br.com.rexpress.valuesObjects.Descartavel;
import br.com.rexpress.valuesObjects.Instituicao;
import br.com.rexpress.valuesObjects.ItemMapa;
import br.com.rexpress.valuesObjects.ItemMapaHist;
import br.com.rexpress.valuesObjects.Mapa;
import br.com.rexpress.valuesObjects.MerendaMapaHist;
import br.com.rexpress.valuesObjects.TurnoMapa;

public class MapaDAO extends DAOGenerico<Mapa> {
	private EntityManager aEntityManager;

	public MapaDAO() {
	}

	@SuppressWarnings("unchecked")
	public List<Mapa> listarEscolasMapeadas() {
		aEntityManager = EntityManagerUtil.getEntityManager();
		Query q = aEntityManager.createNamedQuery("Mapa.escolaMapeada");
		List<Mapa> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}
	
	@SuppressWarnings("unchecked")
	public List<Mapa> listarCrechesMapeadas() {
		aEntityManager = EntityManagerUtil.getEntityManager();
		Query q = aEntityManager.createNamedQuery("Mapa.crecheMapeada");
		List<Mapa> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}

	@SuppressWarnings("unchecked")
	public List<Mapa> listarTudo() {
		aEntityManager = EntityManagerUtil.getEntityManager();
		Query q = aEntityManager.createNamedQuery("Mapa.findAll");
		List<Mapa> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}

	@SuppressWarnings("unchecked")
	public List<Mapa> listarSeExisteInstituicaoPorData(Mapa pMapa) {
		aEntityManager = EntityManagerUtil.getEntityManager();
		Query q = aEntityManager.createNamedQuery("Mapa.seExisteInsituicaoPorData");
		q.setParameter("id", pMapa.getInstituicao().getId());
		q.setParameter("data", pMapa.getDataroteirizacao());
		List<Mapa> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}
	
	@SuppressWarnings("unchecked")
	public List<Mapa> mapaPorData(Date pDataMapa) {
		aEntityManager = EntityManagerUtil.getEntityManager();
		Query q = aEntityManager.createNamedQuery("Mapa.mapaPorData");
		q.setParameter("data", pDataMapa);
		List<Mapa> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}

	public void inserirTurnos(Mapa pMapa, List<MerendaMapaHist> pMerendas) {
		TurnoMapaDAO dao = new TurnoMapaDAO();
		ItemMapaDAO itemMapaDAO = new ItemMapaDAO();
		ItemMapa itemMapa = new ItemMapa();
		Mapa mapa = new Mapa();
		MerendaMapaHist merendas = new MerendaMapaHist();
		int i = 0;
		for (TurnoMapa turnos : pMapa.getTurnoMapas()) {
			if (turnos != null) {
				mapa = listarSeExisteInstituicaoPorData(pMapa).get(0);
				merendas = pMerendas.get(i);
				turnos.setMapa(mapa);
				turnos.setMerendaMapaHist(merendas);
				dao.inserir(turnos);
				if (merendas != null) {
					for (ItemMapaHist item : merendas.getItemMapaHists()) {
						turnos = dao.listarSeExiste(turnos);
						itemMapa.setTurnoMapa(turnos);
						itemMapa.setItemMapaHist(item);
						if (item.getTipo()) {
							itemMapa.setValorTotal1((turnos.getQtdAlunos1() * item.getPercapita()) / item.getGrama());
							itemMapa.setValorTotal2((turnos.getQtdAlunos2() * item.getPercapita()) / item.getGrama());
							itemMapa.setValorTotal3((turnos.getQtdAlunos3() * item.getPercapita()) / item.getGrama());
						} else {
							itemMapa.setValorTotal1((turnos.getQtdAlunos1() * item.getPercapita()));
							itemMapa.setValorTotal2((turnos.getQtdAlunos2() * item.getPercapita()));
							itemMapa.setValorTotal3((turnos.getQtdAlunos3() * item.getPercapita()));
						}
						itemMapaDAO.inserir(itemMapa);
						itemMapa = new ItemMapa();
					}
				}
			}
			i += 1;
		}

	}

	public void inserirMerendas(Mapa pMapa, List<MerendaMapaHist> pMerendas) {
		ItemMapaHistDAO daoItem = new ItemMapaHistDAO();
		MerendaMapaHistDAO daoMerenda = new MerendaMapaHistDAO();
		List<ItemMapaHist> itens = new ArrayList<ItemMapaHist>();
		List<ItemMapaHist> consultaItem = new ArrayList<ItemMapaHist>();
		List<MerendaMapaHist> consultaMerenda = new ArrayList<MerendaMapaHist>();
		List<Descartavel> descartavel = new ArrayList<Descartavel>();
		
		for (MerendaMapaHist merenda : pMerendas) {
			itens = new ArrayList<ItemMapaHist>();
			for (ItemMapaHist item : merenda.getItemMapaHists()) {				
				if (item != null) {
					consultaItem = daoItem.listarSeExiste(item);
					if (consultaItem.isEmpty()) {
						daoItem.inserir(item);
					} else if (!consultaItem.isEmpty()) {
						if (!itens.containsAll(consultaItem))
							itens.addAll(consultaItem);
					}
					if (consultaItem.isEmpty()) {
						itens.addAll(daoItem.listarSeExiste(item));
					}
				}
				consultaItem = new ArrayList<ItemMapaHist>();
			}
		
			merenda.setItemMapaHists(itens);
			merenda.setId(null);
			consultaMerenda = daoMerenda.listarSeExiste(merenda);
			if (consultaMerenda.isEmpty()) {
				daoMerenda.inserir(merenda);
			} 
		}
	}
}