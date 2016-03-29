package br.unibh.escola.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.unibh.escola.util.CharacterToBooleanUtil;

@Entity
@Table(name="tb_dependente")@PrimaryKeyJoinColumn
public class Dependente extends PessoaFisica {
	
	@Column (name="grau_parentesco",length=30, nullable=false)
	private String grauParentesco;
	
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
