CREATE TABLE movies (
                        id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
                        title VARCHAR(255) UNIQUE NOT NULL,
                        release_date DATE NOT NULL,
                        poster_url VARCHAR(512),
                        overview VARCHAR(512),
                        genres VARCHAR(255),
                        rating DOUBLE,
                        runtime INT,
                        language VARCHAR(10) NOT NULL
);
