package br.com.rexpress.valuesObjects;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the rota database table.
 * 
 */
@Entity
@Table(name = "rota")
@NamedQuery(name = "Rota.findAll", query = "SELECT r FROM Rota r")
public class Rota implements Serializable, br.com.rexpress.util.VOGenerico {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_rota", unique = true, nullable = false)
	private Integer id;

	@Column(name = "descricao_rota", length = 2147483647)
	private String descricaoRota;

	@Column(length = 2147483647)
	private String nome;

	// bi-directional many-to-one association to Instituicao
	@OneToMany(mappedBy = "rota", cascade = CascadeType.ALL)
	private List<Instituicao> instituicaos;

	// bi-directional many-to-one association to Mapa
	@OneToMany(mappedBy = "rota", cascade = CascadeType.ALL)
	private List<Mapa> mapas;

	public Rota() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoRota() {
		return this.descricaoRota;
	}

	public void setDescricaoRota(String descricaoRota) {
		this.descricaoRota = descricaoRota;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Instituicao> getInstituicaos() {
		return this.instituicaos;
	}

	public void setInstituicaos(List<Instituicao> instituicaos) {
		this.instituicaos = instituicaos;
	}

	public Instituicao addInstituicao(Instituicao instituicao) {
		getInstituicaos().add(instituicao);
		instituicao.setRota(this);

		return instituicao;
	}

	public Instituicao removeInstituicao(Instituicao instituicao) {
		getInstituicaos().remove(instituicao);
		instituicao.setRota(null);

		return instituicao;
	}

	public List<Mapa> getMapas() {
		return this.mapas;
	}

	public void setMapas(List<Mapa> mapas) {
		this.mapas = mapas;
	}

	public Mapa addMapa(Mapa mapa) {
		getMapas().add(mapa);
		mapa.setRota(this);

		return mapa;
	}

	public Mapa removeMapa(Mapa mapa) {
		getMapas().remove(mapa);
		mapa.setRota(null);

		return mapa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricaoRota == null) ? 0 : descricaoRota.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Rota))
			return false;
		Rota other = (Rota) obj;
		if (descricaoRota == null) {
			if (other.descricaoRota != null)
				return false;
		} else if (!descricaoRota.equals(other.descricaoRota))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}