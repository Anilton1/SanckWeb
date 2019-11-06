package br.com.rexpress.controle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.rexpress.dao.InstituicaoDAO;
import br.com.rexpress.dao.MapaDAO;
import br.com.rexpress.dao.MerendaMapaHistDAO;
import br.com.rexpress.valuesObjects.Instituicao;
import br.com.rexpress.valuesObjects.Mapa;
import br.com.rexpress.valuesObjects.Merenda;
import br.com.rexpress.valuesObjects.MerendaMapaHist;
import br.com.rexpress.valuesObjects.Rota;
import br.com.rexpress.valuesObjects.Turno;
import br.com.rexpress.valuesObjects.TurnoMapa;

@ManagedBean(name = "ControleMapa")
@ViewScoped
public class ControleMapa {
	private Merenda cafeDaManha;
	private Merenda lancheManha;
	private Merenda almoco;
	private Merenda lancheTarde;
	private Merenda jantar;
	private Mapa aMapa;
	private TurnoMapa turnoCafeDaManha;
	private TurnoMapa turnoLancheManha;
	private TurnoMapa turnoAlmoco;
	private TurnoMapa turnoLancheTarde;
	private TurnoMapa turnoJantar;
	private MapaDAO aMapaDAO = new MapaDAO();
	private InstituicaoDAO aInstituicaoDAO = new InstituicaoDAO();
	private List<MerendaMapaHist> merendas = new ArrayList<MerendaMapaHist>();
	private MerendaMapaHist merendaHist = new MerendaMapaHist();
	private TurnoMapa turnoMapa = new TurnoMapa();

	public ControleMapa() {
		cafeDaManha = new Merenda();
		lancheManha = new Merenda();
		almoco = new Merenda();
		lancheTarde = new Merenda();
		jantar = new Merenda();
		aMapa = new Mapa();
		turnoCafeDaManha = new TurnoMapa();
		turnoLancheManha = new TurnoMapa();
		turnoAlmoco = new TurnoMapa();
		turnoLancheTarde = new TurnoMapa();
		turnoJantar = new TurnoMapa();
	}

	public void inserirMapaGeral() {
		MerendaMapaHistDAO daoMerenda = new MerendaMapaHistDAO();

		List<Instituicao> instituicaoPendente = new ArrayList<Instituicao>();
		List<MerendaMapaHist> merendaMapa = new ArrayList<MerendaMapaHist>();
		List<MerendaMapaHist> merendasAdd = new ArrayList<MerendaMapaHist>();
		Date data = (aMapa.getDataroteirizacao());
		boolean tipo = aMapa.getTipo();

		if (!aMapa.getTipo()) {
			instituicaoPendente = aInstituicaoDAO.listarEscolas();
		} else if (aMapa.getTipo()) {
			instituicaoPendente = aInstituicaoDAO.listarCreches();
		}

		List<Mapa> mapasExistentes = aMapaDAO.mapaPorData(aMapa.getDataroteirizacao());

		for (Mapa mapa : mapasExistentes) {
			for (Instituicao instituicao : instituicaoPendente) {
				if (instituicao.getId().equals(mapa.getInstituicao().getId())) {
					instituicaoPendente.remove(instituicao);
					break;
				}
			}
		}

		for (Instituicao instituicao : instituicaoPendente) {
			merendas.add(merendaHist.valueOf(cafeDaManha));
			merendas.add(merendaHist.valueOf(almoco));
			merendas.add(merendaHist.valueOf(jantar));

			if (this.aMapa.getTipo()) {
				merendas.add(merendaHist.valueOf(lancheManha));
			}

			if (this.aMapa.getTipo()) {
				merendas.add(merendaHist.valueOf(lancheTarde));
			}

			aMapa.setDataProcessamento(new Date());
			aMapa.setRota(instituicao.getRota());

			aMapa.setInstituicao(instituicao);
			aMapa.setTurnoMapas(turnoMapa.valueOf(instituicao.getTurnos()));

			aMapaDAO.inserirMerendas(aMapa, merendas);

			for (MerendaMapaHist merenda : merendas) {
				merendaMapa = daoMerenda.listarSeExiste(merenda);
				if (!merendaMapa.isEmpty()) {
					merendasAdd.addAll(merendaMapa);
				}
			}

			aMapaDAO.inserir(aMapa);

			aMapaDAO.inserirTurnos(aMapa, merendasAdd);

			aMapa = new Mapa();

			aMapa.setDataroteirizacao(data);
			aMapa.setTipo(tipo);

			merendas = new ArrayList<MerendaMapaHist>();

			merendasAdd = new ArrayList<MerendaMapaHist>();

		}
		aMapa = new Mapa();
		cafeDaManha = new Merenda();
		almoco = new Merenda();
		jantar = new Merenda();

		if (lancheManha != null) {
			lancheManha = new Merenda();
		}
		if (lancheTarde != null) {
			lancheTarde = new Merenda();
		}
	}

