CREATE TABLE IF NOT EXISTS public.s_type_sys_trans
(
    id_type_st SERIAL PRIMARY KEY,
    abbrev_st VARCHAR(3) NOT NULL UNIQUE,
    name VARCHAR(20) NOT NULL
);

INSERT INTO public.s_type_sys_trans (abbrev_st, name) VALUES ('C', 'CWDM');