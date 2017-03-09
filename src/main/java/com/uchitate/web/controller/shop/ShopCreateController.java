package com.uchitate.web.controller.shop;

import com.uchitate.core.entity.Shop;
import com.uchitate.core.service.ShopService;
import com.uchitate.web.support.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/shops/create")
public class ShopCreateController {

	public static final String FORM_MODEL_KEY = "form";
	public static final String ERROR_MODEL_KEY = BindingResult.MODEL_KEY_PREFIX + FORM_MODEL_KEY;

	@Autowired
	private ShopService shopService;

	@ModelAttribute("genres")
	public Genre[] setupGenres() {
		return Genre.values();
	}

	@GetMapping
	public String create(Model model) {
		ShopCreateForm form = (ShopCreateForm) model.asMap().get(FORM_MODEL_KEY);
		if (form == null) {
			form = new ShopCreateForm();
		}
		model.addAttribute("form", form);
		return "shop/create";
	}

	@PostMapping
	public String create(
			@RequestParam(required = false) String query,
			@ModelAttribute(FORM_MODEL_KEY) @Validated ShopCreateForm form,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute(FORM_MODEL_KEY, form);
		redirectAttributes.addFlashAttribute(ERROR_MODEL_KEY, result);
		redirectAttributes.addAttribute("query", query);

		if (result.hasErrors()) {
			return "redirect:/shops/create?error";
		}

		Shop savedShop = shopService.create(form.toRequest());
		redirectAttributes.addFlashAttribute("savedShop", savedShop);
		return "redirect:/shops";
	}
}