	public void inserirMapaPorRota() {

		MerendaMapaHistDAO daoMerenda = new MerendaMapaHistDAO();

		List<Instituicao> instituicaoPendente = new ArrayList<Instituicao>();
		List<MerendaMapaHist> merendaMapa = new ArrayList<MerendaMapaHist>();
		List<MerendaMapaHist> merendasAdd = new ArrayList<MerendaMapaHist>();
		Date data = (aMapa.getDataroteirizacao());
		Rota rota = aMapa.getRota();

		instituicaoPendente = aInstituicaoDAO.listarInstituicaoPorRota(aMapa.getRota());

		List<Mapa> mapasExistentes = aMapaDAO.mapaPorData(aMapa.getDataroteirizacao());

		for (Mapa mapa : mapasExistentes) {
			for (Instituicao instituicao : instituicaoPendente) {
				if (instituicao.getId().equals(mapa.getInstituicao().getId())) {
					instituicaoPendente.remove(instituicao);
					break;
				}
			}
		}

		for (Instituicao instituicao : instituicaoPendente) {
			merendas.add(merendaHist.valueOf(cafeDaManha));
			merendas.add(merendaHist.valueOf(almoco));
			merendas.add(merendaHist.valueOf(jantar));

			if (this.aMapa.getTipo()) {
				merendas.add(merendaHist.valueOf(lancheManha));
			}

			if (this.aMapa.getTipo()) {
				merendas.add(merendaHist.valueOf(lancheTarde));
			}

			aMapa.setDataProcessamento(new Date());
			aMapa.setTipo(instituicao.getTipo());

			aMapa.setInstituicao(instituicao);
			aMapa.setTurnoMapas(turnoMapa.valueOf(instituicao.getTurnos()));

			aMapaDAO.inserirMerendas(aMapa, merendas);

			for (MerendaMapaHist merenda : merendas) {
				merendaMapa = daoMerenda.listarSeExiste(merenda);
				if (!merendaMapa.isEmpty()) {
					merendasAdd.addAll(merendaMapa);
				}
			}

			aMapaDAO.inserir(aMapa);

			aMapaDAO.inserirTurnos(aMapa, merendasAdd);

			aMapa = new Mapa();

			aMapa.setDataroteirizacao(data);
			aMapa.setRota(rota);

			merendas = new ArrayList<MerendaMapaHist>();

			merendasAdd = new ArrayList<MerendaMapaHist>();

		}
		aMapa = new Mapa();
		cafeDaManha = new Merenda();
		almoco = new Merenda();
		jantar = new Merenda();

		if (lancheManha != null) {
			lancheManha = new Merenda();
		}
		if (lancheTarde != null) {
			lancheTarde = new Merenda();
		}

	}

