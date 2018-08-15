package tk.tommy.tools;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class CustomerSpringAnnotionFactoryBean {






	public static List<CustDbInfo> getCustsInfo() {

		List<CustDbInfo> list = new ArrayList<>();

		AnnotationConfigApplicationContext anno = new AnnotationConfigApplicationContext(MyConfig.class);

		System.out.println("anno " + anno);
		System.out.println("anno " + anno);
		System.out.println("anno " + anno);
		System.out.println("anno " + anno);
		list.add((CustDbInfo) anno.getBean("custDbInfo"));
		list.add((CustDbInfo) anno.getBean("custDbInfo"));
		list.add((CustDbInfo) anno.getBean("custDbInfo"));
		list.add((CustDbInfo) anno.getBean("custDbInfo"));

		System.out.println("anno " + list.size());
		System.out.println("anno " + list.size());
		System.out.println("anno " + list.size());

		anno.close();

		System.out.println("anno.close " + list.size());
		System.out.println("anno.close " + list.size());
		System.out.println("anno.close " + list.size());


		return list;
	}

}
