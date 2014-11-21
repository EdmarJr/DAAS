package br.jus.stj.saad.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.jus.stj.saad.entity.local.Audit;
import br.jus.stj.saad.util.ActionOperation;
import br.jus.stj.saad.util.YieldDate;

public class AuditMock {

	private static LinkedList<Audit> mockedList = new LinkedList<Audit>();

	static {

		int iAudit = 0;

		Long id = new Long(0);

		while (iAudit < 60) {

			Audit audit = new Audit();

			if (iAudit % 3 == 0)
				id = id + 1;

			if (iAudit % 2 == 0) {
				audit.setTipoOperacao(ActionOperation.I);
			} else if (iAudit % 3 == 0) {
				audit.setTipoOperacao(ActionOperation.E);
			} else {
				audit.setTipoOperacao(ActionOperation.A);
			}

			Date yield = new Date();
			yield.setTime(yield.getTime() - (iAudit * 999999999));

			YieldDate yieldDate = new YieldDate();
			yieldDate.setYield(yield);

//			audit.setResponsibleUser("Responsável " + iAudit);
//			audit.setYieldDate(yieldDate);
//			try {
//				audit.setRequestedHostName(Inet4Address.getLocalHost()
//						.getHostName());
//				audit.setRequestedIP(Inet4Address.getLocalHost()
//						.getHostAddress());
//			} catch (UnknownHostException e) {
//				e.printStackTrace();
//			}

			StringBuffer entryNumber = new StringBuffer(id.toString());

			while (entryNumber.length() < 5) {
				entryNumber.insert(0, "0");
			}

//			audit.setEntryNumber(entryNumber.toString() + "-"
//					+ new SimpleDateFormat("yyyy").format(new Date()));

			mockedList.add(audit);

			iAudit++;
		}
	}

	public Map<Long, List<Audit>> getDataPage(int fromIndex, int toIndex, Audit audit) {

		Map<Long, List<Audit>> map = new HashMap<Long, List<Audit>>();

		map.put(new Long(mockedList.size()),
				new LinkedList<Audit>(mockedList.subList(fromIndex, toIndex)));

		return map;
	}

}
