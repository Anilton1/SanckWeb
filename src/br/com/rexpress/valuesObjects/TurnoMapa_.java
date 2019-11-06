package br.com.rexpress.valuesObjects;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-11-23T23:21:24.694-0300")
@StaticMetamodel(TurnoMapa.class)
public class TurnoMapa_ {
	public static volatile SingularAttribute<TurnoMapa, Integer> id;
	public static volatile SingularAttribute<TurnoMapa, String> descricao;
	public static volatile SingularAttribute<TurnoMapa, Integer> qtdAlunos1;
	public static volatile SingularAttribute<TurnoMapa, Integer> qtdAlunos2;
	public static volatile SingularAttribute<TurnoMapa, Integer> qtdAlunos3;
	public static volatile SingularAttribute<TurnoMapa, Mapa> mapa;
	public static volatile SingularAttribute<TurnoMapa, MerendaMapaHist> merendaMapaHist;
	public static volatile ListAttribute<TurnoMapa, ItemMapa> itemMapas;
}
