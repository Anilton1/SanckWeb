package br.com.rexpress.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.rexpress.util.DAOGenerico;
import br.com.rexpress.util.EntityManagerUtil;
import br.com.rexpress.valuesObjects.Endereco;

public class EnderecoDAO extends DAOGenerico<Endereco>{
	private EntityManager aEntityManager;
	private ArrayList<String> listaEnderecos;
	
	public EnderecoDAO(){
		listaEnderecos = new ArrayList<String>();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Endereco> listarTudo(){
		aEntityManager = EntityManagerUtil.getEntityManager();
		Query q = aEntityManager.createNamedQuery("Endereco.findAll");
		List<Endereco> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}
	
	public ArrayList<String> getListaEnderecos() {
		return listaEnderecos;
	}
}
