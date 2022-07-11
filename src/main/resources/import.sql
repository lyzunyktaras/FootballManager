INSERT INTO wallet (id, total) VALUES (1, 1000000);
INSERT INTO wallet (id, total) VALUES (2, 500000);
INSERT INTO wallet (id, total) VALUES (3, 10000);
INSERT INTO wallet (id, total) VALUES (4, 50000);

INSERT INTO club (id, commission, name, wallet_id) VALUES (1, 8, 'Liverpool', 1);
INSERT INTO club (id, commission, name, wallet_id) VALUES (2, 6, 'Real Madrid', 2);
INSERT INTO club (id, commission, name, wallet_id) VALUES (3, 2, 'Manchester City', 3);
INSERT INTO club (id, commission, name, wallet_id) VALUES (4, 4, 'PSG', 4);

INSERT INTO player(id, name, surname,age,months_experience, club_id) VALUES (1, 'Mohamed', 'Salah', 25, 84, 1);
INSERT INTO player(id, name, surname,age,months_experience, club_id) VALUES (2, 'Sadio', 'Mane', 26, 78, 1);
INSERT INTO player(id, name, surname,age,months_experience, club_id) VALUES (3, 'Karim', 'Benzema', 40, 203, 2);
INSERT INTO player(id, name, surname,age,months_experience, club_id) VALUES (4, 'Vinicius', 'Junior', 22, 24, 2);
INSERT INTO player(id, name, surname,age,months_experience, club_id) VALUES (5, 'Alexander', 'Zinchenko', 24, 42, 3);
INSERT INTO player(id, name, surname,age,months_experience, club_id) VALUES (6, 'Rahim', 'Sterling', 26, 56, 3);
INSERT INTO player(id, name, surname,age,months_experience, club_id) VALUES (7, 'Kylian', 'Mbappe', 24, 46, 4);
INSERT INTO player(id, name, surname,age,months_experience, club_id) VALUES (8, 'Neymar', 'Junior', 27, 67, 4);

INSERT INTO club_players (club_id, players_id) VALUES (1, 1);
INSERT INTO club_players (club_id, players_id) VALUES (1, 2);
INSERT INTO club_players (club_id, players_id) VALUES (2, 3);
INSERT INTO club_players (club_id, players_id) VALUES (2, 4);
INSERT INTO club_players (club_id, players_id) VALUES (3, 5);
INSERT INTO club_players (club_id, players_id) VALUES (3, 6);
INSERT INTO club_players (club_id, players_id) VALUES (4, 7);
INSERT INTO club_players (club_id, players_id) VALUES (4, 8);

COMMIT;
