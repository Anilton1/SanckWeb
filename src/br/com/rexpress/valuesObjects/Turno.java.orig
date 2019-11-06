package br.com.rexpress.valuesObjects;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the turno database table.
 * 
 */
@Entity
@Table(name = "turno")
@NamedQueries({
		@NamedQuery(name = "Turno.findAll", query = "SELECT t FROM Turno t"),
		@NamedQuery(name = "Turno.turnosEscolas", query = "SELECT t FROM Turno t INNER JOIN t.instituicao i WHERE i.tipo = false AND t.instituicao.id = :id"),
		@NamedQuery(name = "Turno.turnoA", query = "SELECT t FROM Turno t  WHERE t.descricao = 'CAFÉ DA MANHÃ' "),
		@NamedQuery(name = "Turno.turnoAA", query = "SELECT t FROM Turno t  WHERE t.descricao = 'DESJEJUM' "),
		@NamedQuery(name = "Turno.turnoAAA", query = "SELECT t FROM Turno t  WHERE t.descricao = 'LANCHE DA MANHÃ' "),
		@NamedQuery(name = "Turno.turnoB", query = "SELECT t FROM Turno t  WHERE t.descricao = 'ALMOÇO' "),
		@NamedQuery(name = "Turno.turnoBB", query = "SELECT t FROM Turno t  WHERE t.descricao = 'LANCHE DA TARDE' "),
		@NamedQuery(name = "Turno.turnoC", query = "SELECT t FROM Turno t  WHERE t.descricao = 'JANTAR' "),
		@NamedQuery(name = "Turno.turnosCreches", query = "SELECT t FROM Turno t INNER JOIN t.instituicao i WHERE i.tipo = true AND t.instituicao.id = :id") })
public class Turno implements Serializable, br.com.rexpress.util.VOGenerico {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_turno", unique = true, nullable = false)
	private Integer id;

	@Column(length = 2147483647)
	private String descricao;

	@Column(name = "qtd_alunos1")
	private Integer qtdAlunos1;

	@Column(name = "qtd_alunos2")
	private Integer qtdAlunos2;

	@Column(name = "qtd_alunos3")
	private Integer qtdAlunos3;

	// bi-directional many-to-one association to Instituicao
	@ManyToOne
	@JoinColumn(name = "id_instituicao", nullable = false)
	private Instituicao instituicao;

	public Turno() {
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

	public Integer getQtdAlunos1() {
		return this.qtdAlunos1;
	}

	public void setQtdAlunos1(Integer qtdAlunos1) {
		this.qtdAlunos1 = qtdAlunos1;
	}

	public Integer getQtdAlunos2() {
		return this.qtdAlunos2;
	}

	public void setQtdAlunos2(Integer qtdAlunos2) {
		this.qtdAlunos2 = qtdAlunos2;
	}

	public Integer getQtdAlunos3() {
		return this.qtdAlunos3;
	}

	public void setQtdAlunos3(Integer qtdAlunos3) {
		this.qtdAlunos3 = qtdAlunos3;
	}

	public Instituicao getInstituicao() {
		return this.instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
	
	public Turno getCafeDaManha(Instituicao pInstituicao){
		for (Turno turno : pInstituicao.getTurnos()) {
			if(turno.descricao.equals("CAFÉ DA MANHÃ") || turno.descricao.equals("DESJEJUM")){
				return turno;
			}
		}
		return null;
	}
	
	public Turno getAlmoco(Instituicao pInstituicao){
		for (Turno turno : pInstituicao.getTurnos()) {
			if(turno.descricao.equals("ALMOÇO")){
				return turno;
			}
		}
		return null;
	}
	
	public Turno getJantar(Instituicao pInstituicao){
		for (Turno turno : pInstituicao.getTurnos()) {
			if(turno.descricao.equals("JANTAR")){
				return turno;
			}
		}
		return null;
	}
	
	public Turno getLancheManha(Instituicao pInstituicao){
		for (Turno turno : pInstituicao.getTurnos()) {
			if(turno.descricao.equals("LANCHE MANHÃ")){
				return turno;
			}
		}
		return null;
	}
	
	public Turno getLancheTarde(Instituicao pInstituicao){
		for (Turno turno : pInstituicao.getTurnos()) {
			if(turno.descricao.equals("LANCHE TARDE")){
				return turno;
			}
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((qtdAlunos1 == null) ? 0 : qtdAlunos1.hashCode());
		result = prime * result
				+ ((qtdAlunos2 == null) ? 0 : qtdAlunos2.hashCode());
		result = prime * result
				+ ((qtdAlunos3 == null) ? 0 : qtdAlunos3.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Turno))
			return false;
		Turno other = (Turno) obj;
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
		if (qtdAlunos1 == null) {
			if (other.qtdAlunos1 != null)
				return false;
		} else if (!qtdAlunos1.equals(other.qtdAlunos1))
			return false;
		if (qtdAlunos2 == null) {
			if (other.qtdAlunos2 != null)
				return false;
		} else if (!qtdAlunos2.equals(other.qtdAlunos2))
			return false;
		if (qtdAlunos3 == null) {
			if (other.qtdAlunos3 != null)
				return false;
		} else if (!qtdAlunos3.equals(other.qtdAlunos3))
			return false;
		return true;
	}

}