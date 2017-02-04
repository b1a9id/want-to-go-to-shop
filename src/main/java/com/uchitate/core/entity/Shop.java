package com.uchitate.core.entity;

import com.uchitate.web.support.Genre;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "shop")
public class Shop implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private Genre genre;

	@NonNull
	private String shopName;

	private String station;

	private String url;

	private String memo;

	private LocalDateTime createdAt;

	private LocalDateTime visitedAt;
}
