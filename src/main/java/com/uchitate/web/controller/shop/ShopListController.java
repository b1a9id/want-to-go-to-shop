package com.uchitate.web.controller.shop;

import com.uchitate.core.entity.Shop;
import com.uchitate.core.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/shops")
public class ShopListController {

	@Autowired
	private ShopService shopService;

	@GetMapping
	public String list(Model model) {
		List<Shop> shops = shopService.getAllShops();
		Long visitedShops = shops.stream()
				.filter(shop -> Objects.nonNull(shop.getVisitedAt()))
				.count();
		BigDecimal visitedNum = shops.size() == 0 ? BigDecimal.ZERO : BigDecimal.valueOf(visitedShops * 100 / shops.size());

		model.addAttribute("shops", shops);
		model.addAttribute("visitedNum", visitedNum);

		return "shop/list";
	}

	@GetMapping("/keyword")
	public String search(
			@Validated @ModelAttribute ShopSearchForm form,
			BindingResult result,
			Model model,
			HttpServletRequest servletRequest) {
		List<Shop> shops = shopService.search(form.toRequest());
		Long visitedShops = shops.stream()
				.filter(shop -> Objects.nonNull(shop.getVisitedAt()))
				.count();
		BigDecimal visitedNum = shops.size() == 0 ? BigDecimal.ZERO : BigDecimal.valueOf(visitedShops * 100 / shops.size());

		model.addAttribute("shops", shops);
		model.addAttribute("visitedNum", visitedNum);

		return "shop/list";
	}
}
