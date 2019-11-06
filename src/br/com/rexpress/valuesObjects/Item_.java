package br.com.rexpress.valuesObjects;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-11-13T23:04:19.720-0200")
@StaticMetamodel(Item.class)
public class Item_ {
	public static volatile SingularAttribute<Item, Integer> id;
	public static volatile SingularAttribute<Item, String> descricaoItem;
	public static volatile SingularAttribute<Item, Float> grama;
	public static volatile SingularAttribute<Item, Float> percapita;
	public static volatile SingularAttribute<Item, Boolean> tipo;
	public static volatile ListAttribute<Item, Merenda> merendas;
}
