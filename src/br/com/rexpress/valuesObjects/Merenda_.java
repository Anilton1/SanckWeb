package br.com.rexpress.valuesObjects;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-11-13T17:32:03.738-0200")
@StaticMetamodel(Merenda.class)
public class Merenda_ {
	public static volatile SingularAttribute<Merenda, Integer> id;
	public static volatile SingularAttribute<Merenda, String> descricaoMerenda;
	public static volatile ListAttribute<Merenda, Descartavel> descartavels;
	public static volatile ListAttribute<Merenda, Item> items;
}
