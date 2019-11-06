package br.com.rexpress.valuesObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * The persistent class for the instituicao database table.
 * 
 */
@Entity
@Table(name = "instituicao")
@NamedQueries({ @NamedQuery(name = "Instituicao.findAll", query = "SELECT i FROM Instituicao i"),
		@NamedQuery(name = "Instituicao.listarEscolas", query = "SELECT i FROM Instituicao i WHERE i.tipo = false"),
		@NamedQuery(name = "Instituicao.listarCreches", query = "SELECT i FROM Instituicao i WHERE i.tipo = true"),
		@NamedQuery(name = "Instituicao.instituicaoPorRota", query = "SELECT i FROM Instituicao i WHERE i.rota.id = :rota"), 
		@NamedQuery(name = "Instituicao.instituicaoPorNome", query = "SELECT i FROM Instituicao i WHERE i.nomeInstituicao = :nome")
})
public class Instituicao implements Serializable, br.com.rexpress.util.VOGenerico {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_instituicao", unique = true, nullable = false)
	private Integer id;

	@Column(name = "nome_instituicao", length = 2147483647)
	private String nomeInstituicao;

	private Boolean tipo = false;

	// bi-directional one-to-one association to Endereco
	@OneToOne
	@JoinColumn(name = "id_endereco", nullable = false)
	private Endereco endereco;

	// bi-directional many-to-one association to Rota
	@ManyToOne
	@JoinColumn(name = "id_rota", nullable = false)
	private Rota rota;

	// bi-directional many-to-one association to Mapa
	@OneToMany(mappedBy = "instituicao")
	private List<Mapa> mapas = new ArrayList<Mapa>();

	// bi-directional many-to-one association to Turno
	@OneToMany(mappedBy = "instituicao", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Turno> turnos = new ArrayList<Turno>();

	public Instituicao() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeInstituicao() {
		return this.nomeInstituicao;
	}

	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}

	public Boolean getTipo() {
		return this.tipo;
	}

	public void setTipo(Boolean tipo) {
		this.tipo = tipo;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Rota getRota() {
		return this.rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public List<Mapa> getMapas() {
		return this.mapas;
	}

	public void setMapas(List<Mapa> mapas) {
		this.mapas = mapas;
	}

	public Mapa addMapa(Mapa mapa) {
		getMapas().add(mapa);
		mapa.setInstituicao(this);

		return mapa;
	}

	public Mapa removeMapa(Mapa mapa) {
		getMapas().remove(mapa);
		mapa.setInstituicao(null);

		return mapa;
	}

	public List<Turno> getTurnos() {
		List<Turno> turnosInstituicao = new ArrayList<Turno>();
		if(this.tipo == false){
			for (Turno turno : this.turnos) {
				if (turno.getDescricao().equals("CAFÉ DA MANHÃ"))
					turnosInstituicao.add(turno);
			}
			for (Turno turno : this.turnos) {
				if (turno.getDescricao().equals("ALMOÇO"))
					turnosInstituicao.add(turno);
			}
			for (Turno turno : this.turnos) {
				if (turno.getDescricao().equals("JANTAR"))
					turnosInstituicao.add(turno);
			}
			turnos = turnosInstituicao;
			return turnosInstituicao;
		}
		else {
			for (Turno turno : this.turnos) {
				if (turno.getDescricao().equals("DESJEJUM"))
					turnosInstituicao.add(turno);
			}
			for (Turno turno : this.turnos) {
				if (turno.getDescricao().equals("ALMOÇO"))
					turnosInstituicao.add(turno);
			}
			for (Turno turno : this.turnos) {
				if (turno.getDescricao().equals("JANTAR"))
					turnosInstituicao.add(turno);
			}
			for (Turno turno : this.turnos) {
				if (turno.getDescricao().equals("LANCHE DA MANHÃ"))
					turnosInstituicao.add(turno);
			}
			for (Turno turno : this.turnos) {
				if (turno.getDescricao().equals("LANCHE DA TARDE"))
					turnosInstituicao.add(turno);
			}
			this.turnos = turnosInstituicao;
			return this.turnos;	
		}
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public Turno addTurno(Turno turno) {
		getTurnos().add(turno);
		turno.setInstituicao(this);

		return turno;
	}

	public Turno removeTurno(Turno turno) {
		getTurnos().remove(turno);
		turno.setInstituicao(null);

		return turno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomeInstituicao == null) ? 0 : nomeInstituicao.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Instituicao))
			return false;
		Instituicao other = (Instituicao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeInstituicao == null) {
			if (other.nomeInstituicao != null)
				return false;
		} else if (!nomeInstituicao.equals(other.nomeInstituicao))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

}