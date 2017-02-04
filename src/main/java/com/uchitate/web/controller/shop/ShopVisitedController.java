package com.uchitate.web.controller.shop;

import com.uchitate.core.entity.Shop;
import com.uchitate.core.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shops/visited/{id}")
public class ShopVisitedController {

	@Autowired
	private ShopService shopService;

	@GetMapping
	public String visited(
			@PathVariable Integer id,
			Model model) {
		Shop visitedShop = shopService.visited(id);
		model.addAttribute("visitedShop", visitedShop);
		return "redirect:/shops";
	}
}
