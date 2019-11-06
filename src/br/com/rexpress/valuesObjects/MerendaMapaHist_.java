package br.com.rexpress.valuesObjects;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-11-24T00:29:10.323-0300")
@StaticMetamodel(MerendaMapaHist.class)
public class MerendaMapaHist_ {
	public static volatile SingularAttribute<MerendaMapaHist, Integer> id;
	public static volatile SingularAttribute<MerendaMapaHist, String> descricaoMerenda;
	public static volatile ListAttribute<MerendaMapaHist, ItemMapaHist> itemMapaHists;
	public static volatile ListAttribute<MerendaMapaHist, Descartavel> descartavelsMapa;
	public static volatile ListAttribute<MerendaMapaHist, TurnoMapa> turnoMapa;
}
