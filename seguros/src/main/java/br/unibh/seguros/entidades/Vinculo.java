package br.unibh.seguros.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="tb_vinculo")
public class Vinculo implements Serializable{
	private static final long serialVersionUID = 1L;
	@Version
	@Column(columnDefinition="bigint NOT NULL DEFAULT 0")
	private Long version;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Size(max=30)	
	@Column(name="tipo_vinculo", length=30, nullable=false)
	private String tipoVinculo;
	
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Size(max=120)
	@Column(length=120, nullable=false)
	private String empresa;
	
	@NotNull
	@Past
	@Column(name="data_desde", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dataDesde;
	
	@Past
	@Column(name="data_ate")
	@Temporal(TemporalType.DATE)
	private Date dataAte;
	
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Size(max=100)
	@Column(length=100, nullable=false)
	private String cargo;
	
	@NotNull
	@DecimalMin("500.0")
	@DecimalMax("100000.00")
	@Column(columnDefinition="DECIMAL(14,2)", nullable=false)
	private BigDecimal salario;
	
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Size(max=100)
	@Column(name="pessoa_referencia",length=100, nullable=false)
	private String pessoaReferencia;
	
	@NotBlank
	@Pattern(regexp="\\(\\d{2}\\)\\d{5}-\\d{4}")
	@Column(name="telefone_referencia", columnDefinition="CHAR(14)", nullable=false)
	private String telefoneReferencia;
	
	@NotBlank
	@Email
	@Size(max=100)
	@Column(name="email_referencia",length=100)
	private String emailReferencia;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Proponente proponente;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipoVinculo() {
		return tipoVinculo;
	}
	public void setTipoVinculo(String tipoVinculo) {
		this.tipoVinculo = tipoVinculo;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public Date getDataDesde() {
		return dataDesde;
	}
	public void setDataDesde(Date dataDesde) {
		this.dataDesde = dataDesde;
	}
	public Date getDataAte() {
		return dataAte;
	}
	public void setDataAte(Date dataAte) {
		this.dataAte = dataAte;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	public String getPessoaReferencia() {
		return pessoaReferencia;
	}
	public void setPessoaReferencia(String pessoaReferencia) {
		this.pessoaReferencia = pessoaReferencia;
	}
	public String getTelefoneReferencia() {
		return telefoneReferencia;
	}
	public void setTelefoneReferencia(String telefoneReferencia) {
		this.telefoneReferencia = telefoneReferencia;
	}
	public String getEmailReferencia() {
		return emailReferencia;
	}
	public void setEmailReferencia(String emailReferencia) {
		this.emailReferencia = emailReferencia;
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
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = prime * result + ((dataAte == null) ? 0 : dataAte.hashCode());
		result = prime * result + ((dataDesde == null) ? 0 : dataDesde.hashCode());
		result = prime * result + ((emailReferencia == null) ? 0 : emailReferencia.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pessoaReferencia == null) ? 0 : pessoaReferencia.hashCode());
		result = prime * result + ((proponente == null) ? 0 : proponente.hashCode());
		result = prime * result + ((salario == null) ? 0 : salario.hashCode());
		result = prime * result + ((telefoneReferencia == null) ? 0 : telefoneReferencia.hashCode());
		result = prime * result + ((tipoVinculo == null) ? 0 : tipoVinculo.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}
	
}
