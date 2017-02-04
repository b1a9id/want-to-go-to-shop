package com.uchitate.core.model;

import com.uchitate.web.support.Genre;
import com.uchitate.web.support.ShopCreateRequest;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;

@Getter
@Setter
public class ShopCreateForm implements Serializable {

	private Genre genre;

	@NonNull
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
