package br.com.rexpress.valuesObjects;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-11-07T16:49:03.881-0300")
@StaticMetamodel(Descartavel.class)
public class Descartavel_ {
	public static volatile SingularAttribute<Descartavel, Integer> id;
	public static volatile SingularAttribute<Descartavel, String> descricao;
	public static volatile ListAttribute<Descartavel, Merenda> merendas;
	public static volatile ListAttribute<Descartavel, MerendaMapaHist> merendasMapa;
}
