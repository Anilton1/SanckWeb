package br.com.rexpress.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.PersistenceException;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;

import br.com.rexpress.dao.DescartavelDAO;
import br.com.rexpress.dao.ItemDAO;
import br.com.rexpress.dao.MerendaDAO;
import br.com.rexpress.rn.RNMerenda;
import br.com.rexpress.util.DAOGenerico;
import br.com.rexpress.util.MensagensUtil;
import br.com.rexpress.valuesObjects.Descartavel;
import br.com.rexpress.valuesObjects.Item;
import br.com.rexpress.valuesObjects.Merenda;

@ManagedBean(name = "ControleMerenda")
@SessionScoped
public class ControleMerenda implements Serializable {

	// ----------------------------Delcaração de
	// Atributos-----------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MerendaDAO aMerendaDAO;
	private Merenda aMerendaSelecionado;
	private Merenda aMerenda;
	private Item aItem;
	private RNMerenda aRNMerenda;
	private String DescricaoMerenda;
	private MensagensUtil mensagem;
	private boolean isDescartavel = false;
	private List<Item> itens = new ArrayList<Item>();
	private List<Descartavel> descartaveis = new ArrayList<Descartavel>();
	private DualListModel<Item> itemDualList;
	private DualListModel<Descartavel> descartavelDualList;

	// ----------------------------Método
	// construtor-----------------------------------

	public ControleMerenda() {
		aMerendaDAO = new MerendaDAO();
		aMerenda = new Merenda();
		mensagem = new MensagensUtil();
		aMerendaSelecionado = new Merenda();
		aItem = new Item();
	}

	// ----------------------------Método para inserir, alterar e excluir
	// (Respectivamente)-----------------------------------

	public void inserirMerenda() {
		try {
			aMerenda.setItems(itemDualList.getTarget());
			aMerenda.setDescartavels(descartavelDualList.getTarget());
			// insere o merenda em si
			boolean commit = aMerendaDAO.inserir(aMerenda);

			// Exibe mensagem de sucesso
			if (commit == true) {
				mensagem.MensagemInfo("Sucesso!",
						"Merenda " + aMerenda.getDescricaoMerenda()
								+ " inserida com sucesso.", "messageInserir");
			} else {
				mensagem.MensagemErro("Erro!", "Erro ao inserir a merenda",
						"messageInserir");
			}

		} catch (PersistenceException e) {
			// Exibe mensagem de erro
			mensagem.MensagemErro("Erro!", "Erro ao inserir o merenda",
					"messageInserir");
		} finally {
			aMerenda = new Merenda();
			ControleItem aControleItem = new ControleItem();
			ControleDescartavel aControleDescartavel = new ControleDescartavel();

			List<Item> itemnull = new ArrayList();
			List<Descartavel> descartavel = new ArrayList();

			itemDualList.setSource(aControleItem.listarItens());
			itemDualList.setTarget(itemnull);
			descartavelDualList.setSource(aControleDescartavel
					.listarDescartaveis());
			descartavelDualList.setTarget(descartavel);
		}
	}

	public void alterarMerenda() {
		try {
			aMerendaSelecionado.setItems(itemDualList.getTarget());
			aMerendaSelecionado
					.setDescartavels(descartavelDualList.getTarget());
			// tenta inserir o item para alterar
			boolean commit = aMerendaDAO.alterar(aMerendaSelecionado);
			// se não der nenhum erro, vai retornar a mensagem de sucesso!
			if (commit == true) {
				mensagem.MensagemInfo("Sucesso!", "Merenda "
						+ aMerendaSelecionado.getDescricaoMerenda()
						+ " alterada com sucesso!", "menssageAlterar");

			} else {
				mensagem.MensagemErro("Erro!", "Erro ao alterar a merenda"
						+ aMerendaSelecionado.getDescricaoMerenda() + "!",
						"menssageAlterar");
			}

		} catch (PersistenceException e) {
			// caso ocorra algum erro de persistência, irá apresentar a mensagem
			// de erro
			mensagem.MensagemErro("Erro!", "Erro ao alterar a merenda"
					+ aMerendaSelecionado.getDescricaoMerenda() + "!",
					"menssageAlterar");
		} finally {
			aMerendaSelecionado = new Merenda();
			ControleItem aControleItem = new ControleItem();
			ControleDescartavel aControleDescartavel = new ControleDescartavel();

			List<Item> itemnull = new ArrayList();
			List<Descartavel> descartavel = new ArrayList();
			itemDualList.setSource(aControleItem.listarItens());
			itemDualList.setTarget(itemnull);
			descartavelDualList.setSource(aControleDescartavel
					.listarDescartaveis());
			descartavelDualList.setTarget(descartavel);

		}
	}

