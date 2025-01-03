insert into commands (id, name, balance, commission_rate)
values ('1e5f6a77-ff8d-4d8c-ae5b-3a760bfb0899', 'Barcelona', 65000.00, 2.5),
       ('2b6a7d88-ee7d-4e9a-bc6c-4b871dfb0800', 'Liverpool', 70500.00, 1.8),
       ('3c7b8e99-dd6c-4faa-ab7d-5c982efc0901', 'Karpaty Lviv', 1000000.00, 0.7);

insert into players (id, command_id, first_name, last_name, age, experience)
values ('4d8c9f10-aa6c-4cbb-ac7d-6d0f2ffc0912', '1e5f6a77-ff8d-4d8c-ae5b-3a760bfb0899', 'Dani', 'Olmo', 25, 5),
       ('5e9dad21-bb7d-4dcc-bd8d-7e1f30fd0923', '2b6a7d88-ee7d-4e9a-bc6c-4b871dfb0800', 'Cody', 'Gakpo', 22, 3),
       ('6facbe32-cc8e-4eed-ce9d-8f2f41fe0934', '3c7b8e99-dd6c-4faa-ab7d-5c982efc0901', 'Yevhen', 'Pidlepenets', 30, 8);

insert into transfers (id, player_id, from_command_id, to_command_id, transfer_fee, sales_commission_fee, total_amount)
values ('6e35e93c-c0f2-4684-ac0c-cfb191a657e3', '4d8c9f10-aa6c-4cbb-ac7d-6d0f2ffc0912',
        '1e5f6a77-ff8d-4d8c-ae5b-3a760bfb0899', '2b6a7d88-ee7d-4e9a-bc6c-4b871dfb0800', 2000.00, 5.00, 2050.00),
       ('65e42676-ca32-4980-81fd-d25bd37329b5', '5e9dad21-bb7d-4dcc-bd8d-7e1f30fd0923',
        '2b6a7d88-ee7d-4e9a-bc6c-4b871dfb0800', '3c7b8e99-dd6c-4faa-ab7d-5c982efc0901', 1500.00, 7.00, 1530.00);
