create table shop (
  id  serial not null,
  genre varchar(255),
  shop_name varchar(255),
  station varchar(255),
  url varchar(255),
  memo varchar(255),
  created_at timestamp,
  visited_at timestamp,
  primary key (id)
);
