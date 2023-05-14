CREATE TABLE respuestas (
	id		BIGINT		NOT NULL AUTO_INCREMENT,
	mensaje		VARCHAR(2000)	NOT NULL,
	topico_fk	BIGINT		NOT NULL,
	creacion	BIGINT		NOT NULL,
	autor_fk	BIGINT		NOT NULL,
	solucion	TINYINT		NOT NULL,

	PRIMARY KEY (id),
	FOREIGN KEY (topico_fk) REFERENCES topicos(id),
	FOREIGN KEY (autor_fk) REFERENCES usuarios(id)
);