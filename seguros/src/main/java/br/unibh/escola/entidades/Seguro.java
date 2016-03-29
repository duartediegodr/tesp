package br.unibh.escola.entidades;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_seguro")
public class Seguro {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column (name="codigo_susep",columnDefinition="CHAR(15)", nullable=false,unique=true)
	private String codigoSusep;
	
	@Column (name="tipo_segurado",length=30, nullable=false)
	private String tipoSegurado;
	
	@Column (name="valor_segurado",columnDefinition="DECIMAL(14,2)", nullable=false)
	private BigDecimal valorSegurado;

	@Column (columnDefinition="CHAR(1)", nullable=false)
	private String classe;
	
	@Column(name="data_inicio_vigencia", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dataInicioVigencia;

	@Column(name="data_termino_vigencia", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dataTerminoVigencia;
	
	@Column (name="carencia_em_meses", nullable=false)
	private int carenciaEmMeses;

	@Column (name="situacao_atual",length=30, nullable=false)
	private String situacaoAtual;

	@Column (name="valor_premio",columnDefinition="DECIMAL(14,2)", nullable=false)
	private BigDecimal valorPremio;

	@Column (name="dia_pagamento", nullable=false)
	private int diaPagamento;

	@Column (name="banco_pagamento",length=50, nullable=false)
	private String bancoPagamento;

	@Column (length=15, nullable=false)
	private String agencia;

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
	
	
}
