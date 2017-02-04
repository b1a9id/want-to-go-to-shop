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
@RequestMapping("/shops/describe/{id}")
public class ShopDescribeController {

	@Autowired
	private ShopService shopService;

	@GetMapping
	public String describe(
			@PathVariable Integer id,
			Model model) {
		Shop shop = shopService.getShop(id);
		model.addAttribute("shop", shop);
		return "shop/describe";
	}
}
