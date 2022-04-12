create table credit_card
(
    id     serial primary key,
    type   varchar(50) not null,
    number varchar(50) not null
);

create table person
(
    id             serial primary key,
    first_name     varchar(50) not null,
    last_name      varchar(50) not null,
    money          float       not null,
    credit_card_id serial      not null,
    constraint credit_card_id foreign key (credit_card_id) references credit_card (id)
);
