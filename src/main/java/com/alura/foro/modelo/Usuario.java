package com.alura.foro.modelo;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.alura.foro.dto.usuario.DatosActualizarUsuario;
import com.alura.foro.dto.usuario.DatosRegistroUsuario;

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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Usuario implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String login;
	private String nombre;
	private String email;
	private String contrasena;

	public Usuario(DatosRegistroUsuario autor) {
		this.login=autor.login();
		this.nombre=autor.nombre();
		this.email=autor.email();
		this.contrasena=autor.contrasena();
	}

	public Usuario actualizarUsuario(DatosActualizarUsuario datosActualizarUsuario) {
		if(datosActualizarUsuario.login() != null) {
			this.nombre=datosActualizarUsuario.login();
		}
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
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}
	@Override
	public String getPassword() {
		return this.contrasena;
	}
	@Override
	public String getUsername() {
		return this.login;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public String toString() {
		return "login="+login+",nombre="+nombre+",email="+email+",contrasena="+contrasena;
	}

	public Long getId() {
		return this.id;
	}
	
}
