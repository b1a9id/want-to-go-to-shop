package com.uchitate.core.model;

import com.uchitate.web.support.Genre;
import com.uchitate.web.support.ShopEditRequest;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopEditForm implements Serializable {

	private Genre genre;

	@NonNull
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
