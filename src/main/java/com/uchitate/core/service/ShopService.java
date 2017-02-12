package com.uchitate.core.service;

import com.uchitate.core.entity.Shop;
import com.uchitate.core.model.ShopSearchRequest;
import com.uchitate.core.repository.ShopRepository;
import com.uchitate.web.support.ShopCreateRequest;
import com.uchitate.web.support.ShopEditRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShopService {

	@Autowired
	private ShopRepository shopRepository;

	public List<Shop> getAllShops() {
		return shopRepository.findAll();
	}

	public List<Shop> search(ShopSearchRequest request) {
		return shopRepository.search(request);
	}

	public Shop getShop(Integer id) {
		return shopRepository.findOne(id);
	}

	public Shop create(ShopCreateRequest request) {
		LocalDateTime now = LocalDateTime.now();

		Shop shop = new Shop();
		shop.setGenre(request.getGenre());
		shop.setShopName(request.getShopName());
		shop.setStation(request.getStation());
		shop.setUrl(request.getUrl());
		shop.setMemo(request.getMemo());
		shop.setCreatedAt(now);

		return shopRepository.saveAndFlush(shop);
	}

	public Shop edit(Integer id, ShopEditRequest request) {
		Shop shop = getShop(id);
		shop.setGenre(request.getGenre());
		shop.setShopName(request.getShopName());
		shop.setStation(request.getStation());
		shop.setUrl(request.getUrl());
		shop.setMemo(request.getMemo());

		return shopRepository.saveAndFlush(shop);
	}

	public Shop visited(Integer id) {
		LocalDateTime now = LocalDateTime.now();

		Shop shop = getShop(id);
		shop.setVisitedAt(now);

		return shopRepository.saveAndFlush(shop);
	}

	public void delete(Integer id) {
		shopRepository.delete(id);
	}
}
