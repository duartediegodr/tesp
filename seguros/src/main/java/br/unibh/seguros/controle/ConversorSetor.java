package br.unibh.seguros.controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.naming.InitialContext;

import br.unibh.seguros.entidades.Setor;
import br.unibh.seguros.negocio.ServicoSetor;

public class ConversorSetor implements Converter{

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		try {
			ServicoSetor ss = (ServicoSetor) new InitialContext().lookup("java:global/seguros/ServicoSetor");
			return ss.find(new Long(value));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		if (value != null) {
			Setor s = (Setor) value;
			if (s.getId() != null){
				return s.getId().toString();
			}
		}
		return null;
	}

}
