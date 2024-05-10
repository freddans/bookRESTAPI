-- Airlines
INSERT INTO flights (name, destination, departure, arrival, airline, price, booked)
VALUES ('A3 4321', 'GOT-RHO', '21:00', '01:30', 'Aegean', 3740, true), -- Rhodes
       ('W6 1736', 'GOT-BOJ', '13:05', '17:40', 'Wizz Air', 453, true), -- Bulgaria
       ('D8 4900', 'GOT-OPO', '07:35', '15:15', 'Norwegian International', 636, true), -- Portugal
       ('D8 5505', 'GOT-BCN', '19:55', '22:55', 'Norwegian International', 340, true), -- Barcelona
       ('SK 431', 'GOT-BVC', '16:00', '18:15', 'SAS', 1962, true); -- Kap Verde

-- Hotels
INSERT INTO hotels (name, country, city, wifi, stars, price, booked)
VALUES ('Palmasol', 'Greece', 'Rhodes', true, 2, 5500, true),
       ('AluaSun Helios Beach', 'Bulgaria', 'Obzor', true, 3, 6100, true),
       ('Torel Avantgarde', 'Portugal', 'Porto', true, 5, 31800, true),
       ('Catalonia Gracia', 'Spain', 'Barcelona', true, 4, 15200, true),
       ('Riu Funana', 'Cap Verde', 'Santa Maria', true, 4, 42100, true);

-- Transportation
INSERT INTO transportation (type, price, booked)
VALUES ('VIP Taxi', 1100, true),
       ('Uber', 600, true),
       ('Bolt', 550, true),
       ('Limousine', 2500, true),
       ('Bus', 65, true);

INSERT INTO orders (flight_id, hotel_id, transportation_id)
VALUES (1, 1, 1),
       (2, 2, 2),
       (3, 3, 3),
       (4, 4, 4),
       (5, 5, 5);