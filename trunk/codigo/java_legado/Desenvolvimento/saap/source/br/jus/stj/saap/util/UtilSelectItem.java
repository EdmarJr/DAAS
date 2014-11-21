/*
 * UtilSelectItem.java
 * 
 * Data de criação: 08/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */

package br.jus.stj.saap.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.jxpath.JXPathContext;

import br.jus.stj.alp01.arquitetura.entidade.Entidade;

/**
 * Responsável pela representação de um select item.
 * 
 * @author adrianop
 */
public final class UtilSelectItem {

	/**
	 * Construtor
	 */
	private UtilSelectItem() {
		// Construtor
	}

	/**
	 * @param lista Lista
	 * @return lista de select item
	 */
	public static List<SelectItem> createSelectItemList(
			Collection<? extends Entidade> lista) {

		List<SelectItem> retorno = new ArrayList<SelectItem>();
		Iterator<? extends Entidade> iter = lista.iterator();

		while (iter.hasNext()) {
			Object element = iter.next();

			SelectItem selectItem = new SelectItem(element, String
					.valueOf(element));
			retorno.add(selectItem);
		}
		return retorno;
	}

	/**
	 * Recebe uma lista de beans como parametro e devolve uma lista de
	 * selectItem do JSF
	 * 
	 * @param lista = Uma lista de beans qualquer.
	 * @param fieldLabel = O atributo dos beans que serão exibidos no label do
	 *            selectItem
	 * @return Retorna uma lista de SelectItem para ser usado em páginas JSF
	 */
	public static List<SelectItem> createSelectItemList(
			Collection<? extends Entidade> lista, String fieldLabel) {

		List<SelectItem> retorno = new ArrayList<SelectItem>();
		Iterator<? extends Entidade> iter = lista.iterator();

		while (iter.hasNext()) {
			Object element = iter.next();

			try {

				JXPathContext context = JXPathContext.newContext(element);
				Object label = context.getValue(fieldLabel);

				SelectItem selectItem = new SelectItem(element, String
						.valueOf(label));
				retorno.add(selectItem);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}

	/**
	 * Recebe uma lista de beans como parametro e devolve uma lista de
	 * selectItem do JSF.
	 * 
	 * @param lista = Uma lista de beans qualquer.
	 * @param fieldValue = O atributo dos beans que serão inseridos no value dos
	 *            elementos de seleção.
	 * @param fieldLabel = O atributo dos beans que serão exibidos no label do
	 *            selectItem
	 * @return Retorna uma lista de SelectItem para ser usado em páginas JSF
	 */
	public static List<SelectItem> createSelectItemList(
			Collection<? extends Entidade> lista, String fieldValue,
			String fieldLabel) {

		List<SelectItem> retorno = new ArrayList<SelectItem>();
		Iterator<? extends Entidade> iter = lista.iterator();

		while (iter.hasNext()) {
			Object element = iter.next();

			try {

				JXPathContext context = JXPathContext.newContext(element);
				Object label = context.getValue(fieldLabel);
				Object value = context.getValue(fieldValue);

				if (value instanceof Integer) {
					value = String.valueOf(value);
				}

				SelectItem selectItem = new SelectItem(value, String
						.valueOf(label));
				retorno.add(selectItem);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}
}
