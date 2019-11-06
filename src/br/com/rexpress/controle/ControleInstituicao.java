package br.com.rexpress.controle;

import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.PersistenceException;

import org.primefaces.context.RequestContext;

import br.com.rexpress.dao.InstituicaoDAO;
import br.com.rexpress.dao.TurnoDAO;
import br.com.rexpress.rn.RNInstituicao;
import br.com.rexpress.util.MensagensUtil;
import br.com.rexpress.valuesObjects.Endereco;
import br.com.rexpress.valuesObjects.Instituicao;
import br.com.rexpress.valuesObjects.Rota;
import br.com.rexpress.valuesObjects.Turno;

@ManagedBean(name = "ControleInstituicao")
@SessionScoped
public class ControleInstituicao {
	// ----------------------------Delcaração de
	// Atributos-----------------------------------

	private InstituicaoDAO aInstituicaoDAO;
	private Instituicao aInstituicao;
	private Endereco aEndereco;
	private Turno aTurno1_1;
	private Turno aTurno1_2;
	private Turno aTurno2_1;
	private Turno aTurno2_2;
	private Turno aTurno3;
	private String descricaoInstituicao;
	private RNInstituicao aRNInstituicao;
	private Rota aRota;
	private Instituicao aInstituicaoSelecionado;
	private MensagensUtil mensagem;

	// ----------------------------Método
	// construtor-----------------------------------

	public ControleInstituicao() {
		aInstituicaoDAO = new InstituicaoDAO();
		aInstituicao = new Instituicao();
		aEndereco = new Endereco();
		aTurno1_1 = new Turno();
		aTurno1_2 = new Turno();
		aTurno2_1 = new Turno();
		aTurno2_2 = new Turno();
		aTurno3 = new Turno();
		aRNInstituicao = new RNInstituicao();
		aInstituicaoSelecionado = new Instituicao();
		mensagem = new MensagensUtil();
	}

	// ----------------------------Método para inserir, alterar e
	// excluir-----------------------------------

	public void inserirInstituicao() {
		try {
			aInstituicao.setRota(aRota);
			aInstituicao.setEndereco(aRNInstituicao.inserirEndereco(aEndereco));
			boolean commit = aInstituicaoDAO.inserir(aInstituicao);
			boolean commitTurnos = false;
			String tipo = aInstituicao.getTipo() ? "Creche" : "Escola";

			// --------Inserção das descrições dos turnos, de acordo com o tipo
			// da instituição (CRECHE ou ESCOLA)---------
			if (aInstituicao.getTipo().equals(false)) {
				aTurno1_1.setDescricao("CAFÉ DA MANHÃ");
				aTurno2_1.setDescricao("ALMOÇO");
				aTurno3.setDescricao("JANTAR");

				aInstituicao.addTurno(aTurno1_1);
				aInstituicao.addTurno(aTurno2_1);
				aInstituicao.addTurno(aTurno3);

				commitTurnos = aInstituicaoDAO.inserirTurnos(aInstituicao);
			}

			else if (aInstituicao.getTipo().equals(true)) {
				aTurno1_1.setDescricao("DESJEJUM");
				aTurno1_2.setDescricao("LANCHE DA MANHÃ");
				aTurno2_1.setDescricao("ALMOÇO");
				aTurno2_2.setDescricao("LANCHE DA TARDE");
				aTurno3.setDescricao("JANTAR");

				aInstituicao.addTurno(aTurno1_1);
				aInstituicao.addTurno(aTurno1_2);
				aInstituicao.addTurno(aTurno2_1);
				aInstituicao.addTurno(aTurno2_2);
				aInstituicao.addTurno(aTurno3);

				commitTurnos = aInstituicaoDAO.inserirTurnos(aInstituicao);

			}

			if ((commit == true) && (commitTurnos == true)) {
				mensagem.MensagemInfo("Sucesso!", tipo
						+ " inserida com sucesso.", "messageInserir");
			} else {
				mensagem.MensagemErro("Erro!",
						"Não foi possível inserir essa instituição",
						"messageInserir");
			}

		} catch (PersistenceException e) {
			mensagem.MensagemErro("Erro ao inserir a Instituição!",
					"Não foi possível inserir essa instituição",
					"messageInserir");
		} finally {

			aInstituicao = new Instituicao();

			aEndereco = new Endereco();
			aRota = new Rota();
			aTurno1_1 = new Turno();
			aTurno1_2 = new Turno();
			aTurno2_1 = new Turno();
			aTurno2_2 = new Turno();
			aTurno3 = new Turno();
		}
	}

