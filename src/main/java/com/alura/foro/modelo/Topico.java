package com.alura.foro.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.alura.foro.dto.topico.DatosActualizarTopico;
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
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name="topicos")
@Entity(name="Topico")
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Topico {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	private String mensaje;
		
	@Column(name="creado")
	private LocalDateTime fechaCreacion = LocalDateTime.now();
	
	@Setter
	@Getter
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


	public Topico(String titulo,String mensaje,Usuario autor,Curso curso) {
		this.titulo=titulo;
		this.mensaje=mensaje;
		this.autor=autor;
		this.curso=curso;
	}
	
	public Topico actualizarTopico(DatosActualizarTopico datosActualizarTopico) {
		if(datosActualizarTopico.titulo() != null) {
			this.titulo=datosActualizarTopico.titulo();
		}
		if(datosActualizarTopico.mensaje() != null) {
			this.mensaje=datosActualizarTopico.mensaje();
		}
		return this;
	}
}
