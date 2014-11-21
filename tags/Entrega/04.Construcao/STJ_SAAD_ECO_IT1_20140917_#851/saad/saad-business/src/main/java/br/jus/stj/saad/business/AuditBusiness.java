package br.jus.stj.saad.business;

import java.util.List;
import java.util.Map;

import br.jus.stj.saad.entity.local.Audit;

public interface AuditBusiness {

	public Map<Long, List<Audit>> getDataPage(int fromIndex, int toIndex,
			Audit audit) throws Exception;
	
}
