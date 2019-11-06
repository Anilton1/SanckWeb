package br.com.rexpress.valuesObjects;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-11-24T00:29:10.291-0300")
@StaticMetamodel(ItemMapa.class)
public class ItemMapa_ {
	public static volatile SingularAttribute<ItemMapa, Integer> id;
	public static volatile SingularAttribute<ItemMapa, Float> valorTotal1;
	public static volatile SingularAttribute<ItemMapa, Float> valorTotal2;
	public static volatile SingularAttribute<ItemMapa, Float> valorTotal3;
	public static volatile SingularAttribute<ItemMapa, ItemMapaHist> itemMapaHist;
	public static volatile SingularAttribute<ItemMapa, TurnoMapa> turnoMapa;
}
