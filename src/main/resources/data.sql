-- Airlines
INSERT INTO flights (name, destination, departure, arrival, airline, price, packaged, seats)
VALUES ('A3 4321', 'GOT-RHO', '21:00', '01:30', 'Aegean', 3740, true, 41), -- Rhodes
       ('A3 4321', 'RHO-GOT', '02:00', '06:30', 'Aegean', 3740, true, 41), -- Rhodes Home
       ('W6 1736', 'GOT-BOJ', '13:05', '17:40', 'Wizz Air', 453, true, 41), -- Bulgaria
       ('W6 1736', 'BOJ-GOT', '15:05', '19:40', 'Wizz Air', 453, true, 41), -- Bulgaria Home
       ('D8 4900', 'GOT-OPO', '07:35', '15:15', 'Norwegian International', 636, true, 41), -- Portugal
       ('D8 4900', 'OPO-GOT', '10:35', '18:15', 'Norwegian International', 636, true, 41), -- Portugal Home
       ('D8 5505', 'GOT-BCN', '19:55', '22:55', 'Norwegian International', 340, true, 41), -- Barcelona
       ('D8 5505', 'BCN-GOT', '13:55', '16:55', 'Norwegian International', 340, true, 41), -- Barcelona Home
       ('SK 431', 'GOT-BVC', '16:00', '18:15', 'SAS', 1962, true, 41), -- Kap Verde
       ('SK 431', 'BVC-GOT', '12:00', '14:15', 'SAS', 1962, true, 41); -- Kap Verde Home

-- Hotels
INSERT INTO hotels (name, country, city, wifi, stars, price, packaged, rooms)
VALUES ('Palmasol', 'Greece', 'Rhodes', true, 2, 5500, true, 19),
       ('AluaSun Helios Beach', 'Bulgaria', 'Obzor', true, 3, 6100, true, 19),
       ('Torel Avantgarde', 'Portugal', 'Porto', true, 5, 31800, true, 19),
       ('Catalonia Gracia', 'Spain', 'Barcelona', true, 4, 15200, true, 19),
       ('Riu Funana', 'Cap Verde', 'Santa Maria', true, 4, 42100, true, 19);

-- Transportation
INSERT INTO transportation (type, price, packaged)
VALUES ('VIP Taxi', 1100, true),
       ('Uber', 600, true),
       ('Bolt', 550, true),
       ('Limousine', 2500, true),
       ('Bus', 65, true);

-- TravelPackages
INSERT INTO packages (flight_id, hotel_id, transportation_id, flight_home_id, transportation_home_id)
VALUES (1, 1, 1, 2, 1),
       (3, 2, 2, 4, 2),
       (5, 3, 3, 6, 3),
       (7, 4, 4, 8, 4),
       (9, 5, 5, 10, 5);

-- Users
INSERT INTO users (name, birthday, address, phone, email, hasbookings)
VALUES ('Fredrik', '1989-02-10', 'Prästgårdsängen 8', '0708388404', 'flundell89@gmail.com', false),
       ('Jonas', '1985-12-24', 'Runslingan 29', '0701111111', 'bloosucker85@gmail.com', false);

-- Bookings/Orders
INSERT INTO bookings (travelpackage_id, user_id, date, canceled)
VALUES (4, 1, NOW(), false),
       (3, 2, NOW(), false);

-- Events
INSERT INTO events (name, price, tickets)
VALUES ('Day Trip to Lindos', 30, 50), -- Rhodes
       ('Medieval City', 50, 50),
       ('Cake Lab', 20, 50), -- Bulgaria
       ('Action Aquapark', 40, 50),
       ('Oceanário de Lisboa', 30, 49), -- Portugal
       ('Ponte de Dom Luïs I', 20, 49),
       ('Park Güell', 50, 49), -- Barcelona
       ('La Sagrada Familia', 50, 49),
       ('Lakawon Island Day Tour', 115, 50), -- Kape Verde
       ('Tri-City', 70, 50);

-- Booked Events
INSERT INTO eventbookings (event_id, user_id, date, canceled)
VALUES (7, 1, NOW(), false),
       (8, 1, NOW(), false),
       (5, 2, NOW(), false),
       (6, 2, NOW(), false);