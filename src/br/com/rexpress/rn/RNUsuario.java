package br.com.rexpress.rn;

import java.util.ArrayList;
import java.util.Iterator;

import br.com.rexpress.dao.UsuarioDAO;
import br.com.rexpress.valuesObjects.Usuario;

public class RNUsuario {
	private UsuarioDAO dao;

	public RNUsuario() {
		dao = new UsuarioDAO();

	}

	public Usuario valida(String pDescricaoUsuario) {

		ArrayList<Usuario> usuarioArray = (ArrayList<Usuario>) dao.listarTudo();
		Usuario aUsuario = null;

		Iterator<Usuario> ite = usuarioArray.iterator();
		while (ite.hasNext()) {
			Usuario usuario = ite.next();
			if ((usuario.getNomeUsuario().equals(pDescricaoUsuario))) {
				aUsuario = usuario;
			}
		}
		return aUsuario;
	}

	public Boolean valida(String login, String senha) {
		ArrayList<Usuario> usuarioArray = (ArrayList<Usuario>) dao.listarTudo();
		Boolean valida = false;

		Iterator<Usuario> ite = usuarioArray.iterator();
		while (ite.hasNext()) {
			Usuario utemp = ite.next();
			if ((utemp.getLogin().equals(login)) && (utemp.getSenha().equals(senha))) {
				valida = true;
			}
		}
		return valida;
	}

	public boolean validaSeExiste(String pLogin) {
		boolean resposta = false;
		ArrayList<Usuario> usuarioArray = (ArrayList<Usuario>) dao.listarTudo();

		Iterator<Usuario> ite = usuarioArray.iterator();
		while (ite.hasNext()) {
			Usuario ptemp = ite.next();

			if (ptemp.getLogin().equals(pLogin)) {
				resposta = true;
			}
		}

		return resposta;
	}
}