package com.alura.foro.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.alura.foro.dto.topico.DatosActualizarTopico;
import com.alura.foro.dto.topico.DatosRegistroTopico;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Table(name="topicos")
@Entity(name="Topico")
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Topico {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	private String mensaje;
		
	@Column(name="creado")
	private LocalDateTime fechaCreacion = LocalDateTime.now();
	
	@Enumerated(EnumType.STRING)
	@Column(name="estado")
	private StatusTopico status = StatusTopico.NO_RESPONDIDO;
	
	@ManyToOne
	@JoinColumn(name="autor_fk")
	private Usuario autor;
	
	@ManyToOne
	@JoinColumn(name="curso_fk")
	private Curso curso;
	
	@OneToMany(mappedBy="topico",fetch=FetchType.LAZY)
	private List<Respuesta> respuestas = new ArrayList<>();


	public Topico(DatosRegistroTopico datosRegistroTopico) {
		this.titulo=datosRegistroTopico.titulo();
		this.mensaje=datosRegistroTopico.mensaje();
		this.curso=new Curso(datosRegistroTopico.curso());
		this.autor=new Usuario(datosRegistroTopico.autor());
		this.status=datosRegistroTopico.status();
	}
	
	public Topico actualizarTopico(DatosActualizarTopico datosActualizarTopico) {
		if(datosActualizarTopico.titulo() != null) {
			this.titulo=datosActualizarTopico.titulo();
		}
		if(datosActualizarTopico.mensaje() != null) {
			this.mensaje=datosActualizarTopico.mensaje();
		}
		if(datosActualizarTopico.autor() != null) {
			this.autor.actualizarUsuario(datosActualizarTopico.autor());
		}
		if(datosActualizarTopico.curso() != null) {
			this.curso.actualizarCurso(datosActualizarTopico.curso());
		}
		return this;
	}
}