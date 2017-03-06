package com.uchitate.core.entity;

import com.uchitate.web.support.Genre;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "shop")
@Indexed
public class Shop implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Field
	private Genre genre;

	@NotNull
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String shopName;

	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String station;

	private String url;

	private String memo;

	private LocalDateTime createdAt;

	private LocalDateTime visitedAt;
}
