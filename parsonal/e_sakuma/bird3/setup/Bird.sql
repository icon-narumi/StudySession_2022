--野鳥テーブル
CREATE TABLE t_bird 
(species text ,
 ordo integer not null,
 family integer not Null,
 volume integer not null,
 food integer not Null,
 PRIMARY KEY(species));

comment on table public.t_bird is '野鳥DB';
comment on column public.t_bird.species is '種';
comment on column public.t_bird.ordo is '目';
comment on column public.t_bird.family is '科';
comment on column public.t_bird.volume is '全長';
comment on column public.t_bird.food is '主食';

--目名テーブル
CREATE TABLE m_ordo 
(id integer unique,
 ordoName text not null,
 PRIMARY KEY(ordoName));

comment on table public.m_ordo is '目';
comment on column public.m_ordo.id is '目id';
comment on column public.m_ordo.ordoName is '目名';

--科名テーブル
CREATE TABLE m_family
(id integer unique,
 familyName text not null,
 PRIMARY KEY(familyName));

comment on table public.m_family is '科';
comment on column public.m_family.id is '科id';
comment on column public.m_family.familyName is '科名';

--食料テーブル
CREATE TABLE m_food
(id integer unique,
 foodName text not null,
 PRIMARY KEY(foodName));

comment on table public.m_food is '食料';
comment on column public.m_food.id is '食料id';
comment on column public.m_food.foodName is '食料名';

--鳥情報入力
insert into t_bird (species,ordo,family,volume,food) values('ヤマガラ',1,1,14,2);
insert into t_bird (species,ordo,family,volume,food) values('キビタキ',1,2,13,2);
insert into t_bird (species,ordo,family,volume,food) values('オジロワシ',3,3,85,3);
insert into t_bird (species,ordo,family,volume,food) values('ハヤブサ',4,4,41,4);
insert into t_bird (species,ordo,family,volume,food) values('シマエナガ',5,5,14,5);
insert into t_bird (species,ordo,family,volume,food) values('クマゲラ',2,6,50,2);
insert into t_bird (species,ordo,family,volume,food) values('ホシガラス',1,7,35,1);
insert into t_bird (species,ordo,family,volume,food) values('カケス',1,7,34,6);
insert into t_bird (species,ordo,family,volume,food) values('ウミネコ',6,8,46,7);
insert into t_bird (species,ordo,family,volume,food) values('イワツバメ',1,9,15,2);
insert into t_bird (species,ordo,family,volume,food) values('ゴイサギ',7,10,61,7);
insert into t_bird (species,ordo,family,volume,food) values('コウテイペンギン',8,11,120,3);
insert into t_bird (species,ordo,family,volume,food) values('ヒレンジャク',1,12,18,6);

--目マスター入力
insert into m_ordo (id,ordoName) values(1,'スズメ');
insert into m_ordo (id,ordoName) values(2,'キツツキ');
insert into m_ordo (id,ordoName) values(3,'タカ');
insert into m_ordo (id,ordoName) values(4,'ハヤブサ');
insert into m_ordo (id,ordoName) values(5,'エナガ');
insert into m_ordo (id,ordoName) values(6,'チドリ');
insert into m_ordo (id,ordoName) values(7,'ペリカン');
insert into m_ordo (id,ordoName) values(8,'ペンギン');

--科マスター入力
insert into m_family (id,familyName) values(1,'シジュウカラ');
insert into m_family (id,familyName) values(2,'ヒタキ');
insert into m_family (id,familyName) values(3,'タカ');
insert into m_family (id,familyName) values(4,'ハヤブサ');
insert into m_family (id,familyName) values(5,'エナガ');
insert into m_family (id,familyName) values(6,'キツツキ');
insert into m_family (id,familyName) values(7,'カラス');
insert into m_family (id,familyName) values(8,'カモメ');
insert into m_family (id,familyName) values(9,'ツバメ');
insert into m_family (id,familyName) values(10,'サギ');
insert into m_family (id,familyName) values(11,'ペンギン');
insert into m_family (id,familyName) values(12,'レンジャク');
insert into m_family (id,familyName) values(13,'スズメ');
insert into m_family (id,familyName) values(14,'ウミスズメ');

--食べ物マスター入力
insert into m_food (id,foodName) values(1,'種子');
insert into m_food (id,foodName) values(2,'昆虫');
insert into m_food (id,foodName) values(3,'魚類');
insert into m_food (id,foodName) values(4,'鳥類');
insert into m_food (id,foodName) values(5,'樹液');
insert into m_food (id,foodName) values(6,'果実');
insert into m_food (id,foodName) values(7,'両生類');
