package br.com.rexpress.valuesObjects;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-11-06T00:08:20.790-0300")
@StaticMetamodel(Rota.class)
public class Rota_ {
	public static volatile SingularAttribute<Rota, Integer> id;
	public static volatile SingularAttribute<Rota, String> descricaoRota;
	public static volatile SingularAttribute<Rota, String> nome;
	public static volatile ListAttribute<Rota, Instituicao> instituicaos;
	public static volatile ListAttribute<Rota, Mapa> mapas;
}
