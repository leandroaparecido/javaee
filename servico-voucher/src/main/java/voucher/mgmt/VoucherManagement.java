package voucher.mgmt;

import java.lang.management.*;
import java.util.concurrent.atomic.*;

import javax.annotation.*;
import javax.ejb.*;
import javax.enterprise.event.*;
import javax.management.*;

@Singleton
@Startup
@Local(VoucherManagementListener.class)
public class VoucherManagement implements VoucherManagementMXBean, VoucherManagementListener {

	private AtomicInteger invalidVouchersCount = new AtomicInteger();

	private MBeanServer platformMBeanServer;
	private ObjectName objectName;

	@PostConstruct
	public void registerInJmx() throws JMException {
		objectName = new ObjectName("Monitoring:type=" + this.getClass().getSimpleName());
		platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
		platformMBeanServer.registerMBean(this, objectName);
	}

	@PreDestroy
	public void unregisterFromJMX() throws JMException {
		platformMBeanServer.unregisterMBean(this.objectName);
	}

	public void incrementInvalidVouchers(@Observes Integer event) {
		invalidVouchersCount.addAndGet(event);
	}

	@Override
	public int getInvalidVouchersCount() {
		return invalidVouchersCount.get();
	}
}
