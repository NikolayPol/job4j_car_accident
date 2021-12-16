CREATE TABLE IF NOT EXISTS public.authorities
(
    id integer NOT NULL,
    authority text COLLATE pg_catalog."default" NOT NULL unique,
    CONSTRAINT authorities_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.users
(
    id integer NOT NULL,
    username text COLLATE pg_catalog."default" NOT NULL unique,
    password text COLLATE pg_catalog."default" NOT NULL,
    enabled boolean DEFAULT true,
    authority_id integer NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT authority_fkey FOREIGN KEY (authority_id)
        REFERENCES public.authorities (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);