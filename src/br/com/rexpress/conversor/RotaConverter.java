package br.com.rexpress.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.rexpress.valuesObjects.Rota;

@FacesConverter(value = "RotaConverter")
public class RotaConverter implements Converter {

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
		if(value instanceof Rota){
			Rota rota = (Rota) value;
			if(rota != null && rota instanceof Rota && rota.getId() !=null){
				component.getAttributes().put(rota.getId().toString(), rota);
				return rota.getId().toString();
			}
		}
		return "";
	}

}