package br.unibh.seguros.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import br.unibh.seguros.util.CharacterToBooleanUtil;

@Entity
@Table(name="tb_dependente")@PrimaryKeyJoinColumn
public class Dependente extends PessoaFisica implements Serializable{
	private static final long serialVersionUID = 1L;
	@Version
	@Column(columnDefinition="bigint NOT NULL DEFAULT 0")
	private Long version;

	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Size(max=30)
	@Column (name="grau_parentesco",length=30, nullable=false)
	private String grauParentesco;
	
	@NotNull
	@DecimalMin(value="0.0",message="Valor deve ser menor ou igual a 100.0 e maior ou igual que 0.0")
	@DecimalMax(value="100.0",message="Valor deve ser menor ou igual a 100.0 e maior ou igual que 0.0")
	@Column (name="percentual_beneficio",columnDefinition="DECIMAL(30)", nullable=false)
	private BigDecimal percentualBeneficio;
	
	@Column (name="dependente_ir", columnDefinition="CHAR(1)", nullable=false)
	private Character dependenteIR;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Proponente proponente;
	
	public Dependente() {
		super();
	}
	public Dependente(String nome, String cpf, int idade, String telefoneResidencial, 
			String grauParentesco, BigDecimal percentualBeneficio, Boolean dependenteIR,
			Date dataNascimento, Proponente proponente) {
		super();
		this.setNome(nome);
		this.setCpf(cpf);
		this.setIdade(idade);
		this.setTelefoneResidencial(telefoneResidencial);
		this.setDataNascimento(dataNascimento);
		this.grauParentesco = grauParentesco;
		this.percentualBeneficio = percentualBeneficio;
		this.setDependenteIR(dependenteIR);
		this.proponente = proponente;
	}
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
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dependenteIR == null) ? 0 : dependenteIR.hashCode());
		result = prime * result + ((grauParentesco == null) ? 0 : grauParentesco.hashCode());
		result = prime * result + ((percentualBeneficio == null) ? 0 : percentualBeneficio.hashCode());
		result = prime * result + ((proponente == null) ? 0 : proponente.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}
	
}
