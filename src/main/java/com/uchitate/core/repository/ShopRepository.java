package com.uchitate.core.repository;

import com.uchitate.core.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends ShopRepositoryCustom, JpaRepository<Shop, Integer> {

}
