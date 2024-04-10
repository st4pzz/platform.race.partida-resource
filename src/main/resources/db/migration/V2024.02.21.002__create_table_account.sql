CREATE TABLE partida
(
    id_partida character varying(36) NOT NULL,
    time1 character varying(36) NOT NULL,
    time2 character varying(36) NOT NULL,
    tx_data character varying(256) NOT NULL,
    tx_local character varying(256) NOT NULL,
    tx_campeonato character varying(256) NOT NULL,
    CONSTRAINT partida_pkey PRIMARY KEY (id_partida)
);
