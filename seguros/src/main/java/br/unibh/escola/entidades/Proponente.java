package br.unibh.escola.entidades;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_proponente")@PrimaryKeyJoinColumn
public class Proponente extends PessoaFisica{
	
	@Column (columnDefinition="CHAR(8)", nullable=false,unique=true)
	private String matricula;
	
	@Column(name="data_cadastro", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Column (name="situacao_cadastro", nullable=false,length=30)
	private String situacaoCadastro;
	
	@Column (nullable=false,length=30)
	private String status;
	
	@OneToMany(mappedBy="proponente",fetch=FetchType.LAZY)
	private Collection<Endereco> enderecos;
	
	@OneToMany(mappedBy="proponente",fetch=FetchType.LAZY)
	private Collection<Dependente> dependentes;
	
	@OneToMany(mappedBy="proponente",fetch=FetchType.LAZY)
	private Collection<Vinculo> vinculos;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Proposta proposta;
	
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
	public Proposta getProposta() {
		return proposta;
	}
	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}
}
