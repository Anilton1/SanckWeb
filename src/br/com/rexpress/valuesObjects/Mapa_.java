package br.com.rexpress.valuesObjects;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-11-23T22:39:12.138-0300")
@StaticMetamodel(Mapa.class)
public class Mapa_ {
	public static volatile SingularAttribute<Mapa, Integer> id;
	public static volatile SingularAttribute<Mapa, Date> dataProcessamento;
	public static volatile SingularAttribute<Mapa, Date> dataroteirizacao;
	public static volatile SingularAttribute<Mapa, Boolean> tipo;
	public static volatile SingularAttribute<Mapa, Instituicao> instituicao;
	public static volatile SingularAttribute<Mapa, Rota> rota;
	public static volatile ListAttribute<Mapa, TurnoMapa> turnoMapas;
}
