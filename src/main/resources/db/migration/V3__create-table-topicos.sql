CREATE TABLE topicos (
	id		BIGINT 		NOT NULL AUTO_INCREMENT,
	titulo		VARCHAR(100)	NOT NULL,
	mensaje		VARCHAR(8000)	NOT NULL,
	creado		DATETIME	NOT NULL,
	estado		VARCHAR(30)	NOT NULL,
	usuario_fk	BIGINT		NOT NULL,
	curso_fk	BIGINT		NOT NULL,
	
	PRIMARY KEY(id),
	FOREIGN KEY (usuario_fk) REFERENCES usuarios(id),
	FOREIGN KEY (curso_fk)	REFERENCES cursos(id)
);