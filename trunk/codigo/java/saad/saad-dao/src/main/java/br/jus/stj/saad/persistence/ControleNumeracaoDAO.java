package br.jus.stj.saad.persistence;

import br.jus.stj.saad.entity.local.ControleNumeracao;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.entity.related.Local;

public interface ControleNumeracaoDAO extends GenericDAO<ControleNumeracao> {

	public ControleNumeracao obterPorId(TipoDocumento tipoDocumento,
			Local local, Long ano);
}
