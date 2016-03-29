package br.unibh.escola.entidades;

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

@Entity
@Table(name="tb_vinculo")
public class Vinculo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="tipo_vinculo", length=30, nullable=false)
	private String tipoVinculo;
	
	@Column(length=120, nullable=false)
	private String empresa;
	
	@Column(name="data_desde", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dataDesde;
	
	@Column(name="data_ate")
	@Temporal(TemporalType.DATE)
	private Date dataAte;
	
	@Column(length=100, nullable=false)
	private String cargo;
	@Column(columnDefinition="DECIMAL(14,2)", nullable=false)
	private BigDecimal salario;
	
	@Column(name="pessoa_referencia",length=100, nullable=false)
	private String pessoaReferencia;
	
	@Column(name="telefone_referencia", columnDefinition="CHAR(14)", nullable=false)
	private String telefoneReferencia;
	
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
