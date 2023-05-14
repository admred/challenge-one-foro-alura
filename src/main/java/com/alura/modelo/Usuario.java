package com.alura.modelo;

import com.alura.dto.usuario.DatosActualizarUsuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="Usuario")
@Table(name="usuarios")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String email;
	private String contrasena;

	public Usuario actualizarUsuario(DatosActualizarUsuario datosActualizarUsuario) {
		if(datosActualizarUsuario.nombre() != null) {
			this.nombre=datosActualizarUsuario.nombre();
		}
		if(datosActualizarUsuario.email() != null) {
			this.email=datosActualizarUsuario.email();
		}
		if(datosActualizarUsuario.contrasena() != null) {
			this.email=datosActualizarUsuario.contrasena();
		}
		return this;
	}
}
