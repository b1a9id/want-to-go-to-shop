package com.uchitate.web.controller.shop;

import com.uchitate.core.entity.Shop;
import com.uchitate.core.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		double visitedNum =  visitedShops * 100 / shops.size();

		model.addAttribute("shops", shops);
		model.addAttribute("visitedNum", visitedNum);

		return "shop/list";
	}
}
