package com.uchitate.core.service;

import com.uchitate.core.entity.Shop;
import com.uchitate.core.repository.ShopRepository;
import com.uchitate.web.support.Genre;
import com.uchitate.web.support.ShopCreateRequest;
import com.uchitate.web.support.ShopEditRequest;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

	public class GetAllShops {
		private List<Shop> generateShops() {
			return Arrays.asList(new Shop(), new Shop(), new Shop());
		}

		@Test
		public void success() {
			List<Shop> shops = generateShops();
			Mockito.when(shopRepository.findAll()).thenReturn(shops);

			List<Shop> savedShops = shopService.getAllShops();
			Assertions.assertThat(savedShops)
					.isEqualTo(shops);
		}
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

	public class Edit {
		private ShopEditRequest generateShopEditRequest(
				Genre genre,
				String shopName,
				String station,
				String url,
				String memo) {
			ShopEditRequest request = new ShopEditRequest(genre, shopName, station, url, memo);
			return request;
		}

		private Shop generateShop(int id, Genre genre, String shopName, String station, String url, String memo) {
			Shop shop = new Shop();
			shop.setId(id);
			shop.setGenre(genre);
			shop.setShopName(shopName);
			shop.setStation(station);
			shop.setUrl(url);
			shop.setMemo(memo);
			return shop;
		}

		@Test
		public void success() {
			ShopEditRequest request = generateShopEditRequest(
					Genre.JAPANESE,
					"定食屋１",
					"渋谷",
					"http://teisyoku-1.co.jp",
					"トンカツが有名");

			Objects.equals("test", "test");

			Shop shop = generateShop(1, Genre.ITALIAN, "イタリアン１", "代官山", "http://italian-1.co.jp", "ピザ");

			Mockito.when(shopRepository.findOne(Mockito.anyInt())).thenReturn(shop);

			shopService.edit(1, request);
			Mockito.verify(shopRepository).saveAndFlush(captor.capture());
			Shop savedShop = captor.getValue();
			Assertions.assertThat(savedShop)
					.extracting(Shop::getGenre, Shop::getShopName, Shop::getStation, Shop::getUrl, Shop::getMemo)
					.contains(Genre.JAPANESE, "定食屋１", "渋谷", "http://teisyoku-1.co.jp", "トンカツが有名");
		}
	}

	public class Visited {
		private Shop generateShop(int id) {
			Shop shop = new Shop();
			shop.setId(id);
			return shop;
		}

		@Test
		public void success() {
			Shop shop = generateShop(1);

			Mockito.when(shopRepository.findOne(Mockito.anyInt())).thenReturn(shop);
			shopService.visited(1);
		}
	}

	public class Delete {
		@Test
		public void success() {
			shopService.delete(1);
		}
	}
}