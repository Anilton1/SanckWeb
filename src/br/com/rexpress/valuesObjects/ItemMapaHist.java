package br.com.rexpress.valuesObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the item_mapa_hist database table.
 * 
 */
@Entity
@Table(name = "item_mapa_hist")
@NamedQuery(name = "ItemMapaHist.seExiste", query = "SELECT i FROM ItemMapaHist i WHERE i.descricaoItem = :descricao AND i.grama = :grama AND i.percapita = :percapita")
public class ItemMapaHist implements Serializable, br.com.rexpress.util.VOGenerico {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_item_mapa", unique = true, nullable = false)
	private Integer id;

	@Column(name = "descricao_item", length = 2147483647)
	private String descricaoItem;

	private float grama;

	private float percapita;

	private Boolean tipo;

	// bi-directional many-to-one association to ItemMapa
	@OneToMany(mappedBy = "itemMapaHist")
	private List<ItemMapa> itemMapas = new ArrayList<ItemMapa>();

	// bi-directional many-to-many association to MerendaMapaHist
	@ManyToMany(mappedBy = "itemMapaHists")
	private List<MerendaMapaHist> merendaMapaHists = new ArrayList<MerendaMapaHist>();

	public ItemMapaHist() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoItem() {
		return this.descricaoItem;
	}

	public void setDescricaoItem(String descricaoItem) {
		this.descricaoItem = descricaoItem;
	}

	public float getGrama() {
		return this.grama;
	}

	public void setGrama(float grama) {
		this.grama = grama;
	}

	public float getPercapita() {
		return this.percapita;
	}

	public void setPercapita(float percapita) {
		this.percapita = percapita;
	}

	public List<ItemMapa> getItemMapas() {
		return this.itemMapas;
	}

	public void setItemMapas(List<ItemMapa> itemMapas) {
		this.itemMapas = itemMapas;
	}

	public ItemMapa addItemMapa(ItemMapa itemMapa) {
		getItemMapas().add(itemMapa);
		itemMapa.setItemMapaHist(this);

		return itemMapa;
	}

	public ItemMapa removeItemMapa(ItemMapa itemMapa) {
		getItemMapas().remove(itemMapa);
		itemMapa.setItemMapaHist(null);

		return itemMapa;
	}

	public List<MerendaMapaHist> getMerendaMapaHists() {
		return this.merendaMapaHists;
	}

	public void setMerendaMapaHists(List<MerendaMapaHist> merendaMapaHists) {
		this.merendaMapaHists = merendaMapaHists;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricaoItem == null) ? 0 : descricaoItem.hashCode());
		result = prime * result + Float.floatToIntBits(grama);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Float.floatToIntBits(percapita);
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	public Boolean getTipo() {
		return tipo;
	}

	public void setTipo(Boolean tipo) {
		this.tipo = tipo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ItemMapaHist))
			return false;
		ItemMapaHist other = (ItemMapaHist) obj;
		if (descricaoItem == null) {
			if (other.descricaoItem != null)
				return false;
		} else if (!descricaoItem.equals(other.descricaoItem))
			return false;
		if (Float.floatToIntBits(grama) != Float.floatToIntBits(other.grama))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Float.floatToIntBits(percapita) != Float.floatToIntBits(other.percapita))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	public List<ItemMapaHist> valueOf(List<Item> pItem) {
		List<ItemMapaHist> itemHist = new ArrayList<ItemMapaHist>();
		ItemMapaHist item = new ItemMapaHist();

		for (Item ListItem : pItem) {
			item.setDescricaoItem(ListItem.getDescricaoItem());
			item.setGrama(ListItem.getGrama());
			item.setPercapita(ListItem.getPercapita());
			item.setTipo(ListItem.getTipo());
			itemHist.add(item);
			item = new ItemMapaHist();
		}
		return itemHist;
	}

}