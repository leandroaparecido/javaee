package org.javasimon.cdi;

import java.lang.annotation.*;

import javax.enterprise.util.*;
import javax.interceptor.*;

@InterceptorBinding
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Chrono {
	@Nonbinding String value() default "";
}
