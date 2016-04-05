package br.unibh.seguros.entidades;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="tb_proponente")@PrimaryKeyJoinColumn
public class Proponente extends PessoaFisica{
	
	@NotBlank
	@Pattern(regexp="[0-9]*",message="Deverá ter apenas números")
	@Size(min=5,max=8)
	@Column (columnDefinition="CHAR(8)", nullable=false,unique=true)
	private String matricula;
	
	@Column(name="data_cadastro", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Max(30)
	@Column (name="situacao_cadastro", nullable=false,length=30)
	private String situacaoCadastro;
	
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Max(30)
	@Column (nullable=false,length=30)
	private String status;
	
	@OneToMany(mappedBy="proponente",fetch=FetchType.LAZY)
	private Collection<Endereco> enderecos;
	
	@OneToMany(mappedBy="proponente",fetch=FetchType.LAZY)
	private Collection<Dependente> dependentes;
	
	@OneToMany(mappedBy="proponente",fetch=FetchType.LAZY)
	private Collection<Vinculo> vinculos;
	
	@OneToMany(mappedBy="proponente", fetch=FetchType.LAZY)
	private Collection<Proposta> proposta;
	
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getSituacaoCadastro() {
		return situacaoCadastro;
	}
	public void setSituacaoCadastro(String situacaoCadastro) {
		this.situacaoCadastro = situacaoCadastro;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Collection<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(Collection<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	public Collection<Dependente> getDependentes() {
		return dependentes;
	}
	public void setDependentes(Collection<Dependente> dependentes) {
		this.dependentes = dependentes;
	}
	public Collection<Vinculo> getVinculos() {
		return vinculos;
	}
	public void setVinculos(Collection<Vinculo> vinculos) {
		this.vinculos = vinculos;
	}
	public Collection<Proposta> getProposta() {
		return proposta;
	}
	public void setProposta(Collection<Proposta> proposta) {
		this.proposta = proposta;
	}
}
