package br.com.flaprc.servicos;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.flaprc.modelo.Usuario;
import br.com.flaprc.persistencia.UsuarioDAO;

@RequestScoped
@ManagedBean(name = "usuarioServico")
public class UsuarioServico {
	
	private LazyDataModel<Usuario> usuarios;
	
	@Inject
	private UsuarioDAO dao;
	
	private List<Usuario> valoresComboUsuario;
	
	private Usuario usuario;
	
	public LazyDataModel<Usuario> getUsuarios() {
		
		if (this.usuarios == null) {
		
			this.usuarios = new LazyDataModel<Usuario>() {
				private static final long serialVersionUID = 1L;
			    @Override
		        public List<Usuario> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
			    	List<Usuario> result = getListaUsuarios(first, pageSize, sortField, sortOrder, filters);
		            return result;
		        }
			};
			
			this.usuarios.setRowCount(getCountRegistros());
			
		}
		
		return this.usuarios;
		
	}
	
	private List<Usuario> getListaUsuarios(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		return getDao().pesquisarPaginado(first, pageSize, sortField, sortOrder, filters, Usuario.class);

	}
	
	public Integer getCountRegistros() {
		
		return getDao().getCountRegistros();
		
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
			
		return dao.getEm().find(Usuario.class, idUsuario); 

	}

	public UsuarioDAO getDao() {
		return dao;
	}

	public void setDao(UsuarioDAO dao) {
		this.dao = dao;
	}
	
}