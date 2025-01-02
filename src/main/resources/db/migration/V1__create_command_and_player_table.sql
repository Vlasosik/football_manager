create table if not exists command
(
    id              uuid primary key,
    name            varchar(50) not null,
    balance         decimal(10, 2) default 0 not null check (balance >= 0),
    commission_rate decimal(5, 2) default 0 not null check (commission_rate between 0 and 10)
);

create table if not exists player
(
    id         uuid primary key,
    command_id uuid    not null,
    first_name varchar(50) not null,
    last_name  varchar(50) not null,
    age        smallint not null check (age >= 0 and age <= 120),
    experience smallint not null check (experience >= 0),
    foreign key (command_id) references command (id) on delete set null
);
