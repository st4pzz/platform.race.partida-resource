CREATE TABLE partida
(
    id_partida character varying(36) NOT NULL,
    time1 character varying(36) NOT NULL,
    time2 character varying(36) NOT NULL,
    id_jogador character varying(36) NOT NULL,
    CONSTRAINT partida_pkey PRIMARY KEY (id_partida)
);
