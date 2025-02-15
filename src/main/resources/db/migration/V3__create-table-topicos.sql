CREATE TABLE topicos (
	id		BIGINT 		NOT NULL AUTO_INCREMENT,
	titulo		VARCHAR(100)	NOT NULL UNIQUE,
	mensaje		VARCHAR(8000)	NOT NULL UNIQUE,
	creado		DATETIME	NOT NULL,
	estado		VARCHAR(30)	NOT NULL,
	autor_fk	BIGINT		NOT NULL,
	curso_fk	BIGINT		NOT NULL,
	
	PRIMARY KEY(id),
	FOREIGN KEY (autor_fk) REFERENCES usuarios(id),
	FOREIGN KEY (curso_fk)	REFERENCES cursos(id)
);