	public void alterarInstituicao() {
		try {

			aInstituicaoSelecionado.setRota(aRota);
			aInstituicaoSelecionado.setEndereco(aRNInstituicao
					.inserirEndereco(aEndereco));
			boolean commit = aInstituicaoDAO.alterar(aInstituicaoSelecionado);
			boolean commitTurnos = false;
			String tipo = aInstituicaoSelecionado.getTipo() ? "Creche"
					: "Escola";

			// --------Inserção das descrições dos turnos, de acordo com o tipo
			// da instituição (CRECHE ou ESCOLA)---------
			if (aInstituicaoSelecionado.getTipo().equals(false)) {

				aInstituicaoSelecionado.addTurno(aTurno1_1);
				aInstituicaoSelecionado.addTurno(aTurno2_1);
				aInstituicaoSelecionado.addTurno(aTurno3);

				commitTurnos = aInstituicaoDAO
						.inserirTurnos(aInstituicaoSelecionado);

			}

			else if (aInstituicaoSelecionado.getTipo().equals(true)) {

				aInstituicaoSelecionado.addTurno(aTurno1_1);
				aInstituicaoSelecionado.addTurno(aTurno1_2);
				aInstituicaoSelecionado.addTurno(aTurno2_1);
				aInstituicaoSelecionado.addTurno(aTurno2_2);
				aInstituicaoSelecionado.addTurno(aTurno3);

				commitTurnos = aInstituicaoDAO
						.inserirTurnos(aInstituicaoSelecionado);

			}
			if ((commit == true) && (commitTurnos == true)) {
				mensagem.MensagemInfo("Sucesso!", "Instituição "
						+ aInstituicaoSelecionado.getNomeInstituicao()
						+ " alterada com sucesso", "menssageAlterar");

			} else {
				mensagem.MensagemErro("Erro ao alterar a Instituição!",
						"Erro ao alterar esta Instituição!", "menssageAlterar");
			}

		} catch (PersistenceException e) {
			// caso ocorra algum erro de persistência, irá apresentar a mensagem
			// de erro
			mensagem.MensagemErro("Erro ao alterar a Rota!",
					"Erro ao alterar esta Instituição!", "menssageAlterar");
		} finally {

			aInstituicaoSelecionado = new Instituicao();

			aEndereco = new Endereco();
			aRota = new Rota();
			aTurno1_1 = new Turno();
			aTurno1_2 = new Turno();
			aTurno2_1 = new Turno();
			aTurno2_2 = new Turno();
			aTurno3 = new Turno();
		}

	}

	public void deletarInstituicao(Instituicao pInstituicao) {
		try {
			boolean commit = aInstituicaoDAO.deletar(pInstituicao);
			if (commit == true) {
				mensagem.MensagemInfo(
						"Instituição" + pInstituicao.getNomeInstituicao()
								+ " excluída com sucesso!",
						"Instituição excluída com sucesso!", "messageDataTable");
			} else {
				mensagem.MensagemErro("Erro ao excluir a Instituição"
						+ pInstituicao.getNomeInstituicao(),
						"Ocorreu algum erro ao excluir a Instituição!",
						"messageDataTable");
			}
		} catch (Exception e) {
			mensagem.MensagemErro("Erro ao excluir a Instituição"
					+ pInstituicao.getNomeInstituicao(),
					"Ocorreu algum erro ao excluir a Instituição!",
					"messageDataTable");
		}
	}

	// ----------------------------Método que seleciona um item para
	// alteração-----------------------------------

	public Instituicao selecionarInstituicao(Instituicao pInstituicao) {
		try {
			aInstituicaoSelecionado = pInstituicao;
			aRota = aInstituicaoSelecionado.getRota();
			aEndereco = aInstituicaoSelecionado.getEndereco();
			if (aInstituicaoSelecionado.getTipo().equals(false)) {

				aTurno1_1 = listarCafeDaManha(pInstituicao);
				aTurno2_1 = listarAlmoco(pInstituicao);
				aTurno3 = listarJantar(pInstituicao);

			} else {

				aTurno1_1 = listarDesjejum(pInstituicao);
				aTurno1_2 = listarLancheManha(pInstituicao);
				aTurno2_1 = listarAlmoco(pInstituicao);
				aTurno2_2 = listarLancheTarde(pInstituicao);
				aTurno3 = listarJantar(pInstituicao);
			}

		} catch (Exception e) {
			// caso ocorra algum erro ao selecionar o item, irá apresentar a
			// mensagem de erro
			mensagem.MensagemErro("Erro ao selecionar a Instituicao",
					"Ocorreu algum erro na hora de selecionar a instituição!",
					"menssageDataTable");
		}
		return aInstituicaoSelecionado;
	}

	// ----------------------------Método para abrir o dialog de
	// alteração-----------------------------------

	public void alterarDialog() {

		HashMap<String, Object> opcoes = new HashMap<String, Object>();

		opcoes.put("modal", true);
		opcoes.put("contentWidth", 900);
		opcoes.put("contentHeight", 550);

		RequestContext.getCurrentInstance().openDialog("alterarInstituicao",
				opcoes, null);
	}

	// ----------------------------Método de
	// validação-----------------------------------

	public Instituicao validaInstituicao() {
		return aRNInstituicao.valida(descricaoInstituicao);
	}

	// ----------------------------Consultas-----------------------------------

	public List<Instituicao> listarInstituicoes() {
		return aInstituicaoDAO.listarTudo();
	}

