package com.uchitate.core.repository;

import com.uchitate.core.entity.Shop;
import com.uchitate.core.model.ShopSearchRequest;

import java.util.List;

public interface ShopRepositoryCustom {
	List<Shop> search(ShopSearchRequest request);
}
