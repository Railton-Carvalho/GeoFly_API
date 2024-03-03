CREATE TABLE IF NOT EXISTS public.tb_state
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    idh real NOT NULL,
    pib real NOT NULL,
    acronym character varying(255) COLLATE pg_catalog."default",
    area double precision,
    capital character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    population integer NOT NULL,
    CONSTRAINT tb_state_pkey PRIMARY KEY (id)
)