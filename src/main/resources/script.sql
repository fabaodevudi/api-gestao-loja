create database db_gestao_loja;
use db_gestao_loja;
create table usuario_login (
    id_usuario_login int primary key auto_increment,
    email varchar(100),
    senha varchar(500),
    perfil varchar(20)
);
create table usuario (
    id_usuario int primary key auto_increment,
    nome varchar(100),
    telefone varchar(14),
    cpf varchar(11),
    id_usuario_login int ,
     constraint fk_usuario_usuario_login FOREIGN KEY (id_usuario_login) REFERENCES usuario_login (id_usuario_login)
);
create table categoria_loja (
    id_categoria int primary key auto_increment,
    nome varchar(100),
    descricao varchar(500)  
);

create table loja (
    id_loja int primary key auto_increment,
    nome varchar(100),
    descricao varchar(500),
    localizacao varchar(50),
    id_categoria int,
    id_usuario int,
    constraint fk_loja_categoria FOREIGN KEY (id_categoria) REFERENCES categoria_loja (id_categoria),
    constraint fk_loja_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
);

insert into categoria_loja (nome, descricao) values ('TI', 'Tecnologia da Informação');
insert into categoria_loja (nome, descricao) values ('Alimentos', 'Venda de alimentos');
insert into categoria_loja (nome, descricao) values ('Sexshop', 'Produtos eróticos');
