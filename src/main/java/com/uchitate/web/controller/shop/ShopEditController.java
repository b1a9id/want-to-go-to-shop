package com.uchitate.web.controller.shop;

import com.uchitate.core.entity.Shop;
import com.uchitate.core.service.ShopService;
import com.uchitate.web.controller.CommonController;
import com.uchitate.web.support.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/shops/edit/{id}")
public class ShopEditController extends CommonController {

	public static final String FORM_MODEL_KEY = "form";
	public static final String ERROR_MODEL_KEY = BindingResult.MODEL_KEY_PREFIX + FORM_MODEL_KEY;

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
		ShopEditForm form = (ShopEditForm) model.asMap().get(FORM_MODEL_KEY);
		if (form == null) {
			form = new ShopEditForm(
					shop.getGenre(),
					shop.getShopName(),
					shop.getStation(),
					shop.getUrl(),
					shop.getMemo()
			);
		}

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
			@RequestParam(required = false) String query,
			@Valid @ModelAttribute ShopEditForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes,
			Model model) {
		redirectAttributes.addFlashAttribute(FORM_MODEL_KEY, form);
		redirectAttributes.addFlashAttribute(ERROR_MODEL_KEY, result);
		redirectAttributes.addAttribute("query", query);

			if (result.hasErrors()) {
			return "redirect:/shops/edit/{id}?error";
		}

		Shop updatedShop = shopService.edit(id, form.toRequest());
		redirectAttributes.addFlashAttribute("updatedShop", updatedShop);
		return "redirect:/shops";
	}
}
