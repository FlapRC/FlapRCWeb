import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import br.com.flaprc.modelo.Usuario;
import br.com.flaprc.persistencia.UsuarioDAO;
import br.com.flaprc.servicos.UsuarioServico;

@RequestScoped
@ManagedBean(name = "usuarioConverter")
public class UsuarioConverter implements Converter {
	
	@Inject
	private UsuarioDAO dao;

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

		if (value != null && !value.isEmpty()) {
			
			UsuarioServico usuarioServico = new UsuarioServico();
			usuarioServico.setDao(dao);
			
			Usuario usuario = usuarioServico.recuperarUsuario(Integer.valueOf(value));
			
			return usuario;
			
		}

		return null;
		
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null && object instanceof Usuario) {
			return String.valueOf(((Usuario) object).getIdUsuario());
		} else {
			return null;
		}
	}

}