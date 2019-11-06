package br.com.rexpress.valuesObjects;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-11-23T22:28:08.268-0300")
@StaticMetamodel(ItemMapaHist.class)
public class ItemMapaHist_ {
	public static volatile SingularAttribute<ItemMapaHist, Integer> id;
	public static volatile SingularAttribute<ItemMapaHist, String> descricaoItem;
	public static volatile SingularAttribute<ItemMapaHist, Float> grama;
	public static volatile SingularAttribute<ItemMapaHist, Float> percapita;
	public static volatile SingularAttribute<ItemMapaHist, Boolean> tipo;
	public static volatile ListAttribute<ItemMapaHist, ItemMapa> itemMapas;
	public static volatile ListAttribute<ItemMapaHist, MerendaMapaHist> merendaMapaHists;
}
