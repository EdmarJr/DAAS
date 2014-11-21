package br.jus.stj.saad.persistence;

import java.util.List;
import java.util.Properties;

public interface GenericDAO<T> {
	
	T incluir(T entity);
	
	T atualizar(T entity);
	
	public T obterPorId(String nomeCampo, Class<T> clazz, Object id);
	
	public T obterPorId(Class<T> clazz, Object id);
	
	public void inclurLista(List<T> entidades);
	
	void excluir(T entity);
	
	public T inicializar(T object, String... nomeCampo);
	
	T sincronizar(T entity);
	
	T obter(T entity);
	
	List<T> recuperarTodos(Class<T> entity);
	
	List<T> recuperarPorParametro(T entity);
	
	
	List<Object> executarQueryJPQL(String jpql) 
			throws IllegalStateException;
	
	int excluirJPQL(String jpql, Properties parametros);
	
	int atualizarJPQL(String jpql, Properties parametros);
	
	Object executaJPQL(String jpql)
			throws IllegalStateException;
	
	Object executaJPQL(String jpql, Properties parametros)
			throws IllegalStateException;
	
	List<Object> executaNativeQuery(String sql,
			Properties parametros) throws IllegalStateException;
	
	List<Object> executaNativeQuery(String sql)
			throws IllegalStateException;
	
	Object executaNativeQuerySingleResult(String sql,
			Properties parametros) throws IllegalStateException;
	
	List<T> executaJpqlListResult(final StringBuilder jpql, final Properties parametros)
			throws IllegalStateException;
	
	T executaJpqlUniqueResult(final StringBuilder jpql, final Properties parametros)
			throws IllegalStateException;
	
	void flush();
	
}
