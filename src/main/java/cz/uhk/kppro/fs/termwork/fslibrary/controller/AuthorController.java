package cz.uhk.kppro.fs.termwork.fslibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorController {

	@RequestMapping("/author/list")
	public String displayAuthorList() {
		return "author-list";
	}
	
}
