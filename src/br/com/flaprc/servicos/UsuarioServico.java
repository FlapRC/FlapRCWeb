package br.com.flaprc.servicos;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.primefaces.model.LazyDataModel;

import br.com.flaprc.modelo.Usuario;

@RequestScoped
@ManagedBean(name = "usuarioServico")
public class UsuarioServico extends FlapRCServico<Usuario> {
	
	private List<Usuario> valoresComboUsuario;
	
	private Usuario usuario;
	
	public LazyDataModel<Usuario> getUsuarios() {
		
		return getLista();
		
	}
	
	public List<Usuario> getValoresComboUsuario() {
		
		CriteriaBuilder cb = getDao().getEm().getCriteriaBuilder();
		CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
		Root<Usuario> root = cq.from(Usuario.class);
		cq.select(root);
		TypedQuery<Usuario> tq = getDao().getEm().createQuery(cq);

		valoresComboUsuario = tq.getResultList(); 

		return valoresComboUsuario;
		
	}
	
	public void limpar(ActionEvent event) {
//	    UIComponent form = event.getComponent().getParent();
//	    form.
//	    
//	    FacesContext context = FacesContext.getCurrentInstance();
//	    UIViewRoot viewRoot = context.getViewRoot();
//	    List<UIComponent> children = viewRoot.getChildren();
//
//	    resetInputValues(children);
	    
	}

	public void setValoresComboUsuario(List<Usuario> valoresComboUsuario) {
		this.valoresComboUsuario = valoresComboUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario recuperarUsuario(Integer idUsuario) {
			
		return getDao().getEm().find(Usuario.class, idUsuario); 

	}
	
}