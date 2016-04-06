import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@RequestScoped
@ManagedBean(name = "carrinhoServico")
public class CarrinhoServico {
	
	private Usuario usuario;
	
	
	
	public String salvar() {
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Enviado com sucesso!",  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
		
		return "pages/processoConcluido";
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}