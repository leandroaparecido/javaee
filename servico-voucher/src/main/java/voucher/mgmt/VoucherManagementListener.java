package voucher.mgmt;

import javax.ejb.*;

@Local
public interface VoucherManagementListener {

	void incrementInvalidVouchers(Integer event);

}
