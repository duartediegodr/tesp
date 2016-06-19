package br.unibh.seguros.controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.naming.InitialContext;

import br.unibh.seguros.entidades.Usuario;
import br.unibh.seguros.negocio.ServicoUsuario;

public class ConversorUsuario implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		try {
			ServicoUsuario ss = (ServicoUsuario) new InitialContext().lookup("java:global/seguros/ServicoUsuario");
			return ss.find(new Long(value));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		if (value != null) {
			Usuario s = (Usuario) value;
			if (s.getId() != null){
				return s.getId().toString();
			}
		}
		return null;
	}

}
