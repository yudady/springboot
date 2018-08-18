package tk.tommy.springboot.registerBeanDefinitions;

import java.lang.annotation.*;

import org.springframework.context.annotation.Import;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(value = {EnableThrowableRegistrar.class})
public @interface EnableThrowable {
	Class<?>[] targets() default {};
}