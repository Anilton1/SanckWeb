package br.com.rexpress.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MensagensUtil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MensagensUtil() {
		
	}
	
	public FacesContext MensagemErro(String resumoMensagem, String mensagem,
			String outcomeGrowl) {

		FacesContext contexto = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				resumoMensagem, mensagem);
		contexto.addMessage(outcomeGrowl, msg);

		return contexto;
	}

	public FacesContext MensagemInfo(String resumoMensagem, String mensagem,
			String outcomeGrowl) {

		FacesContext contexto = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				resumoMensagem, mensagem);
		contexto.addMessage(outcomeGrowl, msg);

		return contexto;
	}

	public FacesContext MensagemWarning(String resumoMensagem, String mensagem,
			String outcomeGrowl) {

		FacesContext contexto = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
				resumoMensagem, mensagem);
		contexto.addMessage(outcomeGrowl, msg);

		return contexto;
	}

	public FacesContext MensagemErroFatal(String resumoMensagem,
			String mensagem, String outcomeGrowl) {

		FacesContext contexto = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
				resumoMensagem, mensagem);
		contexto.addMessage(outcomeGrowl, msg);

		return contexto;
	}

}
