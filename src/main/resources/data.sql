-- Airlines
INSERT INTO flights (name, destination, departure, arrival, airline, price, booked, seats)
VALUES ('A3 4321', 'GOT-RHO', '21:00', '01:30', 'Aegean', 3740, true, 41), -- Rhodes
       ('W6 1736', 'GOT-BOJ', '13:05', '17:40', 'Wizz Air', 453, true, 41), -- Bulgaria
       ('D8 4900', 'GOT-OPO', '07:35', '15:15', 'Norwegian International', 636, true, 41), -- Portugal
       ('D8 5505', 'GOT-BCN', '19:55', '22:55', 'Norwegian International', 340, true, 41), -- Barcelona
       ('SK 431', 'GOT-BVC', '16:00', '18:15', 'SAS', 1962, true, 41); -- Kap Verde

-- Hotels
INSERT INTO hotels (name, country, city, wifi, stars, price, booked, rooms)
VALUES ('Palmasol', 'Greece', 'Rhodes', true, 2, 5500, true, 19),
       ('AluaSun Helios Beach', 'Bulgaria', 'Obzor', true, 3, 6100, true, 19),
       ('Torel Avantgarde', 'Portugal', 'Porto', true, 5, 31800, true, 19),
       ('Catalonia Gracia', 'Spain', 'Barcelona', true, 4, 15200, true, 19),
       ('Riu Funana', 'Cap Verde', 'Santa Maria', true, 4, 42100, true, 19);

-- Transportation
INSERT INTO transportation (type, price, booked)
VALUES ('VIP Taxi', 1100, true),
       ('Uber', 600, true),
       ('Bolt', 550, true),
       ('Limousine', 2500, true),
       ('Bus', 65, true);

-- Orders
INSERT INTO orders (flight_id, hotel_id, transportation_id, bookingdate)
VALUES (1, 1, 1, NOW()),
       (2, 2, 2, NOW()),
       (3, 3, 3, NOW()),
       (4, 4, 4, NOW()),
       (5, 5, 5, NOW());

-- Users
INSERT INTO users (name, birthday, address, phone, email, hasbookings)
VALUES ('Fredrik', '1989-02-10', 'Prästgårdsängen 8', '0708388404', 'flundell89@gmail.com', false),
       ('Jonas', '1985-12-24', 'Runslingan 29', '0701111111', 'bloosucker85@gmail.com', false);