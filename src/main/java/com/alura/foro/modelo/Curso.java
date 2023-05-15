package com.alura.foro.modelo;

import com.alura.foro.dto.curso.DatosActualizarCurso;
import com.alura.foro.dto.curso.DatosRegistroCurso;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="cursos")
@Entity(name="Curso")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Curso {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String categoria;

	public Curso(DatosRegistroCurso datosRegistroCurso) {
		this.nombre=datosRegistroCurso.nombre();
		this.categoria=datosRegistroCurso.categoria();
	}
	
	public Curso actualizarCurso(DatosActualizarCurso datosActualizarCurso) {
		if(datosActualizarCurso.nombre() != null) {
			this.nombre=datosActualizarCurso.nombre();
		}
		if(datosActualizarCurso.categoria() != null) {
			this.categoria=datosActualizarCurso.categoria();
		}
		return this;
	}
}
