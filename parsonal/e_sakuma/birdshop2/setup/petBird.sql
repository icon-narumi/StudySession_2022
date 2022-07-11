

--DB作成

Microsoft Windows [Version 10.0.22000.675]
(c) Microsoft Corporation. All rights reserved.


C:\Users\icon>psql -h localhost -p 5432 -U postgres -d postgres		---postgresqlに入る
ユーザー postgres のパスワード:				--postgres
psql (14.2)
"help"でヘルプを表示します。

postgres=# \l		--dbの全件検索
                                             データベース一覧
   名前    |  所有者  | エンコーディング |      照合順序      | Ctype(変換演算子)  |     アクセス権限
-----------+----------+------------------+--------------------+--------------------+-----------------------
 postgres  | postgres | UTF8             | Japanese_Japan.932 | Japanese_Japan.932 |
 template0 | postgres | UTF8             | Japanese_Japan.932 | Japanese_Japan.932 | =c/postgres          +
           |          |                  |                    |                    | postgres=CTc/postgres
 template1 | postgres | UTF8             | Japanese_Japan.932 | Japanese_Japan.932 | =c/postgres          +
           |          |                  |                    |                    | postgres=CTc/postgres
 testdb    | postgres | UTF8             | Japanese_Japan.932 | Japanese_Japan.932 |
(4 行)

                      ^
postgres=# create database practice;		--新規DB作成[practice]
CREATE DATABASE
postgres=# exit		--退出



--ペットショップの鳥テーブル

CREATE TABLE t_petbird 
(id serial,
 species integer ,
 sex integer not null,
 color integer not Null,
 price integer not null,
 life integer not null,
 count integer not null,
 PRIMARY KEY(id));
 
comment on table public.t_petbird is '飼い鳥DB';
comment on column public.t_petbird.id is 'ID';
comment on column public.t_petbird.species is '種';
comment on column public.t_petbird.sex is '性別';
comment on column public.t_petbird.color is '色';
comment on column public.t_petbird.price is '金額';
comment on column public.t_petbird.life is '寿命';
comment on column public.t_petbird.count is '保有個体数';



insert into t_petbird (species,sex,price,color,life,count) values(1,1,4000,1,5,15);
insert into t_petbird (species,sex,price,color,life,count) values(1,2,10000,2,10,20);
insert into t_petbird (species,sex,price,color,life,count) values(1,1,15000,3,15,10);
insert into t_petbird (species,sex,price,color,life,count) values(2,1,16000,3,10,12);
insert into t_petbird (species,sex,price,color,life,count) values(2,2,20000,5,14,4);
insert into t_petbird (species,sex,price,color,life,count) values(2,1,25000,8,15,2);
insert into t_petbird (species,sex,price,color,life,count) values(3,1,400000,7,15,1);
insert into t_petbird (species,sex,price,color,life,count) values(4,1,2000,8,8,30);
insert into t_petbird (species,sex,price,color,life,count) values(4,2,3600,4,10,15);
insert into t_petbird (species,sex,price,color,life,count) values(4,1,6500,6,9,20);
insert into t_petbird (species,sex,price,color,life,count) values(5,1,1000,8,5,23);
insert into t_petbird (species,sex,price,color,life,count) values(5,2,3000,8,8,20);
insert into t_petbird (species,sex,price,color,life,count) values(6,1,15000,1,15,4);
insert into t_petbird (species,sex,price,color,life,count) values(6,2,30000,4,18,5);
insert into t_petbird (species,sex,price,color,life,count) values(6,1,25000,8,25,3);
insert into t_petbird (species,sex,price,color,life,count) values(7,1,32000,8,50,1);
insert into t_petbird (species,sex,price,color,life,count) values(7,2,33000,8,50,1);
insert into t_petbird (species,sex,price,color,life,count) values(8,1,15000,1,10,1);
insert into t_petbird (species,sex,price,color,life,count) values(8,2,10000,5,8,1);


--鳥の種類
CREATE TABLE m_species
(id integer unique,
 speciesname text not null,
 PRIMARY KEY(speciesname));

comment on table public.m_species is '種別';
comment on column public.m_species.id is 'id';
comment on column public.m_species.speciesname is '種別名';

insert into m_species (id,speciesname) values(1,'セキセインコ');
insert into m_species (id,speciesname) values(2,'コザクラインコ');
insert into m_species (id,speciesname) values(3,'九官鳥');
insert into m_species (id,speciesname) values(4,'文鳥');
insert into m_species (id,speciesname) values(5,'キンカチョウ');
insert into m_species (id,speciesname) values(6,'オカメインコ');
insert into m_species (id,speciesname) values(7,'ヨウム');
insert into m_species (id,speciesname) values(8,'カナリア');


--鳥の性別
CREATE TABLE m_sex
(id integer unique,
 sexname text not null,
 PRIMARY KEY(sexname));

comment on table public.m_sex is '性別';
comment on column public.m_sex.id is 'id';
comment on column public.m_sex.sexname is '性別名';

insert into m_sex (id,sexname) values(1,'オス');
insert into m_sex (id,sexname) values(2,'メス');


--鳥の色
CREATE TABLE m_color
(id integer unique,
 colorname text not null,
 PRIMARY KEY(colorname));

comment on table public.m_color is '色';
comment on column public.m_color.id is 'id';
comment on column public.m_color.colorname is '色名';

insert into m_color (id,colorname) values(1,'黄');
insert into m_color (id,colorname) values(2,'青');
insert into m_color (id,colorname) values(3,'緑');
insert into m_color (id,colorname) values(4,'白');
insert into m_color (id,colorname) values(5,'赤');
insert into m_color (id,colorname) values(6,'茶');
insert into m_color (id,colorname) values(7,'黒');
insert into m_color (id,colorname) values(8,'グレー');


--鳥カートDB
create table public.t_cartbird (
  id integer not null
  , count integer not null
  , primary key (id)
);
