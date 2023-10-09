alter table pets add column ativo tinyint;
update pets set ativo = 1;
alter table pets modify ativo tinyint not null;