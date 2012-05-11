package voucher;

import javax.ejb.*;

@Stateless
@Remote(VoucherServiceRemote.class)
public class VoucherService implements VoucherServiceRemote {
	public String sayHello(String to) {
		return "Hello " + to; 
	}
}
