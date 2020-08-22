package cc.caker.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cc.caker.spring.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;

}
