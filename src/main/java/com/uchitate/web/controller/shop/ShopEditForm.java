package com.uchitate.web.controller.shop;

import com.uchitate.web.support.Genre;
import com.uchitate.web.support.ShopEditRequest;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopEditForm implements Serializable {

	private Genre genre;

	@NotNull
	private String shopName;

	private String station;

	@URL
	private String url;

	private String memo;

	public ShopEditRequest toRequest() {
		ShopEditRequest request = new ShopEditRequest(
				getGenre(),
				getShopName(),
				getStation(),
				getUrl(),
				getMemo());
		return request;
	}
}
