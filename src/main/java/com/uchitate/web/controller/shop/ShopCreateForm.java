package com.uchitate.web.controller.shop;

import com.uchitate.web.support.Genre;
import com.uchitate.web.support.ShopCreateRequest;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class ShopCreateForm implements Serializable {

	@NotNull
	private Genre genre;

	@NotNull
	private String shopName;

	private String station;

	@URL
	private String url;

	private String memo;

	public ShopCreateRequest toRequest() {
		ShopCreateRequest request = new ShopCreateRequest(
				getGenre(),
				getShopName(),
				getStation(),
				getUrl(),
				getMemo());
		return request;
	}
}
