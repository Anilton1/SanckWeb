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
import javax.persistence.Table;

/**
 * The persistent class for the descartavel database table.
 * 
 */
@Entity
@Table(name = "descartavel")
@NamedQuery(name = "Descartavel.findAll", query = "SELECT d FROM Descartavel d")
public class Descartavel implements Serializable,
		br.com.rexpress.util.VOGenerico {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_descartavel", unique = true, nullable = false)
	private Integer id;

	@Column(length = 2147483647)
	private String descricao;

	// bi-directional many-to-many association to Merenda
	@ManyToMany(mappedBy="descartavels")
	private List<Merenda> merendas = new ArrayList<Merenda>();

	// bi-directional many-to-many association to Merenda

	@ManyToMany(mappedBy="descartavelsMapa")
	private List<MerendaMapaHist> merendasMapa = new ArrayList<MerendaMapaHist>();

	public Descartavel() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Merenda> getMerendas() {
		return this.merendas;
	}

	public void setMerendas(List<Merenda> merendas) {
		this.merendas = merendas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Descartavel))
			return false;
		Descartavel other = (Descartavel) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}