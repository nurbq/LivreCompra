CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL
);

CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       user_id INTEGER REFERENCES users(id)
);

CREATE TABLE orders (
                        id SERIAL PRIMARY KEY,
                        user_id INTEGER REFERENCES users(id),
                        order_time TIMESTAMP
);


CREATE TABLE ticket (
                        id SERIAL PRIMARY KEY,
                        movie_id INTEGER REFERENCES movie(id),
                        order_id INTEGER REFERENCES orders(id),
                        theater_id INTEGER REFERENCES movie_theater(id),
                        spot VARCHAR(255),
                        isPurchased BOOLEAN
);


CREATE TABLE movie_theater (
                               id SERIAL PRIMARY KEY,
                               name VARCHAR(255),
                               address VARCHAR(255)
);

CREATE TABLE movie_detail (
                              id SERIAL PRIMARY KEY,
                              duration INTEGER,
                              premiere_date DATE,
                              director VARCHAR(255)
);

CREATE TABLE movie (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255),
                       description VARCHAR(255),
                       genre VARCHAR(255),
                       details_id INTEGER REFERENCES movie_detail(id)
);


CREATE TABLE schedule (
                          id SERIAL PRIMARY KEY,
                          movie_id INTEGER REFERENCES movie(id),
                          movie_theater_id INTEGER REFERENCES movie_theater(id),
                          start_time TIMESTAMP,
                          finish_time TIMESTAMP
);






CREATE TABLE movie_review (
                              id SERIAL PRIMARY KEY,
                              user_id INTEGER REFERENCES users(id),
                              movie_id INTEGER REFERENCES movie(id),
                              review_text TEXT
);