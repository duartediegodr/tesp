package br.unibh.seguros.entidades;

import java.io.Serializable;
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
import javax.persistence.Version;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="tb_proponente")@PrimaryKeyJoinColumn
public class Proponente extends PessoaFisica implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Pattern(regexp="[0-9]*",message="Deverá ter apenas números")
	@Size(min=5,max=8)
	@Column (columnDefinition="CHAR(8)", nullable=false,unique=true)
	private String matricula;
	
	@Column(name="data_cadastro", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@NotBlank
	@Size(max=30)
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Column (name="situacao_cadastro", nullable=false,length=30)
	private String situacaoCadastro;
	
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Size(max=30)
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
	
	public Proponente() {
		super();
	}
	public Proponente(String nome, String cpf, String telefoneResidencial,
			 int idade, Date dataNascimento,String matricula, Date dataCadastro, String situacaoCadastro, String status,
			Collection<Endereco> enderecos, Collection<Dependente> dependentes, Collection<Vinculo> vinculos,
			Collection<Proposta> proposta) {
		
		super(nome, cpf, null, telefoneResidencial, null, null, idade, dataNascimento);

		this.matricula = matricula;
		this.dataCadastro = dataCadastro;
		this.situacaoCadastro = situacaoCadastro;
		this.status = status;
		this.enderecos = enderecos;
		this.dependentes = dependentes;
		this.vinculos = vinculos;
		this.proposta = proposta;
	}
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((situacaoCadastro == null) ? 0 : situacaoCadastro.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}
	
}