	// ----------------------------Método para listar apenas as
	// escolas-----------------------------------

	public List<Instituicao> listarEscolas() {
		return aInstituicaoDAO.listarEscolas();
	}

	// ----------------------------Método para listar apenas as
	// creches-----------------------------------

	public List<Instituicao> listarCreches() {
		return aInstituicaoDAO.listarCreches();
	}

	// ---------------------------Consultas de
	// Turnos------------------------------
	public Turno listarCafeDaManha(Instituicao pInttituicao) {

		Turno cafeDaManha = new Turno();
		List<Turno> turnos = aInstituicaoDAO.listarCafeDaManha();
		for (int x = 0; x < turnos.size(); x++) {
			if (turnos.get(x).getInstituicao().getId()
					.equals(pInttituicao.getId())) {
				cafeDaManha = turnos.get(x);
			}
		}
		return cafeDaManha;
	}

	public Turno listarDesjejum(Instituicao pInttituicao) {

		Turno desjejum = new Turno();
		List<Turno> turnos = aInstituicaoDAO.listarDesjejum();
		for (int x = 0; x < turnos.size(); x++) {
			if (turnos.get(x).getInstituicao().getId()
					.equals(pInttituicao.getId())) {
				desjejum = turnos.get(x);
			}
		}
		return desjejum;
	}

	public Turno listarLancheManha(Instituicao pInttituicao) {

		Turno lancheManha = new Turno();
		List<Turno> turnos = aInstituicaoDAO.listarLancheManha();
		for (int x = 0; x < turnos.size(); x++) {
			if (turnos.get(x).getInstituicao().getId()
					.equals(pInttituicao.getId())) {
				lancheManha = turnos.get(x);
			}
		}
		return lancheManha;
	}

	public Turno listarAlmoco(Instituicao pInttituicao) {

		Turno almoco = new Turno();
		List<Turno> turnos = aInstituicaoDAO.listarAlmoco();
		for (int x = 0; x < turnos.size(); x++) {
			if (turnos.get(x).getInstituicao().getId()
					.equals(pInttituicao.getId())) {
				almoco = turnos.get(x);
			}
		}
		return almoco;
	}

	public Turno listarLancheTarde(Instituicao pInttituicao) {

		Turno lancheTarde = new Turno();
		List<Turno> turnos = aInstituicaoDAO.listarLancheTarde();
		for (int x = 0; x < turnos.size(); x++) {
			if (turnos.get(x).getInstituicao().getId()
					.equals(pInttituicao.getId())) {
				lancheTarde = turnos.get(x);
			}
		}
		return lancheTarde;
	}

	public Turno listarJantar(Instituicao pInttituicao) {

		Turno jantar = new Turno();
		List<Turno> turnos = aInstituicaoDAO.listarJantar();
		for (int x = 0; x < turnos.size(); x++) {
			if (turnos.get(x).getInstituicao().getId()
					.equals(pInttituicao.getId())) {
				jantar = turnos.get(x);
			}
		}
		return jantar;
	}

	// ----------------------------Getters and
	// Setters-----------------------------------

	public Instituicao getInstituicao() {
		return aInstituicao;
	}

	public void setInstituicao(Instituicao pInstituicao) {
		this.aInstituicao = pInstituicao;
	}

	public String getDescricaoInstituicao() {
		return descricaoInstituicao;
	}

	public void setDescricaoInstituicao(String descricaoInstituicao) {
		this.descricaoInstituicao = descricaoInstituicao;
	}

	public Endereco getaEndereco() {
		return aEndereco;
	}

	public void setaEndereco(Endereco aEndereco) {
		this.aEndereco = aEndereco;
	}

	public Rota getRota() {
		return aRota;
	}

	public void setRota(Rota rota) {
		this.aRota = rota;
	}

	public Instituicao getInstituicaoSelecionado() {
		return aInstituicaoSelecionado;
	}

	public void setInstituicaoSelecionado(Instituicao aInstituicaoSelecionado) {
		this.aInstituicaoSelecionado = aInstituicaoSelecionado;
	}

	public Turno getTurno1_1() {
		return aTurno1_1;
	}

	public void setTurno1_1(Turno aTurno1_1) {
		this.aTurno1_1 = aTurno1_1;
	}

	public Turno getTurno1_2() {
		return aTurno1_2;
	}

	public void setTurno1_2(Turno aTurno1_2) {
		this.aTurno1_2 = aTurno1_2;
	}

	public Turno getTurno2_1() {
		return aTurno2_1;
	}

	public void setTurno2_1(Turno aTurno2_1) {
		this.aTurno2_1 = aTurno2_1;
	}

	public Turno getTurno2_2() {
		return aTurno2_2;
	}

	public void setTurno2_2(Turno aTurno2_2) {
		this.aTurno2_2 = aTurno2_2;
	}

	public Turno getTurno3() {
		return aTurno3;
	}

	public void setTurno3(Turno aTurno3) {
		this.aTurno3 = aTurno3;
	}
}