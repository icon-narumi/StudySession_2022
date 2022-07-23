create table public."M_CHARACTER" (
  "CHARACTER_ID" serial not null
  , "CHARACTER_NAME" character varying(100) not null
  , "IMG_PATH" character varying(100) default '/img/0.png'
  , primary key (CHARACTER_ID)
)
/
alter table public."M_CHARACTER"  add constraint "M_CHARACTER_1" primary key ("CHARACTER_ID")
/
comment on table public."M_CHARACTER" is 'キャラクターマスタ'
/
comment on column public."M_CHARACTER"."CHARACTER_ID" is 'キャラクターID'
/
comment on column public."M_CHARACTER"."CHARACTER_NAME" is 'キャラクター名'
/
comment on column public."M_CHARACTER"."IMG_PATH" is 'キャラクター画像パス'
/



create table public."T_USER" (
  "USER_ID" serial not null
  , "USER_NAME" character varying(100) not null
  , "SAIBAI_COUNT" integer default 1
  , primary key (USER_NAME)
)
/
alter table public."T_USER"  add constraint "T_USER_1" primary key ("USER_NAME")
/
comment on table public."T_USER" is 'ユーザー'
/
comment on column public."T_USER"."USER_ID" is 'かいぬしID'
/
comment on column public."T_USER"."USER_NAME" is 'かいぬしの名前'
/
comment on column public."T_USER"."SAIBAI_COUNT" is 'さいばいくんの数'
/




create table public."T_SAIBAI_DAICHO" (
  "SAIBAI_DAICHO_ID" serial not null
  , "CHARACTER_ID" integer not null
  , "SAIBAI_NAME" character varying(100) not null
  , "USER_ID" integer not null
  , "LEVEL" character varying(100) default 0
  , primary key (SAIBAI_DAICHO_ID)
)
/
alter table public."T_SAIBAI_DAICHO"  add constraint "T_SAIBAI_DAICHO_1" primary key ("SAIBAI_DAICHO_ID")
/
comment on table public."T_SAIBAI_DAICHO" is 'さいばいくん台帳'
/
comment on column public."T_SAIBAI_DAICHO"."SAIBAI_DAICHO_ID" is 'さいばいくん台帳ID'
/
comment on column public."T_SAIBAI_DAICHO"."CHARACTER_ID" is 'キャラクターID'
/
comment on column public."T_SAIBAI_DAICHO"."SAIBAI_NAME" is 'さいばいくんの名前'
/
comment on column public."T_SAIBAI_DAICHO"."USER_ID" is 'かいぬしID'
/
comment on column public."T_SAIBAI_DAICHO"."LEVEL" is 'さいばいくんレベル'
/




create table public."T_ACTION_RRK" (
  "SAIBAI_DAICHO_ID" integer not null
  , "ACTION_YMD" character varying(100) not null
  , "ACTION_COUNT_1" integer default 0
  , "ACTION_COUNT_2" integer default 0
  , "ACTION_COUNT_3" integer default 0
  , "CHECKED_FLG" character varying(100) default 0
  , primary key (SAIBAI_DAICHO_ID, ACTION_YMD)
)
/
alter table public."T_ACTION_RRK"  add constraint "T_ACTION_RRK_1" primary key ("SAIBAI_DAICHO_ID","ACTION_YMD")
/
comment on table public."T_ACTION_RRK" is 'アクション履歴'
/
comment on column public."T_ACTION_RRK"."SAIBAI_DAICHO_ID" is 'さいばいくん台帳ID'
/
comment on column public."T_ACTION_RRK"."ACTION_YMD" is 'アクション実行年月日'
/
comment on column public."T_ACTION_RRK"."ACTION_COUNT_1" is 'ごはん実行回数'
/
comment on column public."T_ACTION_RRK"."ACTION_COUNT_2" is 'そうじ実行回数'
/
comment on column public."T_ACTION_RRK"."ACTION_COUNT_3" is 'あそび実行回数'
/
comment on column public."T_ACTION_RRK"."CHECKED_FLG" is 'チェック済みフラグ'
/




create table public."T_LOGIN_LOG" (
  "USER_ID" integer not null
  , "RRK_NO" character varying(100) not null
  , "LOGIN_TM" timestamp(6) without time zone default CURRENT_TIMESTAMP not null
  , "ZENKAI_LOGIN_TM" timestamp(6) without time zone not null
  , primary key (USER_ID, RRK_NO)
)
/
alter table public."T_LOGIN_LOG"  add constraint "T_LOGIN_LOG_1" primary key ("USER_ID","RRK_NO")
/
comment on table public."T_LOGIN_LOG" is 'ログインログ'
/
comment on column public."T_LOGIN_LOG"."USER_ID" is 'かいぬしID'
/
comment on column public."T_LOGIN_LOG"."RRK_NO" is '履歴番号'
/
comment on column public."T_LOGIN_LOG"."LOGIN_TM" is 'ログイン日時'
/
comment on column public."T_LOGIN_LOG"."ZENKAI_LOGIN_TM" is '前回ログイン日時'
/
