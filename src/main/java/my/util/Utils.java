package my.util;

import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import java.io.IOException;

/**
 */
@ManagedBean
@Component
public class Utils {

    public void renderJson(String json) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.setResponseContentType("application/json");
        externalContext.setResponseCharacterEncoding("UTF-8");
        externalContext.getResponseOutputWriter().write(json);
        facesContext.responseComplete();
    }

    public String get(boolean b, String value, String altValue) {
        return b ? value : altValue;
    }

    public boolean value() {
        return false;
    }

}
