package br.unibh.seguros.entidades;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import br.unibh.seguros.util.CharacterToBooleanUtil;

@Entity
@Table(name="tb_dependente")@PrimaryKeyJoinColumn
public class Dependente extends PessoaFisica implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Max(30)
	@Column (name="grau_parentesco",length=30, nullable=false)
	private String grauParentesco;
	
	@NotNull
	@DecimalMin("0.0")
	@DecimalMax("100.0")
	@Column (name="percentual_beneficio",columnDefinition="DECIMAL(30)", nullable=false)
	private BigDecimal percentualBeneficio;
	
	@Column (name="dependente_ir", columnDefinition="CHAR(1)", nullable=false)
	private Character dependenteIR;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Proponente proponente;
	
	public String getGrauParentesco() {
		return grauParentesco;
	}
	public void setGrauParentesco(String grauParentesco) {
		this.grauParentesco = grauParentesco;
	}
	public BigDecimal getPercentualBeneficio() {
		return percentualBeneficio;
	}
	public void setPercentualBeneficio(BigDecimal percentualBeneficio) {
		this.percentualBeneficio = percentualBeneficio;
	}
	public Boolean isDependenteIR() {
		return CharacterToBooleanUtil.getBoolean(dependenteIR);
	}
	public void setDependenteIR(Boolean dependenteR) {
		this.dependenteIR = CharacterToBooleanUtil.getCharacter(dependenteR);
	}
	public Proponente getProponente() {
		return proponente;
	}
	public void setProponente(Proponente proponente) {
		this.proponente = proponente;
	}
}
