package br.unibh.seguros.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="tb_endereco")
public class Endereco implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Size(max=30)
	@Column(length=30, nullable=false)
	private String tipo;
	
	@Column(columnDefinition="CHAR(9)", nullable=false)
	private String cep;
	
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Size(max=30)
	@Column(name="tipo_logradouro", length=30, nullable=false)
	private String tipoLogradouro;
	
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Size(max=150)
	@Column(length=150, nullable=false)
	private String logradouro;
	
	@NotBlank
	@Size(max=30)
	@Column(length=30, nullable=false)
	private String numero;
	
	@Size(max=100)
	@Column(length=100)
	private String complemento;
	
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá conter apenas Letras e Espaço")
	@Size(max=100)
	@Column(length=100, nullable=false)
	private String cidade;
	
	@NotBlank
	@Pattern(regexp="[A-Z]*",message="Deverá ter apenas letras Maiúsculas")
	@Size(max=2,min=2)
	@Column(columnDefinition="CHAR(2)", nullable=false)
	private String estado;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Proponente proponente; 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getTipoLogradouro() {
		return tipoLogradouro;
	}
	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
