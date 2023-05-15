create table cashback(
    id BIGSERIAL primary key not null,
    customer    varchar(255) not null,
    credit      numeric(15,4) not null,
    debit       numeric(15,4) not null,
    balance     numeric(15,4) not null,
    date_time   timestamp not null
);
