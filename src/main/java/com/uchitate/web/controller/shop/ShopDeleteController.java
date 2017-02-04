package com.uchitate.web.controller.shop;

import com.uchitate.core.entity.Shop;
import com.uchitate.core.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shops/delete/{id}")
public class ShopDeleteController {

	@Autowired
	private ShopService shopService;

	@DeleteMapping
	public String delete(
			@PathVariable Integer id,
			Model model) {
		shopService.delete(id);

		Shop deletedShop = shopService.getShop(id);
		if (deletedShop != null) {
			model.addAttribute("deletedShop", deletedShop);
		}
		return "redirect:/shops";
	}
}
