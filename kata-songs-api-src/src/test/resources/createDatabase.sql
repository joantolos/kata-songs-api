DROP TABLE IF EXISTS songs;

CREATE TABLE songs (
	name	VARCHAR(30) NOT NULL,
	artist	VARCHAR(30) NOT NULL
);

ALTER TABLE songs ADD CONSTRAINT p_songs PRIMARY KEY (name);

INSERT INTO songs (name, artist) VALUES('Yellow', 'Coldplay');
INSERT INTO songs (name, artist) VALUES('Master of puppets', 'Metallica');
INSERT INTO songs (name, artist) VALUES('Cocaine', 'Eric Clapton');