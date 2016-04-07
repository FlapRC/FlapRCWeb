package br.com.flaprc.persistencia;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.primefaces.model.SortOrder;

public class DAO {

	@Inject
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public <T>List<T> pesquisarPaginado(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters, Class<T> classe) {

		CriteriaBuilder cb = getEm().getCriteriaBuilder();
		
		CriteriaQuery<T> cq = cb.createQuery(classe);
		
		Root<T> root = cq.from(classe);
		
		// Seta o campo a ser ordenado bem como o tipo de ordenação ASC ou DESC
		setSort(sortField, sortOrder, cb, cq, root);
		
		cq.select(root);
		TypedQuery<T> tq = getEm().createQuery(cq);

		if (pageSize >= 0) {
			tq.setMaxResults(pageSize);
		}
		if (first >= 0) {
			tq.setFirstResult(first);
		}

		return (List<T>)tq.getResultList();

	}

	/**
	 * Seta o campo a ser ordenado bem como o tipo de ordenação ASC ou DESC
	 * 
	 * @param sortField String campo a ser ordenado.
	 * @param sortOrder SortOrder tipo de ordenação ASC / DESC / UNSORTED.
	 * @param cb CriteriaBuilder objeto criteria.
	 * @param cq CriteriaQuery<T> objeto query da criteria.
	 * @param root Root<T>
	 */
	private <T> void setSort(String sortField, SortOrder sortOrder, CriteriaBuilder cb, CriteriaQuery<T> cq, Root<T> root) {
		
		if ((sortField != null && !"".equals(sortField)) && (sortOrder != null && !"".equals(sortOrder))) {
		
			if (sortOrder.equals(SortOrder.ASCENDING)) {
		
				cq.orderBy(cb.asc(root.get(sortField)));
				
			} else if (sortOrder.equals(SortOrder.DESCENDING)) {
				
				cq.orderBy(cb.desc(root.get(sortField)));
				
			} 
				
		}
		
	}

}