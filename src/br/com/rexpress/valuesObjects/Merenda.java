package br.com.rexpress.valuesObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * The persistent class for the merenda database table.
 * 
 */
@Entity
@Table(name = "merenda")
@NamedQuery(name = "Merenda.findAll", query = "SELECT m FROM Merenda m")
public class Merenda implements Serializable, br.com.rexpress.util.VOGenerico {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_merenda", unique = true, nullable = false)
	private Integer id;

	@Column(name = "descricao_merenda", length = 2147483647)
	private String descricaoMerenda;

	// bi-directional many-to-many association to Descartavel
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "descartavel_merenda", joinColumns = @JoinColumn(name = "id_merenda", nullable = false), inverseJoinColumns = @JoinColumn(name = "id_descartavel", nullable = false))
	@Fetch(FetchMode.SUBSELECT)
	private List<Descartavel> descartavels = new ArrayList<Descartavel>();

	// bi-directional many-to-many association to Item
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "item_merenda", joinColumns = @JoinColumn(name = "id_merenda", nullable = false), inverseJoinColumns = @JoinColumn(name = "id_item", nullable = false))
	@Fetch(FetchMode.SUBSELECT)
	private List<Item> items = new ArrayList<Item>();

	public Merenda() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoMerenda() {
		return this.descricaoMerenda;
	}

	public void setDescricaoMerenda(String descricaoMerenda) {
		this.descricaoMerenda = descricaoMerenda;
	}

	public List<Descartavel> getDescartavels() {
		return this.descartavels;
	}

	public void setDescartavels(List<Descartavel> descartavels) {
		this.descartavels = descartavels;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((descricaoMerenda == null) ? 0 : descricaoMerenda.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Merenda))
			return false;
		Merenda other = (Merenda) obj;
		if (descricaoMerenda == null) {
			if (other.descricaoMerenda != null)
				return false;
		} else if (!descricaoMerenda.equals(other.descricaoMerenda))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}