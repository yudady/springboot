package tk.tommy.springboot.importSelector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;
import tk.tommy.config.MyConfig1;
import tk.tommy.config.MyConfig2;

@Component
public class MyImportSelector implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		AnnotationAttributes attributes = AnnotationAttributes
			.fromMap(importingClassMetadata.getAnnotationAttributes(EnableSomeModule.class.getName(), false));

		String value = attributes.getString("value");
		System.out.println("************************");
		System.out.println("*******@EnableSomeModule(\"someValue\")*****************");
		System.out.println("***ImportSelector*******[" + value + "]**************");
		System.out.println("***ImportSelector*******[" + value + "]**************");
		System.out.println("***ImportSelector*******[" + value + "]**************");
		System.out.println("************************");
		System.out.println("************************");
		String prop = System.getProperty("myProp");
		if ("someValue".equals(prop)) {
			return new String[]{MyConfig1.class.getName()};
		} else {
			return new String[]{MyConfig2.class.getName()};
		}
	}
}