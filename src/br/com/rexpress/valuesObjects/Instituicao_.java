package br.com.rexpress.valuesObjects;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-11-06T00:08:20.763-0300")
@StaticMetamodel(Instituicao.class)
public class Instituicao_ {
	public static volatile SingularAttribute<Instituicao, Integer> id;
	public static volatile SingularAttribute<Instituicao, String> nomeInstituicao;
	public static volatile SingularAttribute<Instituicao, Boolean> tipo;
	public static volatile SingularAttribute<Instituicao, Endereco> endereco;
	public static volatile SingularAttribute<Instituicao, Rota> rota;
	public static volatile ListAttribute<Instituicao, Mapa> mapas;
	public static volatile ListAttribute<Instituicao, Turno> turnos;
}
