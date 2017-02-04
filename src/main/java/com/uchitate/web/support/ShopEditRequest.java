package com.uchitate.web.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopEditRequest implements Serializable {

	private Genre genre;

	private String shopName;

	private String station;

	private String url;

	private String memo;
}
