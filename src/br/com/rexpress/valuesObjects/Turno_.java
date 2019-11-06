package br.com.rexpress.valuesObjects;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-11-06T00:08:20.795-0300")
@StaticMetamodel(Turno.class)
public class Turno_ {
	public static volatile SingularAttribute<Turno, Integer> id;
	public static volatile SingularAttribute<Turno, String> descricao;
	public static volatile SingularAttribute<Turno, Integer> qtdAlunos1;
	public static volatile SingularAttribute<Turno, Integer> qtdAlunos2;
	public static volatile SingularAttribute<Turno, Integer> qtdAlunos3;
	public static volatile SingularAttribute<Turno, Instituicao> instituicao;
}
