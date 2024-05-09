-- Hotels
INSERT INTO hotels (name, country, city, wifi, stars, price)
VALUES ('Palmasol', 'Greece', 'Rhodes', true, 2, 5500),
       ('AluaSun Helios Beach', 'Bulgaria', 'Obzor', true, 3, 6100),
       ('Torel Avantgarde', 'Portugal', 'Porto', true, 5, 31800),
       ('Catalonia Gracia', 'Spain', 'Barcelona', true, 4, 15200),
       ('Riu Funana', 'Cap Verde', 'Santa Maria', true, 4, 42100);

-- Airlines
INSERT INTO airlines (name, price)
VALUES ('TUI', 2300),
       ('Norwegian Air', 2500),
       ('SAS', 5400),
       ('Vueling', 2400),
       ('Ryan Air', 2300);

-- Taxis
INSERT INTO transportation (type, price)
VALUES ('VIP Taxi', 1100),
       ('Uber', 600),
       ('Bolt', 550),
       ('Limousine', 2500),
       ('Bus', 65);

INSERT INTO orders (airline_id, hotel_id, transportation_id)
VALUES (1, 1, 1),
       (2, 2, 2);