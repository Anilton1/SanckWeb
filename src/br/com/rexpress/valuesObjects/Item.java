package br.com.rexpress.valuesObjects;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@Table(name = "item")
@NamedQueries({ @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i") })
public class Item implements Serializable, br.com.rexpress.util.VOGenerico {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_item", unique = true, nullable = false)
	private Integer id;

	@Column(name = "descricao_item", length = 2147483647)
	private String descricaoItem;

	private float grama;

	private float percapita;

	@Column(name = "tipo")
	private Boolean tipo;

	// bi-directional many-to-many association to Merenda
	@ManyToMany(mappedBy="items")
	private List<Merenda> merendas;

	public Item() {
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

	public List<Merenda> getMerendas() {
		return this.merendas;
	}

	public void setMerendas(List<Merenda> merendas) {
		this.merendas = merendas;
	}

	public Boolean getTipo() {
		return tipo;
	}

	public void setTipo(Boolean tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricaoItem == null) ? 0 : descricaoItem.hashCode());
		result = prime * result + Float.floatToIntBits(grama);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Float.floatToIntBits(percapita);
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Item))
			return false;
		Item other = (Item) obj;
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
		if (Float.floatToIntBits(percapita) != Float
				.floatToIntBits(other.percapita))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

}