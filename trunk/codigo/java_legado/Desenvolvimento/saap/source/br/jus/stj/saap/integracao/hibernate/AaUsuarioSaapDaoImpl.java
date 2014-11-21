/*
 * AaUsuarioDaoImpl.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */

package br.jus.stj.saap.integracao.hibernate;

import java.util.Collection;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.jus.stj.alp01.hibernate.dao.HibernateDAOAbstrato;
import br.jus.stj.saap.entidade.AaUsuario;
import br.jus.stj.saap.entidade.SaapAcompPendenciaAdm;
import br.jus.stj.saap.integracao.AaUsuarioSaapDAO;

/**
 * Classe para persistência do objeto <code>AaUsuario</code>.
 *
 * @author Politec Informática S/A
 */
@Repository
class AaUsuarioSaapDaoImpl extends HibernateDAOAbstrato<AaUsuario> 
		implements AaUsuarioSaapDAO {
	// Classe gerada
	
	public Collection<AaUsuario> consultarUsuariosPendencia(SaapAcompPendenciaAdm entidade) {
		Query query = getQuery("consultar-usuarios-pendencia");
		query.setInteger(0, entidade.getSaapPendenciaAdmPresidencia().getId());
		query.setInteger(1, entidade.getSeqUsuario());	
		return consultar(query);
	}
	
	
}
