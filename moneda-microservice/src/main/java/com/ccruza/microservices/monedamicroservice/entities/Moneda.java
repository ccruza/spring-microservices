package com.ccruza.microservices.monedamicroservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Moneda", schema = "dbo")
public class Moneda {

	@Id
	@Column(name = "Codigo")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;

	@NotEmpty(message = "El campo Descripcion es obligatorio")
	@Column(name = "Descripcion", length = 3, nullable = false)
	private String descripcion;

	@NotEmpty(message = "El campo Estado es obligatorio")
	@Column(name = "Estado", length = 1, nullable = false)
	private String estado;

	@NotEmpty(message = "Se requiere el el Usuario")
	@Column(name = "Usuario", nullable = false)
	private String usuario;

	@Column(name = "Token", length = 500)
	private String token;

	@Column(name = "Port", length = 10)
	private String port;

	public Moneda() {
	}

	public Moneda(Long codigo, @NotEmpty(message = "El campo Descripcion es obligatorio") String descripcion,
			@NotEmpty(message = "El campo Estado es obligatorio") String estado,
			@NotEmpty(message = "Se requiere el el Usuario") String usuario, String token, String port) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.estado = estado;
		this.usuario = usuario;
		this.token = token;
		this.port = port;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "Moneda [codigo=" + codigo + ", descripcion=" + descripcion + ", estado=" + estado + ", usuario="
				+ usuario + ", token=" + token + ", port=" + port + "]";
	}

}
