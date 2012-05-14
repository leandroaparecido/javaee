package test.mgmt;

import java.lang.management.*;
import java.util.concurrent.atomic.*;

import javax.annotation.*;
import javax.ejb.*;
import javax.enterprise.event.*;
import javax.management.*;

@Singleton
@Startup
public class TestManagement implements TestManagementMXBean, TestManagementListener {

	private AtomicInteger updateCount = new AtomicInteger();

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

	public void incrementUpdateCount(@Observes Integer count) {
		updateCount.addAndGet(count);
	}

	@Override
	public int getUpdateCount() {
		return updateCount.get();
	}
}
