CREATE TABLE artists (
                         id INT NOT NULL AUTO_INCREMENT,
                         name VARCHAR(255) NOT NULL,
                         PRIMARY KEY (id)
);

CREATE TABLE genres (
                        id INT NOT NULL AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        PRIMARY KEY (id)
);

CREATE TABLE albums (
                        id INT NOT NULL AUTO_INCREMENT,
                        release_year INT NOT NULL,
                        title VARCHAR(255) NOT NULL,
                        artist_id INT NOT NULL,
                        PRIMARY KEY (id),
                        FOREIGN KEY (artist_id) REFERENCES artists(id)
);

CREATE TABLE album_genres (
                              album_id INT NOT NULL,
                              genre_id INT NOT NULL,
                              PRIMARY KEY (album_id, genre_id),
                              FOREIGN KEY (album_id) REFERENCES albums(id),
                              FOREIGN KEY (genre_id) REFERENCES genres(id)
);
