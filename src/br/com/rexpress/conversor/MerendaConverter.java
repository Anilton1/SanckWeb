package br.com.rexpress.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.rexpress.valuesObjects.Merenda;

@FacesConverter(value = "MerendaConverter")
public class MerendaConverter implements Converter {

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
		if(value instanceof Merenda){
			Merenda merenda = (Merenda) value;
			if(merenda != null && merenda instanceof Merenda && merenda.getId() !=null){
				component.getAttributes().put(merenda.getId().toString(), merenda);
				return merenda.getId().toString();
			}
		}
		return "";
	}

}