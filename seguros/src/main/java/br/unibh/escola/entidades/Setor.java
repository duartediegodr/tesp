package br.unibh.escola.entidades;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_setor")
public class Setor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column (length=150, nullable=false,unique=true)
	private String nome;
	
	@OneToMany(mappedBy="setorSuperior", fetch=FetchType.LAZY)
	private Collection<Setor> setoresInferiores;
	
	@JoinColumn (name="setor_superior")
	@ManyToOne(fetch=FetchType.LAZY)
	private Setor setorSuperior;
	
	@OneToMany(mappedBy ="setor", fetch=FetchType.LAZY)
	private Collection<Usuario> mebros;
	
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
	public Collection<Usuario> getMebros() {
		return mebros;
	}
	public void setMebros(Collection<Usuario> mebros) {
		this.mebros = mebros;
	}
	public Collection<Setor> getSetoresInferiores() {
		return setoresInferiores;
	}
	public void setSetoresInferiores(Collection<Setor> setoresInferiores) {
		this.setoresInferiores = setoresInferiores;
	}
}
