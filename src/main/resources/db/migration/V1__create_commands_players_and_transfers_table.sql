create table if not exists commands
(
    id              uuid primary key,
    name            varchar(50) unique       not null,
    balance         decimal(10, 2) default 0 not null check (balance >= 0),
    commission_rate decimal(5, 2)  default 0 not null check (commission_rate between 0 and 10)
);

create table if not exists players
(
    id         uuid primary key,
    command_id uuid        not null,
    first_name varchar(50) not null,
    last_name  varchar(50) not null,
    age        smallint    not null check (age >= 0 and age <= 120),
    experience smallint    not null check (experience >= 0),
    foreign key (command_id) references commands (id) on delete set null on update cascade
);

create table if not exists transfers
(
    id                   uuid primary key,
    player_id            uuid                                     not null,
    from_command_id      uuid                                     not null,
    to_command_id        uuid                                     not null,
    transfer_fee         decimal(10, 2) default 0                 not null check (transfer_fee >= 0),
    sales_commission_fee decimal(5, 2)  default 0                 not null check (sales_commission_fee between 0 and 10),
    total_amount         decimal(10, 2) default 0                 not null check (total_amount >= 0),
    transfer_date        timestamp      default current_timestamp not null,
    foreign key (player_id) references players (id) on delete set null on update cascade,
    foreign key (from_command_id) references commands (id) on delete set null on update cascade,
    foreign key (to_command_id) references commands (id) on delete set null on update cascade
);
