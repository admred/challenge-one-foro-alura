package com.alura.foro.modelo;

import java.time.LocalDateTime;

import com.alura.foro.dto.respuesta.DatosActualizarRespuesta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="respuestas")
@Entity(name="Repuesta")
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Respuesta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String mensaje;
	
	@ManyToOne
	@JoinColumn(name="topico_fk")
	private Topico topico;
	
	@Column(name="creado")
	private LocalDateTime fechaCreacion = LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name="autor_fk")
	private Usuario autor;
	
	private Boolean solucion = false;

	public Respuesta(String mensaje,Usuario autor,Topico topico) {
		this.mensaje=mensaje;
		this.topico=topico;
		this.autor=autor;
	}
	
	public Respuesta actualizarRespuesta(DatosActualizarRespuesta actualizarRespuesta ) {
		if(actualizarRespuesta.mensaje() != null) {
			this.mensaje=actualizarRespuesta.mensaje();
		}
		return this;
	}
	
}
