package my.util;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

/**
 */
@ManagedBean
@SessionScoped
public class UserConverter implements Converter,Serializable {


	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return null;   //todo_need
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return null;   //todo_need
	}
}
