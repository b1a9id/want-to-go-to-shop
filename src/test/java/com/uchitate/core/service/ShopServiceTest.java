package com.uchitate.core.service;

import com.uchitate.core.entity.Shop;
import com.uchitate.core.repository.ShopRepository;
import com.uchitate.web.support.Genre;
import com.uchitate.web.support.ShopCreateRequest;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

@RunWith(HierarchicalContextRunner.class)
public class ShopServiceTest {

	@InjectMocks
	private ShopService shopService;

	@Mock
	private ShopRepository shopRepository;

	@Captor
	private ArgumentCaptor<Shop> captor;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	public class Create {

		private ShopCreateRequest generateShopCreateRequest(
				Genre genre,
				String shopName,
				String station,
				String url,
				String memo) {
			ShopCreateRequest request = new ShopCreateRequest(genre, shopName, station, url, memo);
			return request;
		}

		@Test
		public void success() {
			ShopCreateRequest request = generateShopCreateRequest(
					Genre.JAPANESE,
					"定食屋１",
					"渋谷",
					"http://teisyoku-1.co.jp",
					"トンカツが有名");

			shopService.create(request);
			Mockito.verify(shopRepository).saveAndFlush(captor.capture());
			Shop savedShop = captor.getValue();
			Assertions.assertThat(savedShop)
					.extracting(Shop::getGenre, Shop::getShopName, Shop::getStation, Shop::getUrl, Shop::getMemo)
					.contains(Genre.JAPANESE, "定食屋１", "渋谷", "http://teisyoku-1.co.jp", "トンカツが有名");
		}
	}
}