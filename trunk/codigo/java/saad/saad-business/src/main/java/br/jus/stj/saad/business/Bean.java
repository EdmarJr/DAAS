package br.jus.stj.saad.business;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.jus.stj.saad.persistence.GenericDAO;
import br.jus.stj.saad.persistence.GenericDAOMssqlImpl;

public abstract class Bean<T> {
	
	protected static final Logger LOGGER = LoggerFactory
			.getLogger(GenericDAOMssqlImpl.class);
	
	public void incluir(T entidade) {
		getDao().incluir(entidade);
	}

	public void alterar(T entidade) {
		getDao().atualizar(entidade);
	}

	public void excluir(T entidade) {
		getDao().excluir(entidade);
	}
	
	public List<T> listar(Class<T> clazz) {
		return getDao().recuperarTodos(clazz);
	}
	
	public T inicializar(T objeto, String... nomeCampo) {
		return getDao().inicializar(objeto, nomeCampo);
	}
	
	public List<T> inicializarLista(List<T> lista, String... campo) {
		
		List<T> novaLista = new ArrayList<T>();
		
		for (T t : lista) {
			novaLista.add(getDao().inicializar(t, campo));
		}
		
		return novaLista;
	}
	
	public T sincronizar(T objeto) {
		return getDao().sincronizar(objeto);
	}
	
	public T obterPorId(Class<T> clazz, Object id) {
		return getDao().obterPorId(clazz, id);
	}

	public abstract GenericDAO<T> getDao();
}
