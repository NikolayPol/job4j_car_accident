CREATE TABLE IF NOT EXISTS public.users
(
    username text COLLATE pg_catalog."default" NOT NULL,
    password text COLLATE pg_catalog."default" NOT NULL,
    enabled boolean DEFAULT true,
    CONSTRAINT users_pkey PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS public.authorities
(
    username text COLLATE pg_catalog."default" NOT NULL,
    authority text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT authorities_pkey PRIMARY KEY (username),
    CONSTRAINT users_fkey FOREIGN KEY (username)
        REFERENCES public.users (username) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);