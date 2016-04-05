package br.unibh.seguros.entidades;

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
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="tb_vinculo")
public class Vinculo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Max(30)	
	@Column(name="tipo_vinculo", length=30, nullable=false)
	private String tipoVinculo;
	
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Max(120)
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
	@Max(100)
	@Column(length=100, nullable=false)
	private String cargo;
	
	@NotNull
	@DecimalMin("500.0")
	@DecimalMax("100000.00")
	@Column(columnDefinition="DECIMAL(14,2)", nullable=false)
	private BigDecimal salario;
	
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Max(100)
	@Column(name="pessoa_referencia",length=100, nullable=false)
	private String pessoaReferencia;
	
	@NotBlank
	@Pattern(regexp="\\(\\d{2}\\)\\d{5}-\\d{4}")
	@Column(name="telefone_referencia", columnDefinition="CHAR(14)", nullable=false)
	private String telefoneReferencia;
	
	@NotBlank
	@Email
	@Max(100)
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
	
}
