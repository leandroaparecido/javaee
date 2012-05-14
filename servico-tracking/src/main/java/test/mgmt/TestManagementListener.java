package test.mgmt;

import javax.ejb.*;

@Local
public interface TestManagementListener {

	void incrementUpdateCount(Integer count);

}
