package br.com.rexpress.valuesObjects;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the item_mapa database table.
 * 
 */
@Entity
@Table(name="item_mapa")
@NamedQuery(name="ItemMapa.findAll", query="SELECT i FROM ItemMapa i")
public class ItemMapa implements Serializable, br.com.rexpress.util.VOGenerico {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_item_mapa")
	private Integer id;

	@Column(name="valor_total1")
	private float valorTotal1;
	
	@Column(name="valor_total2")
	private float valorTotal2;
	
	@Column(name="valor_total3")
	private float valorTotal3;

	//bi-directional many-to-one association to ItemMapaHist
	@ManyToOne
	@JoinColumn(name="id_item")
	private ItemMapaHist itemMapaHist;

	//bi-directional many-to-one association to TurnoMapa
	@ManyToOne
	@JoinColumn(name="id_turno")
	private TurnoMapa turnoMapa;

	public ItemMapa() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getValorTotal1() {
		return this.valorTotal1;
	}

	public void setValorTotal1(float valorTotal1) {
		this.valorTotal1 = valorTotal1;
	}
	
	public float getValorTotal2() {
		return this.valorTotal2;
	}

	public void setValorTotal2(float valorTotal2) {
		this.valorTotal2 = valorTotal2;
	}
	
	public float getValorTotal3() {
		return this.valorTotal3;
	}

	public void setValorTotal3(float valorTotal3) {
		this.valorTotal3 = valorTotal3;
	}

	public ItemMapaHist getItemMapaHist() {
		return this.itemMapaHist;
	}

	public void setItemMapaHist(ItemMapaHist itemMapaHist) {
		this.itemMapaHist = itemMapaHist;
	}

	public TurnoMapa getTurnoMapa() {
		return this.turnoMapa;
	}

	public void setTurnoMapa(TurnoMapa turnoMapa) {
		this.turnoMapa = turnoMapa;
	}

}