	public void deletarMerenda(Merenda pMerenda) {
		try {
			boolean commit = aMerendaDAO.deletar(pMerenda);
			if (commit == true) {
				mensagem.MensagemInfo(
						"Merenda" + pMerenda.getDescricaoMerenda()
								+ " excluída com sucesso!",
						"Merenda excluída com sucesso!", "messageDataTable");
			} else {
				mensagem.MensagemErro(
						"Erro ao excluir a Merenda"
								+ pMerenda.getDescricaoMerenda(),
						"Ocorreu algum erro ao excluir a Merenda!",
						"messageDataTable");
			}
		} catch (PersistenceException e) {
			mensagem.MensagemErro(
					"Erro ao excluir a Merenda"
							+ pMerenda.getDescricaoMerenda(),
					"Ocorreu algum erro ao excluir a Merenda!",
					"messageDataTable");
		}
	}

	// ----------------------------Método que seleciona um item para
	// alteração-----------------------------------

	public Merenda selecionarMerenda(Merenda pMerenda) {
		try {

			ControleItem controleItem = new ControleItem();
			ControleDescartavel controleDescartavel = new ControleDescartavel();

			List<Item> itemSource = new ArrayList<Item>();
			List<Descartavel> descartavelSource = new ArrayList<Descartavel>();

			aMerendaSelecionado = pMerenda;

			itemSource = controleItem.listarItens();
			itemSource.removeAll(aMerendaSelecionado.getItems());

			descartavelSource = controleDescartavel.listarDescartaveis();
			descartavelSource.removeAll(aMerendaSelecionado.getDescartavels());

			itemDualList.setSource(itemSource);
			itemDualList.setTarget(aMerendaSelecionado.getItems());
			descartavelDualList.setSource(descartavelSource);
			descartavelDualList
					.setTarget(aMerendaSelecionado.getDescartavels());

		} catch (Exception e) {
			// caso ocorra algum erro ao selecionar o item, irá apresentar a
			// mensagem de erro
			mensagem.MensagemErro("Erro ao selecionar a Merenda",
					"Ocorreu algum erro na hora de selecionar a merenda!",
					"menssageDataTable");
		}
		return aMerendaSelecionado;
	}

	// ----------------------------Método para abrir o dialog de
	// alteração-----------------------------------

	public void alterarDialog() {

		HashMap<String, Object> opcoes = new HashMap<String, Object>();

		opcoes.put("modal", true);
		opcoes.put("contentWidth", 1100);

		RequestContext.getCurrentInstance().openDialog("alterarMerenda",
				opcoes, null);

	}

	// ----------------------------Método de
	// validação-----------------------------------

	public Merenda merendaValida() {
		return aRNMerenda.valida(DescricaoMerenda);
	}

	// ----------------------------Consultas-----------------------------------

	public List<Merenda> listarMerendas() {
		return aMerendaDAO.listarTudo();
	}

	// ----------------------------Getters and
	// Setters-----------------------------------

	public Merenda getMerenda() {
		return aMerenda;
	}

	public void setMerenda(Merenda pMerenda) {
		this.aMerenda = pMerenda;
	}

	public String getDescricaoMerenda() {
		return DescricaoMerenda;
	}

	public void setDescricaoMerenda(String descricaoMerenda) {
		DescricaoMerenda = descricaoMerenda;
	}

	public Merenda getMerendaSelecionado() {
		return aMerendaSelecionado;
	}

	public void setMerendaSelecionado(Merenda pMerendaSelecionado) {
		this.aMerendaSelecionado = pMerendaSelecionado;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public void adicionarItem() {
		this.itens.add(aItem);
		aItem = null;
	}

	public List<Descartavel> getDescartaveis() {
		return descartaveis;
	}

	public void setDescartaveis(List<Descartavel> descartaveis) {
		this.descartaveis = descartaveis;
	}

	public void adicionarDescartaveis(Descartavel pDescartavel) {
		this.descartaveis.add(pDescartavel);

	}

	public boolean isDescartavel() {
		return isDescartavel;
	}

	public void setDescartavel(boolean isDescartavel) {
		this.isDescartavel = isDescartavel;
	}

	public Item getItem() {
		return aItem;
	}

	public void setItem(Item aItem) {
		this.aItem = aItem;
	}

	public DualListModel<Item> getItemDualList() {
		if (this.itemDualList == null) {
			this.itemDualList = new DualListModel<Item>(
					new ItemDAO().listarTudo(), new ArrayList<Item>());
		}
		return itemDualList;
	}

	public void setItemDualList(DualListModel<Item> itemDualList) {
		this.itemDualList = itemDualList;
	}

	public DualListModel<Descartavel> getDescartavelDualList() {
		if (this.descartavelDualList == null) {
			this.descartavelDualList = new DualListModel<Descartavel>(
					new DescartavelDAO().listarTudo(),
					new ArrayList<Descartavel>());
		}

		return descartavelDualList;
	}

	public void setDescartavelDualList(
			DualListModel<Descartavel> descartavelDualList) {
		this.descartavelDualList = descartavelDualList;
	}
}
