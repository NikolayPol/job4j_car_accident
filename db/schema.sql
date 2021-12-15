CREATE TABLE IF NOT EXISTS public.accident
(
    id integer NOT NULL DEFAULT nextval('accident_id_seq'::regclass),
    name text COLLATE pg_catalog."default",
    text text COLLATE pg_catalog."default",
    address text COLLATE pg_catalog."default",
    type_id integer,
    CONSTRAINT accident_pkey PRIMARY KEY (id),
    CONSTRAINT accident_id_key UNIQUE (id),
    CONSTRAINT type_fkey FOREIGN KEY (type_id)
        REFERENCES public.type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.accident_rule
(
    accident_id integer,
    rule_id integer
);

CREATE TABLE IF NOT EXISTS public.rule
(
    id integer NOT NULL,
    name text COLLATE pg_catalog."default",
    CONSTRAINT rule_pkey PRIMARY KEY (id),
    CONSTRAINT rule_id_key UNIQUE (id)
);

CREATE TABLE IF NOT EXISTS public.type
(
    id integer NOT NULL,
    name text COLLATE pg_catalog."default",
    CONSTRAINT accident_type_pkey PRIMARY KEY (id)
);
