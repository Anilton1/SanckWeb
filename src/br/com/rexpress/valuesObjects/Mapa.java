package br.com.rexpress.valuesObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the mapa database table.
 * 
 */
@Entity
@Table(name = "mapa")
@NamedQueries({ @NamedQuery(name = "Mapa.findAll", query = "SELECT m FROM Mapa m"),
		@NamedQuery(name = "Mapa.seExisteInsituicaoPorData", query = "SELECT m FROM Mapa m WHERE m.instituicao.id = :id AND m.dataroteirizacao = :data"),
		@NamedQuery(name = "Mapa.mapaPorData", query = "SELECT m FROM Mapa m WHERE m.dataroteirizacao = :data"),
		@NamedQuery(name = "Mapa.escolaMapeada", query = "SELECT m FROM Mapa m WHERE m.tipo = false"),
		@NamedQuery(name = "Mapa.crecheMapeada", query = "SELECT m FROM Mapa m WHERE m.tipo = true") })
public class Mapa implements Serializable, br.com.rexpress.util.VOGenerico {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_mapa", unique = true, nullable = false)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_processamento")
	private Date dataProcessamento;

	@Temporal(TemporalType.DATE)
	private Date dataroteirizacao;

	private Boolean tipo = false;

	// bi-directional many-to-one association to Instituicao
	@ManyToOne
	@JoinColumn(name = "id_instituicao", nullable = false)
	private Instituicao instituicao;

	// bi-directional many-to-one association to Rota
	@ManyToOne
	@JoinColumn(name = "id_rota", nullable = false)
	private Rota rota;

	// bi-directional many-to-one association to TurnoMapa
	@OneToMany(mappedBy = "mapa")
	private List<TurnoMapa> turnoMapas = new ArrayList<TurnoMapa>();

	public Mapa() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataProcessamento() {
		return this.dataProcessamento;
	}

	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	public Date getDataroteirizacao() {
		return this.dataroteirizacao;
	}

	public void setDataroteirizacao(Date dataroteirizacao) {
		this.dataroteirizacao = dataroteirizacao;
	}

	public Boolean getTipo() {
		return this.tipo;
	}

	public void setTipo(Boolean tipo) {
		this.tipo = tipo;
	}

	public Instituicao getInstituicao() {
		return this.instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Rota getRota() {
		return this.rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public List<TurnoMapa> getTurnoMapas() {
		return this.turnoMapas;
	}

	public void setTurnoMapas(List<TurnoMapa> turnoMapas) {
		this.turnoMapas = turnoMapas;
	}

	public TurnoMapa addTurnoMapa(TurnoMapa turnoMapa) {
		getTurnoMapas().add(turnoMapa);
		turnoMapa.setMapa(this);

		return turnoMapa;
	}

	public TurnoMapa removeTurnoMapa(TurnoMapa turnoMapa) {
		getTurnoMapas().remove(turnoMapa);
		turnoMapa.setMapa(null);

		return turnoMapa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataProcessamento == null) ? 0 : dataProcessamento.hashCode());
		result = prime * result + ((dataroteirizacao == null) ? 0 : dataroteirizacao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Mapa))
			return false;
		Mapa other = (Mapa) obj;
		if (dataProcessamento == null) {
			if (other.dataProcessamento != null)
				return false;
		} else if (!dataProcessamento.equals(other.dataProcessamento))
			return false;
		if (dataroteirizacao == null) {
			if (other.dataroteirizacao != null)
				return false;
		} else if (!dataroteirizacao.equals(other.dataroteirizacao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

}