	public void inserirMapaPorInstituicao() {
		MerendaMapaHistDAO daoMerenda = new MerendaMapaHistDAO();

		List<MerendaMapaHist> merendaMapa = new ArrayList<MerendaMapaHist>();
		List<MerendaMapaHist> merendasAdd = new ArrayList<MerendaMapaHist>();
		List<TurnoMapa> turnos = new ArrayList<TurnoMapa>();

		turnoCafeDaManha.setDescricao("CAFÉ DA MANHÃ");
		turnoAlmoco.setDescricao("ALMOÇO");
		turnoJantar.setDescricao("JANTAR");

		turnos.add(turnoCafeDaManha);
		turnos.add(turnoAlmoco);
		turnos.add(turnoJantar);

		merendas.add(merendaHist.valueOf(cafeDaManha));
		merendas.add(merendaHist.valueOf(almoco));
		merendas.add(merendaHist.valueOf(jantar));

		if (this.aMapa.getTipo()) {
			turnoLancheManha.setDescricao("LANCHE DA MANHÃ");
			turnos.add(turnoLancheManha);
			merendas.add(merendaHist.valueOf(lancheManha));
		}

		if (this.aMapa.getTipo()) {
			turnoLancheManha.setDescricao("LANCHE DA TARDE");
			turnos.add(turnoLancheTarde);
			merendas.add(merendaHist.valueOf(lancheTarde));
		}

		aMapa.setDataProcessamento(new Date());

		aMapa.setTurnoMapas(turnos);

		aMapaDAO.inserirMerendas(aMapa, merendas);

		for (MerendaMapaHist merenda : merendas) {
			merendaMapa = daoMerenda.listarSeExiste(merenda);
			if (!merendaMapa.isEmpty()) {
				merendasAdd.addAll(merendaMapa);
			}
		}

		aMapaDAO.inserir(aMapa);

		aMapaDAO.inserirTurnos(aMapa, merendasAdd);

		aMapa = new Mapa();

		merendas = new ArrayList<MerendaMapaHist>();

		merendasAdd = new ArrayList<MerendaMapaHist>();

		cafeDaManha = new Merenda();
		almoco = new Merenda();
		jantar = new Merenda();

		if (lancheManha != null) {
			lancheManha = new Merenda();
		}
		if (lancheTarde != null) {
			lancheTarde = new Merenda();
		}
	}

	public List<Mapa> escolasMapeadas() {
		return aMapaDAO.listarEscolasMapeadas();
	}

	public List<Mapa> crechesMapeadas() {
		return aMapaDAO.listarCrechesMapeadas();
	}

	public void excluirMapa(Mapa pMapa) {
		aMapaDAO.deletar(pMapa);
	}

	public Merenda getCafeDaManha() {
		return cafeDaManha;
	}

	public void setCafeDaManha(Merenda cafeDaManha) {
		this.cafeDaManha = cafeDaManha;
	}

	public Merenda getLancheManha() {
		return lancheManha;
	}

	public void setLancheManha(Merenda lancheManha) {
		this.lancheManha = lancheManha;
	}

	public Merenda getAlmoco() {
		return almoco;
	}

	public void setAlmoco(Merenda almoco) {
		this.almoco = almoco;
	}

	public Merenda getLancheTarde() {
		return lancheTarde;
	}

	public TurnoMapa getTurnoCafeDaManha() {
		return turnoCafeDaManha;
	}

	public void setTurnoCafeDaManha(TurnoMapa turnoCafeDaManha) {
		this.turnoCafeDaManha = turnoCafeDaManha;
	}

	public TurnoMapa getTurnoLancheManha() {
		return turnoLancheManha;
	}

	public void setTurnoLancheManha(TurnoMapa turnoLancheManha) {
		this.turnoLancheManha = turnoLancheManha;
	}

	public TurnoMapa getTurnoAlmoco() {
		return turnoAlmoco;
	}

	public void setTurnoAlmoco(TurnoMapa turnoAlmoco) {
		this.turnoAlmoco = turnoAlmoco;
	}

	public TurnoMapa getTurnoLancheTarde() {
		return turnoLancheTarde;
	}

