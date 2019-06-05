package com.bolsadeideas.spring.boot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="clientes")
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="No puede estar vacio - dperez")
	@Size(min=4,max=12,message="el tamaño debe estar entre 4 y 12 caracteres - dperez")
	@Column(name="nombre", nullable=false)
	private String nombre; 
	
	@NotEmpty(message="No puede estar vacio - dperez")
	@Column(name="apellido")
	private String apellido;
	
	@NotEmpty(message="No puede estar vacio - dperez")
	@Email(message="no es una direccion de correo bien formada - dperez")
	@Column(name="email",nullable=false, unique=true)
	private String email; 
	
	@NotNull(message="No puede estar vacio - dperez")
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt; 
	
	private String foto;
	/*1 cliente tiene 1 region, 1 region tiene muchos clientes =>  clientes(*) --> region (1) */
	
	@NotNull(message="la región no puede ser vacia")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="region_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Region region;
	
	// mappedBy=cliente,de la tabla factura. cascare=ALL, cuando eliminamos un cliente, eliminara toda la informacion que trae consigo (info,detalle,factura) 
//	@JsonIgnoreProperties({"cliente","hibernateLazyInitializer","handler"})
	@JsonIgnoreProperties(value={"cliente","hibernateLazyInitializer","handler"},allowSetters=true)
	@OneToMany(fetch=FetchType.LAZY,mappedBy="cliente",cascade=CascadeType.ALL)
	private List<Factura> facturas;
	
//	@PrePersist // genera de manera automatica
//	public void prePersis() {
//		createAt=new Date();
//	}
	


	public Cliente() {
		this.facturas=new ArrayList<>();
	}

	public Cliente(Long id,
		@NotEmpty(message = "No puede estar vacio - dperez") @Size(min = 4, max = 12, message = "el tamaño debe estar entre 4 y 12 caracteres - dperez") String nombre,
		@NotEmpty(message = "No puede estar vacio - dperez") String apellido,
		@NotEmpty(message = "No puede estar vacio - dperez") @Email(message = "no es una direccion de correo bien formada - dperez") String email,
		@NotNull(message = "No puede estar vacio - dperez") Date createAt, String foto,
		@NotNull(message = "la región no puede ser vacia") Region region) {
	super();
	this.id = id;
	this.nombre = nombre;
	this.apellido = apellido;
	this.email = email;
	this.createAt = createAt;
	this.foto = foto;
	this.region = region;
}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}



	
	
}
