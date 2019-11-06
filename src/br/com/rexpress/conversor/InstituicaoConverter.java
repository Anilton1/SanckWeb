package br.com.rexpress.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.rexpress.valuesObjects.Instituicao;

@FacesConverter(value = "InstituicaoConverter")
public class InstituicaoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if(value != null && !value.isEmpty())
			return component.getAttributes().get(value);
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value instanceof Instituicao){
			Instituicao instituicao = (Instituicao) value;
			if(instituicao != null && instituicao instanceof Instituicao && instituicao.getId() !=null){
				component.getAttributes().put(instituicao.getId().toString(), instituicao);
				return instituicao.getId().toString();
			}
		}
		return "";
	}

}