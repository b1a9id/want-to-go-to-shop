package com.uchitate.config;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Controller
@RequestMapping("/indexing")
public class IndexingController {

	@PersistenceContext
	private EntityManager entityManager;

	@GetMapping
	public String index(RedirectAttributes redirectAttributes) throws InterruptedException {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		fullTextEntityManager.createIndexer().start();

		redirectAttributes.addFlashAttribute("reIndex", true);
		return "redirect:/shops";
	}
}
