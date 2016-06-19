package br.unibh.seguros.entidades;

import java.io.Serializable;
import java.util.Collection;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="tb_setor")
@NamedQueries({
	@NamedQuery(name="Setor.findByName", 
			query="select o from Setor o where o.nome like :nome"),
	@NamedQuery(name="Setor.findOneById", 
	query="select o from Setor o LEFT JOIN FETCH o.membros where o.id like :id")
})
public class Setor implements Serializable{
	private static final long serialVersionUID = 1L;
	@Version
	@Column(columnDefinition="bigint NOT NULL DEFAULT 0")
	private Long version;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Favor fornecer apenas letras")
	@Size(min=3,max=150)
	@Column (length=150, nullable=false,unique=true)
	private String nome;
	
	@JoinColumn (name="setor_superior")
	@ManyToOne(fetch=FetchType.LAZY)
	private Setor setorSuperior;
	
	@OneToMany(mappedBy ="setor", fetch=FetchType.LAZY)
	private Collection<Usuario> membros;
	
	@OneToMany(mappedBy ="setorResponsavel", fetch=FetchType.LAZY)
	private Collection<Tramitacao> tramitacoes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Setor getSetorSuperior() {
		return setorSuperior;
	}
	public void setSetorSuperior(Setor setorSuperior) {
		this.setorSuperior = setorSuperior;
	}
	public Collection<Usuario> getMembros() {
		return membros;
	}
	public void setMembros(Collection<Usuario> mebros) {
		this.membros = mebros;
	}
	public Collection<Tramitacao> getTramitacoes() {
		return tramitacoes;
	}
	public void setTramitacoes(Collection<Tramitacao> tramitacoes) {
		this.tramitacoes = tramitacoes;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}
	
}
