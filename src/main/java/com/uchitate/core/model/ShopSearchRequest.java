package com.uchitate.core.model;

import com.uchitate.web.support.Genre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopSearchRequest {

	private String keyword;

	private Genre genre;
}
