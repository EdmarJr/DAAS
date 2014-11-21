package br.jus.stj.saad.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.auth.StaticUserAuthenticator;
import org.apache.commons.vfs2.impl.DefaultFileSystemConfigBuilder;
import org.apache.commons.vfs2.provider.ftp.FtpFileSystemConfigBuilder;

import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.persistence.TipoDocumentoDAO;

/**
 * Session Bean implementation class DocumentType
 */
@Stateless
public class DocumentTypeBean implements DocumentTypeBusiness {

	@Inject
	private TipoDocumentoDAO tipoDocumentoDAO;

	private String domain = "127.0.0.1";
	private String user = "";
	private String password = "";

	@Override
	public FileObject getNetworkDirectoryStructure() throws FileSystemException {
		StaticUserAuthenticator auth = new StaticUserAuthenticator(this.domain,
				this.user, this.password);
		FileSystemOptions opts = new FileSystemOptions();

		DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts,
				auth);
		FtpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);
		return VFS.getManager().resolveFile(
				"ftp://" + this.domain + "/actualize/", opts);
	}

	@Override
	public List<TipoDocumento> getAll() {
		return this.tipoDocumentoDAO.recuperarTodos(TipoDocumento.class);
	}
	
	@Override
	public List<Object[]> getAllByFilter(TipoDocumento documentType) {
		
		return this.tipoDocumentoDAO.listByFilter(documentType);
	}

	@Override
	public void removeAll(List<TipoDocumento> documentTypeList) {
		int iDocType = 0;

		while (iDocType < documentTypeList.size()) {

			//mockTest.delete(documentTypeList.get(iDocType));
			iDocType++;
		}

	}

	@Override
	public void add(TipoDocumento documentType, String root) throws Exception {

		/*
		if (documentType.getPhysicalNetworkDirectory() != null) {

			StaticUserAuthenticator auth = new StaticUserAuthenticator(
					this.domain, this.user, this.password);
			FileSystemOptions fileSystemOptions = new FileSystemOptions();

			DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(
					fileSystemOptions, auth);
			FtpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(
					fileSystemOptions, true);

			FileObject newDirectory = VFS
					.getManager()
					.resolveFile(
							"ftp://"
									+ this.domain
									+ "/actualize/"
											.concat(root)
											.concat(documentType
													.getPhysicalNetworkDirectory()),
							fileSystemOptions);

			if (!newDirectory.exists()) {

				newDirectory.createFolder();
				DocumentTypeMock mockTest = new DocumentTypeMock();
				mockTest.insert(documentType);

			} else {
				throw new Exception("O diretório informado já existe!");
			}
		} else {

			DocumentTypeMock mock = new DocumentTypeMock();
			mock.insert(documentType);
		}
		*/
		
		this.tipoDocumentoDAO.incluir(documentType);
	}

	@Override
	public void update(TipoDocumento documentType) throws Exception {

		this.tipoDocumentoDAO.atualizar(documentType);

	}

}
