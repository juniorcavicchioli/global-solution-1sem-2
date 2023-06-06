CREATE DATABASE ifeed;
USE ifeed;
/*
DROP TABLE IF EXISTS t_arrecadacao;
DROP TABLE IF EXISTS t_instituicoes;
DROP TABLE IF EXISTS t_usuario;
DROP TABLE IF EXISTS t_logs;


CREATE TABLE t_instituicoes (
    id_instituicao INT NOT NULL,
    nm_instituicao VARCHAR(30) NOT NULL,
    ds_descricao   VARCHAR(100) NOT NULL,
    ds_endereco    VARCHAR(80) NOT NULL,
    ds_contato     VARCHAR(30) NOT NULL,
    PRIMARY KEY (id_instituicao)
);

CREATE TABLE t_usuario (
    id_usuario  INT NOT NULL,
    nm_usuario  VARCHAR(15) NOT NULL,
    ds_email    VARCHAR(50) NOT NULL,
    ds_senha    VARCHAR(20) NOT NULL,
    ds_telefone VARCHAR(11) NOT NULL,
    PRIMARY KEY (id_usuario)
);

CREATE TABLE t_arrecadacao (
    id_arrecadacao INT NOT NULL,
    id_usuario     INT NOT NULL,
    id_instituicao INT NOT NULL,
    vl_arrecadacao DECIMAL(9, 2) NOT NULL,
    dt_arrecadacao DATE NOT NULL,
    tp_pagamento   VARCHAR(10) NOT NULL,
    PRIMARY KEY (id_arrecadacao),
    FOREIGN KEY (id_usuario) REFERENCES t_usuario (id_usuario),
    FOREIGN KEY (id_instituicao) REFERENCES t_instituicoes (id_instituicao)
);

CREATE TABLE t_logs (
    id_usuario    INT NOT NULL,
    dt_ocorrencia DATE NOT NULL,
    cd_erro       INT NOT NULL,
    ds_mensagem   VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_usuario),
    FOREIGN KEY (id_usuario) REFERENCES t_usuario (id_usuario)
);