	public void setTurnoLancheTarde(TurnoMapa turnoLancheTarde) {
		this.turnoLancheTarde = turnoLancheTarde;
	}

	public TurnoMapa getTurnoJantar() {
		return turnoJantar;
	}

	public void setTurnoJantar(TurnoMapa turnoJantar) {
		this.turnoJantar = turnoJantar;
	}

	public void setLancheTarde(Merenda lancheTarde) {
		this.lancheTarde = lancheTarde;
	}

	public Merenda getJantar() {
		return jantar;
	}

	public void setJantar(Merenda jantar) {
		this.jantar = jantar;
	}

	public Mapa getMapa() {
		return aMapa;
	}

	public void setMapa(Mapa aMapa) {
		this.aMapa = aMapa;
	}

	public void getTurnosInstituicao() {
		getAjaxTurnoCafeDaManha();
		getAjaxTurnoAlmoco();
		getAjaxTurnoJantar();
		if (aMapa.getInstituicao().getTipo()) {
			getAjaxTurnoLancheManha();
			getAjaxTurnoLancheTarde();
		}

	}

	public void getAjaxTurnoCafeDaManha() {
		for (Turno turno : this.aMapa.getInstituicao().getTurnos()) {
			if (turno.getDescricao().equals("CAFÉ DA MANHÃ") || turno.getDescricao().equals("DESJEJUM")) {
				turnoCafeDaManha.setQtdAlunos1(turno.getQtdAlunos1());
				turnoCafeDaManha.setQtdAlunos2(turno.getQtdAlunos2());
				turnoCafeDaManha.setQtdAlunos3(turno.getQtdAlunos3());
				turnoCafeDaManha.setDescricao(turno.getDescricao());
			}
		}
	}

	public void getAjaxTurnoAlmoco() {
		for (Turno turno : this.aMapa.getInstituicao().getTurnos()) {
			if (turno.getDescricao().equals("ALMOÇO")) {
				turnoAlmoco.setQtdAlunos1(turno.getQtdAlunos1());
				turnoAlmoco.setQtdAlunos2(turno.getQtdAlunos2());
				turnoAlmoco.setQtdAlunos3(turno.getQtdAlunos3());
				turnoAlmoco.setDescricao(turno.getDescricao());
			}
		}
	}

	public void getAjaxTurnoJantar() {
		for (Turno turno : this.aMapa.getInstituicao().getTurnos()) {
			if (turno.getDescricao().equals("JANTAR")) {
				turnoJantar.setQtdAlunos1(turno.getQtdAlunos1());
				turnoJantar.setQtdAlunos2(turno.getQtdAlunos2());
				turnoJantar.setQtdAlunos3(turno.getQtdAlunos3());
				turnoJantar.setDescricao(turno.getDescricao());
			}
		}
	}

	public void getAjaxTurnoLancheManha() {
		for (Turno turno : this.aMapa.getInstituicao().getTurnos()) {
			if (turno.getDescricao().equals("LANCHE DA MANHÃ")) {
				turnoLancheManha.setQtdAlunos1(turno.getQtdAlunos1());
				turnoLancheManha.setQtdAlunos2(turno.getQtdAlunos2());
				turnoLancheManha.setQtdAlunos3(turno.getQtdAlunos3());
				turnoLancheManha.setDescricao(turno.getDescricao());
			}
		}
	}

	public Turno getAjaxTurnoLancheTarde() {
		for (Turno turno : this.aMapa.getInstituicao().getTurnos()) {
			if (turno.getDescricao().equals("LANCHE DA TARDE")) {
				turnoLancheTarde.setQtdAlunos1(turno.getQtdAlunos1());
				turnoLancheTarde.setQtdAlunos2(turno.getQtdAlunos2());
				turnoLancheTarde.setQtdAlunos3(turno.getQtdAlunos3());
				turnoLancheTarde.setDescricao(turno.getDescricao());
			}
		}
		return null;
	}
}
