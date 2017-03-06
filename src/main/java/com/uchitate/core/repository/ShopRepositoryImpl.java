package com.uchitate.core.repository;

import com.uchitate.core.entity.Shop;
import com.uchitate.core.model.ShopSearchRequest;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ShopRepositoryImpl implements ShopRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Shop> search(ShopSearchRequest request) {
		javax.persistence.Query jpaQuery = getQuery(request);
		List<Shop> result = jpaQuery.getResultList();

		return result;
	}

	private javax.persistence.Query getQuery(ShopSearchRequest request) {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		QueryBuilder qb = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(Shop.class)
				.get();

		BooleanJunction<BooleanJunction> junction = qb.bool();
		junction.must(qb.all().createQuery());

		// キーワード
		if (StringUtils.hasText(request.getKeyword())) {
			junction.must(qb.keyword().onFields("shopName", "station").matching(request.getKeyword()).createQuery());
		}

		// ジャンル
		if (request.getGenre() != null) {
			junction.must(qb.keyword().onField("genre").matching(request.getGenre()).createQuery());
		}

		Query searchQuery = junction.createQuery();

//		Query luceneQuery = qb
//				.keyword()
//				.onFields("shopName", "station")
//				.matching("shibuya")
//				.createQuery();

		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(searchQuery, Shop.class);
		return jpaQuery;
	}
}
