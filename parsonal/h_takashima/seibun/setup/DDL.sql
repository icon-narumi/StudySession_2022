-- テーブルの作成
create table public.ingredient (
  category character varying(100) not null
  , name character varying(100) not null
  , color character varying(100)
  , calorie numeric
  , protein numeric
)
/



-- 主キーの作成
alter table public.ingredient  add constraint ingredient_pkey primary key (name)
/



-- コメントの作成
comment on table public.ingredient is '成分表'
/
comment on column public.ingredient.category is '商品カテゴリー'
/
comment on column public.ingredient.name is '名称'
/
comment on column public.ingredient.color is '色'
/
comment on column public.ingredient.calorie is '可食部100gあたりのカロリー'
/
comment on column public.ingredient.protein is '可食部100gあたりのタンパク質量'
/