package com.uchitate.web.controller.shop;

import com.uchitate.core.entity.Shop;
import com.uchitate.core.service.ShopService;
import com.uchitate.web.support.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/shops/edit/{id}")
public class ShopEditController {

	@Autowired
	private ShopService shopService;

	@ModelAttribute("genres")
	public Genre[] setupGenres() {
		return Genre.values();
	}

	@GetMapping
	public String edit(
			@PathVariable Integer id,
			Model model) {
		Shop shop = shopService.getShop(id);
		ShopEditForm form = new ShopEditForm(
				shop.getGenre(),
				shop.getShopName(),
				shop.getStation(),
				shop.getUrl(),
				shop.getMemo()
		);

		model.addAttribute("form", form);
		model.addAttribute("id", id);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
		String createdAt = shop.getCreatedAt().toLocalDate().format(formatter);
		model.addAttribute("createdAt", createdAt);
		return "shop/edit";
	}

	@PatchMapping
	public String edit(
			@PathVariable Integer id,
			@ModelAttribute ShopEditForm form,
			Model model) {
		Shop updatedShop = shopService.edit(id, form.toRequest());
		model.addAttribute("updatedShop", updatedShop);
		return "redirect:/shops";
	}
}
