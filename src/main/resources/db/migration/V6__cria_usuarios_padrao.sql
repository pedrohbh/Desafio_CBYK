INSERT INTO public.users
(username, "password", enabled)
select 'user', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', true
WHERE
    NOT EXISTS (
        SELECT username FROM public.users WHERE username='user'
    );

INSERT INTO public.users
(username, "password", enabled)
select 'admin', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', true
WHERE
    NOT EXISTS (
        SELECT username FROM public.users WHERE username='admin'
    );


INSERT INTO public.authorities
(username, authority)
select 'user', 'ROLE_USER'
where
    not exists (
        select username from public.authorities where username='user');

INSERT INTO public.authorities
(username, authority)
select 'admin', 'ROLE_USER'
where
    not exists (
        select username from public.authorities where username='admin');


INSERT INTO public.authorities
(username, authority)
select 'admin', 'ROLE_ADMIN'
where
    not exists (
        select username from public.authorities where username='admin' and authority='ROLE_ADMIN');
