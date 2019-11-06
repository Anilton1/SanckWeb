package br.com.rexpress.controle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.PersistenceException;

import org.primefaces.context.RequestContext;

import br.com.rexpress.dao.ItemDAO;
import br.com.rexpress.rn.RNItem;
import br.com.rexpress.util.MensagensUtil;
import br.com.rexpress.valuesObjects.Item;

@ManagedBean(name = "ControleItem")
@SessionScoped
public class ControleItem implements Serializable {

	// ----------------------------Declara��o de
	// Atributos-----------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ItemDAO aItemDAO;
	private Item aItem;
	private Item aItemSelecionado = new Item();
	private String descricaoItem;
	private RNItem aRNItem;
	private MensagensUtil mensagem;

	// ----------------------------M�todo
	// construtor-----------------------------------

	public ControleItem() {
		aItemDAO = new ItemDAO();
		aItem = new Item();
		aRNItem = new RNItem();
		mensagem = new MensagensUtil();
	}

	// ----------------------------M�todo para inserir, alterar e excluir
	// (Respectivamente)-----------------------------------

	public void inserirItem() {

		try {
			// Tenta inserir o objeto
			boolean commit = aItemDAO.inserir(aItem);

			// se n�o der nenhum erro, vai retornar a mensagem de sucesso!
			if (commit == true) {
				mensagem.MensagemInfo("Sucesso!",
						"Item " + aItem.getDescricaoItem()
								+ " inserido com sucesso", "messageInserir");
			} else {
				mensagem.MensagemErro("Erro ao salvar o item!",
						"Erro ao inserir um item!", "menssageInserir");
			}

		} catch (PersistenceException e) {
			// caso ocorra algum erro de persist�ncia, ir� apresentar a mensagem
			// de erro
			mensagem.MensagemErro("Erro ao salvar o item!",
					"Erro ao inserir um item!", "menssageInserir");

		} finally {
			aItem = new Item();
		}
	}

	public void alterarItem() {
		try {
			// tenta inserir o item para alterar
			boolean commit = aItemDAO.alterar(aItemSelecionado);
			// se n�o der nenhum erro, vai retornar a mensagem de sucesso!
			if (commit == true) {
				mensagem.MensagemInfo("Sucesso!",
						"Item " + aItemSelecionado.getDescricaoItem()
								+ " alterado com sucesso!", "menssageAlterar");
			} else {
				mensagem.MensagemErro("Erro!", "Erro ao alterar o item"
						+ aItemSelecionado.getDescricaoItem() + "!",
						"menssageAlterar");
			}

		} catch (PersistenceException e) {
			// caso ocorra algum erro de persist�ncia, ir� apresentar a mensagem
			// de erro
			mensagem.MensagemErro("Erro!", "Erro ao alterar o item"
					+ aItemSelecionado.getDescricaoItem() + "!",
					"menssageAlterar");

		} finally {
			aItemSelecionado = new Item();
		}
	}

	public void deletarItem(Item pItem) {
		try {
			// tenta deletar o item
			boolean commit = aItemDAO.deletar(pItem);
			// se n�o der nenhum erro, vai retornar a mensagem de sucesso!
			if (commit == true) {
				mensagem.MensagemInfo("Item exclu�do com sucesso!",
						"Item exclu�do com sucesso!", "messageDataTable");
			} else {
				mensagem.MensagemErro("Erro ao excluir o Item",
						"Ocorreu algum erro ao excluir o Item!Talvez ele esteja sendo usado por alguma Merenda.",
						"messageDataTable");
			}

		} catch (PersistenceException e) {
			mensagem.MensagemErro("Erro ao excluir o Item",
					"Ocorreu algum erro ao excluir o Item!", "messageDataTable");
		}
	}

	// ----------------------------M�todo que seleciona um item para
	// altera��o-----------------------------------

	public Item selecionarItem(Item pItem) {
		try {
			aItemSelecionado = pItem;
		} catch (Exception e) {
			// caso ocorra algum erro ao selecionar o item, ir� apresentar a
			// mensagem de erro
			mensagem.MensagemErro("Erro ao selecionar o Item",
					"Ocorreu algum erro na hora de selecionar o item!",
					"menssageDataTable");
		}
		return aItemSelecionado;
	}

	// ----------------------------M�todo para abrir o dialog de
	// altera��o-----------------------------------

	public void alterarDialog() {

		HashMap<String, Object> opcoes = new HashMap<String, Object>();

		opcoes.put("modal", true);
		opcoes.put("contentWidth", 900);

		RequestContext.getCurrentInstance().openDialog("alterarItem", opcoes,
				null);

	}

	// ----------------------------M�todo de
	// valida��o-----------------------------------

	public Item validaItem() {
		return aRNItem.valida(descricaoItem);
	}

	// ----------------------------Consultas-----------------------------------

	public List<Item> listarItens() {
		return aItemDAO.listarTudo();
	}

	// ----------------------------Getters and
	// Setters-----------------------------------

	public Item getItem() {
		return aItem;
	}

	public void setItem(Item pItem) {
		this.aItem = pItem;
	}

	public String getDescricaoItem() {
		return descricaoItem;
	}

	public void setDescricaoItem(String descricaoItem) {
		this.descricaoItem = descricaoItem;
	}

	public Item getItemSelecionado() {
		return aItemSelecionado;
	}

	public void setItemSelecionado(Item aItemSelecionado) {
		this.aItemSelecionado = aItemSelecionado;
	}

}