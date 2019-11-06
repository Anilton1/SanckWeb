package br.com.rexpress.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.rexpress.util.DAOGenerico;
import br.com.rexpress.util.EntityManagerUtil;
import br.com.rexpress.valuesObjects.Usuario;

public class UsuarioDAO extends DAOGenerico<Usuario> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager aEntityManager;
	private ArrayList<String> listaUsuarios;
	
	public UsuarioDAO(){
		listaUsuarios = new ArrayList<String>();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listarTudo(){
		aEntityManager = EntityManagerUtil.getEntityManager();
		Query q = aEntityManager.createNamedQuery("Usuario.findAll");
		List<Usuario> retornoConsulta = q.getResultList();
		return retornoConsulta;
	}
	
	public ArrayList<String> getListaUsuarios() {
		return listaUsuarios;
	}

}
