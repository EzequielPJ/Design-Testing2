drop database if exists `Sample`;
create database `Sample`;
drop user 'acme-user'@'%';
drop user 'acme-manager'@'%';
create user 'acme-user'@'%' identified by 'ACME-Us3r-P@ssw0rd';

create user 'acme-manager'@'%' identified by 'ACME-M@n@ger-6874';

grant select, insert, update, delete on `Sample`.* to 'acme-user'@'%';
grant select, insert, update, delete, create, drop, references, index, alter,
create temporary tables, lock tables, create view, create routine,
alter routine, execute, trigger, show view on `Sample`.* to 'acme-manager'@'%';