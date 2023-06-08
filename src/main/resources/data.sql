INSERT INTO Users (id, username, password)
VALUES (1, 'user1', 'password1'),
       (2, 'user2', 'password2');

-- Insert test data for the Roles table
INSERT INTO Roles (id, name, user_id)
VALUES (1, 'role1', 1),
       (2, 'role2', 2);

-- Insert test data for the Orders table
INSERT INTO Orders (id, order_time, user_id)
VALUES (1, '2023-06-01 10:00:00', 1),
       (2, '2023-06-02 14:30:00', 2);

-- Insert test data for the Tickets table
INSERT INTO ticket (id, order_id, schedule_id, seat_number, ispurchased)
VALUES (1, 1, 1, 4, true),
       (2, 1, 2, 5, true),
       (3, 2, 1, 5, false);

-- Insert test data for the Schedules table
INSERT INTO schedule (id, movie_id, movie_theater_id, start_time, finish_time)
VALUES (1, 1, 1, '2023-06-01 12:00:00', '2023-06-01 14:00:00'),
       (2, 2, 1, '2023-06-02 15:30:00', '2023-06-02 17:30:00');

-- Insert test data for the Movies table
INSERT INTO movie (id, name, genre)
VALUES (1, 'Movie 1', 'Genre 1'),
       (2, 'Movie 2', 'Genre 2');

-- Insert test data for the MovieTheaters table
INSERT INTO movie_theater (id, name, address)
VALUES (1, 'Theater 1', 'Address 1'),
       (2, 'Theater 2', 'Address 2');
