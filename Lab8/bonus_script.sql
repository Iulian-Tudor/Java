CREATE TABLE `albums` (
                          `id` INT(11) NOT NULL AUTO_INCREMENT,
                          `number` INT(11) NOT NULL,
                          `year` INT(11) NOT NULL,
                          `title` VARCHAR(255) NOT NULL,
                          `artist_id` INT(11) NOT NULL,
                          `genre_id` INT(11) NOT NULL,
                          PRIMARY KEY (`id`)
);

CREATE TABLE `artists` (
                           `id` INT(11) NOT NULL AUTO_INCREMENT,
                           `name` VARCHAR(255) NOT NULL,
                           PRIMARY KEY (`id`)
);

CREATE TABLE `genres` (
                          `id` INT(11) NOT NULL AUTO_INCREMENT,
                          `name` VARCHAR(255) NOT NULL,
                          PRIMARY KEY (`id`)
);

CREATE TABLE `album_genres` (
                                `album_id` INT(11) NOT NULL,
                                `genre_id` INT(11) NOT NULL,
                                PRIMARY KEY (`album_id`, `genre_id`),
                                FOREIGN KEY (`album_id`) REFERENCES `albums` (`id`) ON DELETE CASCADE,
                                FOREIGN KEY (`genre_id`) REFERENCES `genres` (`id`) ON DELETE CASCADE
);