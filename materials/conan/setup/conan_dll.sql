create table t_character(
name VARCHAR(20) not null
,sex_id INTEGER not null
,job_id INTEGER not null
,PRIMARY KEY(name
)
);
comment on column t_character.name is '氏名';
comment on column t_character.sex_id is '性別ID';
comment on column t_character.job_id is '職業ID';


create table m_sex(
sex_id INTEGER not null
,sex_name VARCHAR(20) not null
,PRIMARY KEY(sex_id
)
);
comment on column m_sex.sex_id is '性別ID';
comment on column m_sex.sex_name is '名称';

create table m_job(
job_id INTEGER not null
,job_name VARCHAR(20) not null
,PRIMARY KEY(job_id
)
);
comment on column m_job.job_id is '職業ID';
comment on column m_job.job_name is '名称';
