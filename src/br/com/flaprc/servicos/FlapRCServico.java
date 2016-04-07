package br.com.flaprc.servicos;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.flaprc.persistencia.DAO;

public class FlapRCServico<T> {
	
	@Inject
	private DAO<T> dao;
	
	private LazyDataModel<T> lista;
	
	private Class<T> classe;
	
	public LazyDataModel<T> getLista() {
		
		if (this.lista == null && getDao() != null) {
		
			this.lista = new LazyDataModel<T>() {
				
				private static final long serialVersionUID = 1L;
			    
				@Override
		        public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
			    
					List<T> result = getDao().pesquisarPaginado(first, pageSize, sortField, sortOrder, filters, classe);
		            
					return result;
					
		        }
				
			};
			
			//this.lista.setRowCount(getCountRegistros());
			
		}
		
		return this.lista;
		
	}

	public void setLista(LazyDataModel<T> lista) {
		this.lista = lista;
	}

	public DAO<T> getDao() {
		return dao;
	}

	public void setDao(DAO<T> dao) {
		this.dao = dao;
	}
	
}