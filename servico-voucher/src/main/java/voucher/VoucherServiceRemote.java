package voucher;

import javax.ejb.*;

@Remote
public interface VoucherServiceRemote {

	String sayHello(String to);

}
