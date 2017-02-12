package com.uchitate.web.controller.shop;

import com.uchitate.core.entity.Shop;
import com.uchitate.core.service.ShopService;
import com.uchitate.web.support.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shops/create")
public class ShopCreateController {

	@Autowired
	private ShopService shopService;

	@ModelAttribute("genres")
	public Genre[] setupGenres() {
		return Genre.values();
	}

	@GetMapping
	public String create(Model model) {
		ShopCreateForm form = new ShopCreateForm();
		model.addAttribute("form", form);
		return "shop/create";
	}

	@PostMapping
	public String create(
			@ModelAttribute ShopCreateForm form,
			Model model) {
		Shop savedShop = shopService.create(form.toRequest());
		model.addAttribute("savedShop", savedShop);
		return "redirect:/shops";
	}
}
