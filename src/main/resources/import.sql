INSERT INTO wallet (id, total) VALUES ('b5a9a671-91da-4149-bac6-b508c51c040b', 1000000);
INSERT INTO wallet (id, total) VALUES ('23607612-1a50-4225-9bfe-aa69fc77019b', 500000);
INSERT INTO wallet (id, total) VALUES ('92082430-1e33-44d5-8c93-7e2401d2ded2', 10000);
INSERT INTO wallet (id, total) VALUES ('57d8adb1-8882-4ff3-9e1b-7b4baa64a09b', 50000);

INSERT INTO club (id, commission, name, wallet_id) VALUES ('15e3578f-d26a-4b0a-b741-b19ded0e318e', 8, 'Liverpool', 'b5a9a671-91da-4149-bac6-b508c51c040b');
INSERT INTO club (id, commission, name, wallet_id) VALUES ('bc2f785b-8dc2-4ad5-b780-fc664589a7a4', 6, 'Real Madrid', '23607612-1a50-4225-9bfe-aa69fc77019b');
INSERT INTO club (id, commission, name, wallet_id) VALUES ('e92a6663-df93-49e5-8302-aa27ba0538fc', 2, 'Manchester City', '92082430-1e33-44d5-8c93-7e2401d2ded2');
INSERT INTO club (id, commission, name, wallet_id) VALUES ('91368aee-8c46-4781-91a6-8052b6486761', 4, 'PSG', '57d8adb1-8882-4ff3-9e1b-7b4baa64a09b');

INSERT INTO player(id, name, surname,age,months_experience, club_id) VALUES ('e669c9b9-2cf2-4a0a-97de-52ad07ae5ffb', 'Mohamed', 'Salah', 25, 84, '15e3578f-d26a-4b0a-b741-b19ded0e318e');
INSERT INTO player(id, name, surname,age,months_experience, club_id) VALUES ('d6c893df-cfcf-48c6-9a03-0ccd4e0c38a1', 'Sadio', 'Mane', 26, 78, '15e3578f-d26a-4b0a-b741-b19ded0e318e');
INSERT INTO player(id, name, surname,age,months_experience, club_id) VALUES ('c0363a3e-c22c-403f-89f9-204e8bf8baff', 'Karim', 'Benzema', 40, 203, 'bc2f785b-8dc2-4ad5-b780-fc664589a7a4');
INSERT INTO player(id, name, surname,age,months_experience, club_id) VALUES ('02093c76-b612-4260-965d-83fc2c507daf', 'Vinicius', 'Junior', 22, 24, 'bc2f785b-8dc2-4ad5-b780-fc664589a7a4');
INSERT INTO player(id, name, surname,age,months_experience, club_id) VALUES ('eb3b43c3-acf0-4adf-a6ff-037cb6541b28', 'Alexander', 'Zinchenko', 24, 42, 'e92a6663-df93-49e5-8302-aa27ba0538fc');
INSERT INTO player(id, name, surname,age,months_experience, club_id) VALUES ('9fb5ad98-edbe-43fc-b46a-cb62a5d36ba8', 'Rahim', 'Sterling', 26, 56, 'e92a6663-df93-49e5-8302-aa27ba0538fc');
INSERT INTO player(id, name, surname,age,months_experience, club_id) VALUES ('8c549195-bbfd-40d4-9093-59deb8b1ec47', 'Kylian', 'Mbappe', 24, 46, '91368aee-8c46-4781-91a6-8052b6486761');
INSERT INTO player(id, name, surname,age,months_experience, club_id) VALUES ('30f8e6a9-eb4f-46a0-afe7-339f8c213f09', 'Neymar', 'Junior', 27, 67, '91368aee-8c46-4781-91a6-8052b6486761');

INSERT INTO club_players (club_id, players_id) VALUES ('15e3578f-d26a-4b0a-b741-b19ded0e318e', 'e669c9b9-2cf2-4a0a-97de-52ad07ae5ffb');
INSERT INTO club_players (club_id, players_id) VALUES ('15e3578f-d26a-4b0a-b741-b19ded0e318e', 'd6c893df-cfcf-48c6-9a03-0ccd4e0c38a1');
INSERT INTO club_players (club_id, players_id) VALUES ('bc2f785b-8dc2-4ad5-b780-fc664589a7a4', 'c0363a3e-c22c-403f-89f9-204e8bf8baff');
INSERT INTO club_players (club_id, players_id) VALUES ('bc2f785b-8dc2-4ad5-b780-fc664589a7a4', '02093c76-b612-4260-965d-83fc2c507daf');
INSERT INTO club_players (club_id, players_id) VALUES ('e92a6663-df93-49e5-8302-aa27ba0538fc', 'eb3b43c3-acf0-4adf-a6ff-037cb6541b28');
INSERT INTO club_players (club_id, players_id) VALUES ('e92a6663-df93-49e5-8302-aa27ba0538fc', '9fb5ad98-edbe-43fc-b46a-cb62a5d36ba8');
INSERT INTO club_players (club_id, players_id) VALUES ('91368aee-8c46-4781-91a6-8052b6486761', '8c549195-bbfd-40d4-9093-59deb8b1ec47');
INSERT INTO club_players (club_id, players_id) VALUES ('91368aee-8c46-4781-91a6-8052b6486761', '30f8e6a9-eb4f-46a0-afe7-339f8c213f09');

COMMIT;
