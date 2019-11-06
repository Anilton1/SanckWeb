package br.com.rexpress.controle;

import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.PersistenceException;

import org.primefaces.context.RequestContext;

import br.com.rexpress.dao.DescartavelDAO;
import br.com.rexpress.util.MensagensUtil;
import br.com.rexpress.valuesObjects.Descartavel;

@ManagedBean(name = "ControleDescartavel")
@SessionScoped
public class ControleDescartavel {
	/**
	 * 
	 */
	// ----------------------------Delcara��o de
	// Atributos-----------------------------------
	private DescartavelDAO aDescartavelDAO;
	private Descartavel aDescartavel;
	private MensagensUtil mensagem;
	private Descartavel aDescartavelSelecionado;

	// ----------------------------M�todo
	// construtor-----------------------------------

	public ControleDescartavel() {
		aDescartavelDAO = new DescartavelDAO();
		aDescartavel = new Descartavel();
		aDescartavelSelecionado = new Descartavel();
		mensagem = new MensagensUtil();
	}

	// ----------------------------M�todo para inserir, alterar e
	// excluir-----------------------------------

	public void inserirDescartavel() {
		try {
			boolean commit = aDescartavelDAO.inserir(aDescartavel);

			// se n�o der nenhum erro, vai retornar a mensagem de sucesso!
			if (commit == true) {
				mensagem.MensagemInfo("Sucesso!",
						"Descart�vel " + aDescartavel.getDescricao()
								+ " inserido com sucesso", "messageInserir");
			} else {
				mensagem.MensagemErro("Erro ao salvar o Descart�vel!",
						"Erro ao inserir um Descart�vel!", "menssageInserir");
			}
		} catch (PersistenceException e) {
			// caso ocorra algum erro de persist�ncia, ir� apresentar a mensagem
			// de erro
			mensagem.MensagemErro("Erro ao salvar a Descart�vel!",
					"Erro ao inserir um Descart�vel!", "menssageInserir");
		} finally {
			aDescartavel = new Descartavel();
		}
	}

	public void alterarDescartavel() {
		try {
			boolean commit = aDescartavelDAO.alterar(aDescartavelSelecionado);
			if (commit == true) {
				mensagem.MensagemInfo("Sucesso!", "Descart�vel "
						+ aDescartavelSelecionado.getDescricao()
						+ " alterado com sucesso", "menssageAlterar");
			} else {
				mensagem.MensagemErro("Erro ao alterar o Descart�vel!",
						"Erro ao alterar este Descart�vel!", "menssageAlterar");
			}
		} catch (PersistenceException e) {
			// caso ocorra algum erro de persist�ncia, ir� apresentar a mensagem
			// de erro
			mensagem.MensagemErro("Erro ao alterar o Descart�vel!",
					"Erro ao alterar este Descart�vel!", "menssageAlterar");
		}finally {
			aDescartavelSelecionado = new Descartavel();
		}
	}

	public void deletarDescartavel(Descartavel pDescartavel) {
		try {
			boolean commit = aDescartavelDAO.deletar(pDescartavel);
			
			if(commit == true){
				mensagem.MensagemInfo("Sucesso!",
					"Descart�vel " + pDescartavel.getDescricao()
							+ " excluido com sucesso", "messageInserir");
			}else{
				mensagem.MensagemErro(
						"Erro ao excluir o Descart�vel!",
						"Erro ao excluir este Descart�vel! Talvez ela esteja sendo usado por alguma Merenda.",
						"menssageInserir");
			}
		} catch (PersistenceException e) {
			// caso ocorra algum erro de persist�ncia, ir� apresentar a mensagem
			// de erro
			mensagem.MensagemErro(
					"Erro ao excluir o Descart�vel!",
					"Erro ao excluir este Descart�vel! Talvez ela esteja sendo usada por alguma Merenda.",
					"menssageInserir");
		}
	}

	// ----------------------------M�todo que seleciona um item para
	// altera��o-----------------------------------

	public Descartavel selecionarDescartavel(Descartavel pDescartavel) {
		try {
			aDescartavelSelecionado = pDescartavel;
		} catch (Exception e) {
			// caso ocorra algum erro ao selecionar o item, ir� apresentar a
			// mensagem de erro
			mensagem.MensagemErro("Erro ao selecionar o Descart�vel",
					"Ocorreu algum erro na hora de selecionar o Descart�vel!",
					"menssageDataTable");
		}
		return aDescartavelSelecionado;
	}

	// ----------------------------M�todo para abrir o dialog de
	// altera��o-----------------------------------

	public void alterarDialog() {

		HashMap<String, Object> opcoes = new HashMap<String, Object>();

		opcoes.put("modal", true);
		opcoes.put("contentWidth", 900);

		RequestContext.getCurrentInstance().openDialog("alterarDescartavel",
				opcoes, null);
	}

	// ----------------------------M�todo de
	// valida��o-----------------------------------

	// public Descartavel validaDescartavel() {
	// return aRNDescartavel.valida(descricaoDescartavel);
	// }

	// ----------------------------Consultas-----------------------------------

	public List<Descartavel> listarDescartaveis() {
		return aDescartavelDAO.listarTudo();
	}

	// public Descartavel pesquisaPorId(Descartavel pDescartavel) {
	// return aDescartavelDAO.buscarPorId(pDescartavel);
	// }

	// ----------------------------Getters and
	// Setters-----------------------------------

	public Descartavel getDescartavel() {
		return aDescartavel;
	}

	public void setDescartavel(Descartavel pDescartavel) {
		this.aDescartavel = pDescartavel;
	}

	public Descartavel getaDescartavelSelecionado() {
		return aDescartavelSelecionado;
	}

	public void setaDescartavelSelecionado(Descartavel aDescartavelSelecionado) {
		this.aDescartavelSelecionado = aDescartavelSelecionado;
	}

}