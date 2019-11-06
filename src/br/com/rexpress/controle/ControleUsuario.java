package br.com.rexpress.controle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.PersistenceException;

import org.primefaces.context.RequestContext;

import br.com.rexpress.dao.UsuarioDAO;
import br.com.rexpress.rn.RNUsuario;
import br.com.rexpress.util.MensagensUtil;
import br.com.rexpress.valuesObjects.Usuario;

@ManagedBean(name = "ControleUsuario")
@SessionScoped
public class ControleUsuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UsuarioDAO aUsuarioDAO;
	private Usuario aUsuario;
	private Usuario aUserSession;
	private String login = null;
	private transient String senha = null;
	private MensagensUtil mensagem;
	private Usuario aUsuarioSelecionado = new Usuario();
	private RNUsuario aRNUsuario = new RNUsuario();

	public ControleUsuario() {
		aUsuarioDAO = new UsuarioDAO();
		aUsuario = new Usuario();
		aUserSession = new Usuario();
		mensagem = new MensagensUtil();
	}

	public void inserirUsuario() {
		try {
			if (aRNUsuario.validaSeExiste(aUsuario.getLogin()) == true) {
				mensagem.MensagemErro("Erro", "Login já existe, tente outro.", "messageInserir");
			} else {
				boolean commit = aUsuarioDAO.inserir(aUsuario);
				if (commit == true) {
					mensagem.MensagemInfo("Sucesso", "Usuário " + aUsuario.getLogin() + " cadastrado com sucesso!",
							"messageInserir");
				} else {
					mensagem.MensagemErro("Erro", "Erro ao cadastrar o usuário.", "messageInserir");
				}
			}
		} catch (PersistenceException e) {
			mensagem.MensagemErro("Erro", "Erro ao cadastrar o usuário.", "messageInserir");
		} finally {
			aUsuario = new Usuario();
		}
	}

	public Usuario selecionarUsuario(Usuario pUsuario) {
		try {
			setUsuarioSelecionado(pUsuario);
		} catch (Exception e) {
			// caso ocorra algum erro ao selecionar o item, irá apresentar a
			// mensagem de erro
			mensagem.MensagemErro("Erro ao selecionar o Usuário", "Ocorreu algum erro na hora de selecionar o Usuário!",
					"menssageDataTable");
		}
		return aUsuarioSelecionado;
	}

	public void alterarUsuario() {
		try {
			boolean commit = aUsuarioDAO.alterar(aUsuarioSelecionado);
			if (commit == true) {
				mensagem.MensagemInfo("Sucesso!",
						"Usuário " + aUsuarioSelecionado.getNomeUsuario() + " alterado com sucesso", "menssageAlterar");
			} else {
				mensagem.MensagemErro("Erro ao alterar o Usuário!", "Erro ao alterar este Usuário!", "menssageAlterar");
			}

		} catch (PersistenceException e) {
			// caso ocorra algum erro de persistência, irá apresentar a mensagem
			// de erro
			mensagem.MensagemErro("Erro ao alterar o Usuário!", "Erro ao alterar este Usuário!", "menssageAlterar");
		} finally {
			aUsuarioSelecionado = new Usuario();
		}

	}

	public void alterarDialog() {

		HashMap<String, Object> opcoes = new HashMap<String, Object>();

		opcoes.put("modal", true);
		opcoes.put("contentWidth", 900);

		RequestContext.getCurrentInstance().openDialog("alterarUsuario", opcoes, null);
	}

	public void deletarUsuario(Usuario pUsuario) {
		try {
			boolean commit = aUsuarioDAO.deletar(pUsuario);
			if (commit == true) {
				mensagem.MensagemInfo("Usuário" + pUsuario.getLogin() + " excluído com sucesso!",
						"Usuário excluído com sucesso!", "messageDataTable");
			} else {
				mensagem.MensagemErro("Erro ao excluir o Usuário" + pUsuario.getLogin(),
						"Ocorreu algum erro ao excluir o Usuário!", "messageDataTable");
			}
		} catch (Exception e) {
			mensagem.MensagemErro("Erro ao excluir o Usuário" + pUsuario.getLogin(),
					"Ocorreu algum erro ao excluir o Usuário!", "messageDataTable");
		}
	}

	public String beginSession(){
		String resposta = "index.xhtml?faces-redirect=true";
		if(aRNUsuario.valida(aUserSession.getLogin(), aUserSession.getSenha()) == true){
			resposta = "principal.xhtml?faces-redirect=true";
		}
		return resposta;
	}
	
	public String logout(){
		aUserSession = new Usuario();
		return "index.xhtml?faces-redirect=true";
	}

	public UsuarioDAO getUsuarioDAO() {
		return aUsuarioDAO;
	}

	public List<Usuario> listarUsuarios() {
		return aUsuarioDAO.listarTudo();
	}

	public void setUsuarioDAO(UsuarioDAO pdao) {
		this.aUsuarioDAO = pdao;
	}

	public Usuario getUsuario() {
		return aUsuario;
	}

	public void setUsuario(Usuario u) {
		this.aUsuario = u;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuarioSelecionado() {
		return aUsuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario aUsuarioSelecionado) {
		this.aUsuarioSelecionado = aUsuarioSelecionado;
	}

	public Usuario getaUserSession() {
		return aUserSession;
	}

	public void setaUserSession(Usuario aUserSession) {
		this.aUserSession = aUserSession;
	}

}
