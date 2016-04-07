package br.com.flaprc.persistencia;
import javax.persistence.Query;

public class UsuarioDAO extends DAO {

	public Integer getCountRegistros() {

		Query query = getEm().createNativeQuery("select count(idUsuario) from usuario");

		return Integer.valueOf(query.getResultList().get(0).toString());

	}

}