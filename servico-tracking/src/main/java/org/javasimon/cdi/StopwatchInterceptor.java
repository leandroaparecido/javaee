package org.javasimon.cdi;

import javax.interceptor.*;

import org.javasimon.*;

/**
 * Interceptor stop measure method invocations execution time.
 * 
 * @since 3.2.2
 */
@Chrono
@Interceptor
public class StopwatchInterceptor {
	/**
	 * Returns Simon name for the specified Invocation context.
	 * 
	 * @param context Invocation context
	 * @return fully qualified name of the Simon
	 */
	protected String getSimonName(InvocationContext context) {
		String className = context.getMethod().getDeclaringClass().getName();
		Chrono chrono = context.getMethod().getAnnotation(Chrono.class);
		String nameSuffix = "".equals(chrono.value()) ? context.getMethod().getName() : chrono.value();
		return className + Manager.HIERARCHY_DELIMITER + nameSuffix;
	}

	/**
	 * Around invoke method that measures the split for one method invocation.
	 * 
	 * @param context invocation context
	 * @return return value from the invocation
	 * @throws Exception exception thrown from the invocation
	 */
	@AroundInvoke
	public Object monitor(InvocationContext context) throws Exception {
		if (context.getMethod().getAnnotation(Chrono.class) == null) {
			return context.proceed();
		}
		String simonName = getSimonName(context);
		Split split = SimonManager.getStopwatch(simonName).start();
		try {
			return context.proceed();
		} finally {
			split.stop();
		}
	}
}
