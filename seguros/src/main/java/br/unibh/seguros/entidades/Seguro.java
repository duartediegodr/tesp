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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="tb_seguro")
@NamedQueries({
	@NamedQuery(name="Seguro.findByCodigo", 
			query="select o from Seguro o where o.codigoSusep like :codigoSusep")
})
public class Seguro implements Serializable{
	private static final long serialVersionUID = 1L;
	@Version
	@Column(columnDefinition="bigint NOT NULL DEFAULT 0")
	private Long version;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max=15)
	@Column (name="codigo_susep",columnDefinition="CHAR(15)", nullable=false,unique=true)
	private String codigoSusep;
	
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Size(max=30)
	@Column (name="tipo_segurado",length=30, nullable=false)
	private String tipoSegurado;
	
	@NotNull
	@DecimalMin("1000.0")
	@DecimalMax("10000000.00")
	@Column (name="valor_segurado",columnDefinition="DECIMAL(14,2)", nullable=false)
	private BigDecimal valorSegurado;

	@NotBlank
	@Pattern(regexp="[A-Z]*",message="Deverá ter apenas Letras Maiúsculas")
	@Size(max=1)
	@Column (columnDefinition="CHAR(1)", nullable=false)
	private String classe;
	
	@NotNull
	@Column(name="data_inicio_vigencia", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dataInicioVigencia;

	@NotNull
	@Column(name="data_termino_vigencia", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dataTerminoVigencia;
	
	@NotNull
	@Range(min=0, max=24, message="Valor deve estar entre 0 e 24")
	@Column (name="carencia_em_meses", nullable=false)
	private int carenciaEmMeses;

	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Size(max=30)
	@Column (name="situacao_atual",length=30, nullable=false)
	private String situacaoAtual;

	@NotNull
	@DecimalMin("100.0")
	@DecimalMax("100000.00")
	@Column (name="valor_premio",columnDefinition="DECIMAL(14,2)", nullable=false)
	private BigDecimal valorPremio;

	@NotNull
	@Range(min=1, max=31, message="Valor deve estar entre 1 e 31")
	@Column (name="dia_pagamento", nullable=false)
	private int diaPagamento;

	@NotBlank
	@Size(max=50)
	@Column (name="banco_pagamento",length=50, nullable=false)
	private String bancoPagamento;

	@NotBlank
	@Size(max=15)
	@Column (length=15, nullable=false)
	private String agencia;

	@NotBlank
	@Size(max=15)
	@Column (length=15, nullable=false)
	private String conta;
	
	@OneToOne(optional=false)
	@JoinColumn(name="id_proposta")
	private Proposta proposta;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Proponente proponente;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigoSusep() {
		return codigoSusep;
	}
	public void setCodigoSusep(String codigoSusep) {
		this.codigoSusep = codigoSusep;
	}
	public String getTipoSegurado() {
		return tipoSegurado;
	}
	public void setTipoSegurado(String tipoSegurado) {
		this.tipoSegurado = tipoSegurado;
	}
	public BigDecimal getValorSegurado() {
		return valorSegurado;
	}
	public void setValorSegurado(BigDecimal valorSegurado) {
		this.valorSegurado = valorSegurado;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public Date getDataInicioVigencia() {
		return dataInicioVigencia;
	}
	public void setDataInicioVigencia(Date dataInicioVigencia) {
		this.dataInicioVigencia = dataInicioVigencia;
	}
	public Date getDataTerminoVigencia() {
		return dataTerminoVigencia;
	}
	public void setDataTerminoVigencia(Date dataTerminoVigencia) {
		this.dataTerminoVigencia = dataTerminoVigencia;
	}
	public int getCarenciaEmMeses() {
		return carenciaEmMeses;
	}
	public void setCarenciaEmMeses(int carenciaEmMeses) {
		this.carenciaEmMeses = carenciaEmMeses;
	}
	public String getSituacaoAtual() {
		return situacaoAtual;
	}
	public void setSituacaoAtual(String situacaoAtual) {
		this.situacaoAtual = situacaoAtual;
	}
	public BigDecimal getValorPremio() {
		return valorPremio;
	}
	public void setValorPremio(BigDecimal valorPremio) {
		this.valorPremio = valorPremio;
	}
	public int getDiaPagamento() {
		return diaPagamento;
	}
	public void setDiaPagamento(int diaPagamento) {
		this.diaPagamento = diaPagamento;
	}
	public String getBancoPagamento() {
		return bancoPagamento;
	}
	public void setBancoPagamento(String bancoPagamento) {
		this.bancoPagamento = bancoPagamento;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	public Proponente getProponente() {
		return proponente;
	}
	public void setProponente(Proponente proponente) {
		this.proponente = proponente;
	}
	public Proposta getProposta() {
		return proposta;
	}
	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
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
		result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
		result = prime * result + ((bancoPagamento == null) ? 0 : bancoPagamento.hashCode());
		result = prime * result + carenciaEmMeses;
		result = prime * result + ((classe == null) ? 0 : classe.hashCode());
		result = prime * result + ((codigoSusep == null) ? 0 : codigoSusep.hashCode());
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + ((dataInicioVigencia == null) ? 0 : dataInicioVigencia.hashCode());
		result = prime * result + ((dataTerminoVigencia == null) ? 0 : dataTerminoVigencia.hashCode());
		result = prime * result + diaPagamento;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((proponente == null) ? 0 : proponente.hashCode());
		result = prime * result + ((proposta == null) ? 0 : proposta.hashCode());
		result = prime * result + ((situacaoAtual == null) ? 0 : situacaoAtual.hashCode());
		result = prime * result + ((tipoSegurado == null) ? 0 : tipoSegurado.hashCode());
		result = prime * result + ((valorPremio == null) ? 0 : valorPremio.hashCode());
		result = prime * result + ((valorSegurado == null) ? 0 : valorSegurado.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

}
