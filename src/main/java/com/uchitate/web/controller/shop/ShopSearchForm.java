package com.uchitate.web.controller.shop;

import com.uchitate.core.model.ShopSearchRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopSearchForm implements Serializable {

	private String keyword;

	public ShopSearchRequest toRequest() {
		ShopSearchRequest request = new ShopSearchRequest();
		request.setKeyword(getKeyword());
		return request;
	}
}
