package br.unibh.seguros.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

@Entity
@Table(name="tb_cidade")
public class Cidade implements Serializable{
	private static final long serialVersionUID = 1L;
	@Version
	@Column(columnDefinition="bigint NOT NULL DEFAULT 0")
	private Long version;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min=2,max=2)
	@Pattern(regexp="[A-Z]*",message="Deverá ter apenas letras Maiúsculas sem acento")
	@Column(columnDefinition="CHAR(2)", nullable=false)
	private String estado;
	
	@NotBlank
	@Size(max=100)
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá conter apenas Letras e Espaço")
	@Column(length=100, nullable=false)
	private String cidade;
	
//************************************ Construtores *************************** 
	public Cidade() {
		super();
	}
	public Cidade( String estado, String cidade) {
		super();
		this.estado = estado;
		this.cidade = cidade;
	}
	
//****************** Getters and setters ************************************
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}	

}
