alter table usuarios add perfil varchar(100) not null;
update usuarios set perfil = 'ROLE_USER';