package br.jus.stj.saad.business;

import java.util.List;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;

import br.jus.stj.saad.entity.local.TipoDocumento;

public interface DocumentTypeBusiness {
	
	public FileObject getNetworkDirectoryStructure() throws FileSystemException;
	public List<TipoDocumento> getAll();
	public List<Object[]> getAllByFilter(TipoDocumento documentType);
	public void removeAll(List<TipoDocumento> documentTypeList);
	public void add(TipoDocumento documentType, String root) throws Exception;
	public void update(TipoDocumento documentType) throws Exception;

}
