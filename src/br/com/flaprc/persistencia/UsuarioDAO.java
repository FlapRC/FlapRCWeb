package br.com.flaprc.persistencia;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.primefaces.model.SortOrder;

import br.com.flaprc.modelo.Usuario;

public class UsuarioDAO extends DAO {

	public List<Usuario> getListaUsuarios(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		CriteriaBuilder cb = getEm().getCriteriaBuilder();
		CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
		Root<Usuario> root = cq.from(Usuario.class);
		cq.select(root);
		TypedQuery<Usuario> tq = getEm().createQuery(cq);

		if (pageSize >= 0) {
			tq.setMaxResults(pageSize);
		}
		if (first >= 0) {
			tq.setFirstResult(first);
		}

		return tq.getResultList();

	}

	public Integer getCountRegistros() {

		Query query = getEm().createNativeQuery("select count(idUsuario) from usuario");

		return Integer.valueOf(query.getResultList().get(0).toString());

	}

}