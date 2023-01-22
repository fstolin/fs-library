package cz.uhk.kppro.fs.termwork.fslibrary.initialTest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {	
	
	@RequestMapping("/hello")
	public String helloWorld() {		
		return "hello-world";
	}
}
