package br.com.rexpress.controle;

import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.PersistenceException;

import org.primefaces.context.RequestContext;

import br.com.rexpress.dao.RotaDAO;
import br.com.rexpress.rn.RNRota;
import br.com.rexpress.util.MensagensUtil;
import br.com.rexpress.valuesObjects.Rota;

@ManagedBean(name = "ControleRota")
@SessionScoped
public class ControleRota {
	/**
	 * 
	 */
	// ----------------------------Delcaração de
	// Atributos-----------------------------------
	private RotaDAO aRotaDAO;
	private Rota aRota;
	private String descricaoRota;
	private RNRota aRNRota;
	private MensagensUtil mensagem;
	private Rota aRotaSelecionado;

	// ----------------------------Método
	// construtor-----------------------------------

	public ControleRota() {
		aRotaDAO = new RotaDAO();
		aRota = new Rota();
		aRNRota = new RNRota();
		aRotaSelecionado = new Rota();
		mensagem = new MensagensUtil();
	}

	// ----------------------------Método para inserir, alterar e
	// excluir-----------------------------------

	public void inserirRota() {
		try {
			boolean commit = aRotaDAO.inserir(aRota);

			// se não der nenhum erro, vai retornar a mensagem de sucesso!
			if (commit == true) {
				mensagem.MensagemInfo("Sucesso!", "Rota " + aRota.getNome()
						+ " inserida com sucesso", "messageInserir");
			} else {
				mensagem.MensagemErro("Erro ao salvar a Rota!",
						"Erro ao inserir a Rota!", "menssageInserir");
			}

		} catch (PersistenceException e) {
			// caso ocorra algum erro de persistência, irá apresentar a mensagem
			// de erro
			mensagem.MensagemErro("Erro ao salvar a Rota!",
					"Erro ao inserir a Rota!", "menssageInserir");
		} finally {
			aRota = new Rota();
		}
	}

	public void alterarRota() {
		try {
			boolean commit = aRotaDAO.alterar(aRotaSelecionado);
			if (commit == true) {
				mensagem.MensagemInfo("Sucesso!",
						"Rota " + aRotaSelecionado.getNome()
								+ " alterada com sucesso", "menssageAlterar");
			} else {
				mensagem.MensagemErro("Erro ao alterar a Rota!",
						"Erro ao alterar esta Rota!", "menssageAlterar");
			}

		} catch (PersistenceException e) {
			// caso ocorra algum erro de persistência, irá apresentar a mensagem
			// de erro
			mensagem.MensagemErro("Erro ao alterar a Rota!",
					"Erro ao alterar esta Rota!", "menssageAlterar");
		} finally {
			aRotaSelecionado = new Rota();
		}
	}

	public void deletarRota(Rota pRota) {
		try {
			boolean commit = aRotaDAO.deletar(pRota);
			if (commit == true) {
				mensagem.MensagemInfo("Sucesso!", "Rota " + pRota.getNome()
						+ " excluída com sucesso", "messageInserir");
			} else {
				mensagem.MensagemErro(
						"Erro ao excluir a Rota!",
						"Erro ao excluir esta Rota! Talvez ela esteja sendo usada por alguma instituição.",
						"menssageInserir");
			}

		} catch (PersistenceException e) {
			// caso ocorra algum erro de persistência, irá apresentar a mensagem
			// de erro
			mensagem.MensagemErro(
					"Erro ao excluir a Rota!",
					"Erro ao excluir esta Rota! Talvez ela esteja sendo usada por alguma instituição.",
					"menssageInserir");
		}
	}

	// ----------------------------Método que seleciona um item para
	// alteração-----------------------------------

	public Rota selecionarRota(Rota pRota) {
		try {
			aRotaSelecionado = pRota;
		} catch (Exception e) {
			// caso ocorra algum erro ao selecionar o item, irá apresentar a
			// mensagem de erro
			mensagem.MensagemErro("Erro ao selecionar a Rota",
					"Ocorreu algum erro na hora de selecionar a Rota!",
					"menssageDataTable");
		}
		return aRotaSelecionado;
	}

	// ----------------------------Método para abrir o dialog de
	// alteração-----------------------------------

	public void alterarDialog() {

		HashMap<String, Object> opcoes = new HashMap<String, Object>();

		opcoes.put("modal", true);
		opcoes.put("contentWidth", 900);

		RequestContext.getCurrentInstance().openDialog("alterarRota", opcoes,
				null);
	}

	// ----------------------------Método de
	// validação-----------------------------------

	public Rota validaRota() {
		return aRNRota.valida(descricaoRota);
	}

	// ----------------------------Consultas-----------------------------------

	public List<Rota> listarRotas() {
		return aRotaDAO.listarTudo();
	}

	// ----------------------------Getters and
	// Setters-----------------------------------

	public Rota getRota() {
		return aRota;
	}

	public void setRota(Rota pRota) {
		this.aRota = pRota;
	}

	public Rota getaRotaSelecionado() {
		return aRotaSelecionado;
	}

	public void setaRotaSelecionado(Rota aRotaSelecionado) {
		this.aRotaSelecionado = aRotaSelecionado;
	}

}