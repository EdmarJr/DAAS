package br.jus.stj.saad.business;

import java.util.List;

import br.jus.stj.saad.entity.local.Document;
import br.jus.stj.saad.entity.local.Documento;

public interface DocumentBusiness {

	public List<Documento> getAll();

	public void removeAll(List<Document> documentList);

	public void add(Document document) throws Exception;

	public void update(Document document) throws Exception;

	public Documento get(Documento document) throws Exception;

}
