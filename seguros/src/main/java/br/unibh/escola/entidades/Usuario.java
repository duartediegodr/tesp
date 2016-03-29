package br.unibh.escola.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column (length=15, nullable=false,unique=true)
	private String login;
	
	@Column (length=100, nullable=false)
	private String senha;
	
	@Column (length=100, nullable=false)
	private String nome;
	
	@Column (length=30, nullable=false)
	private String perfil;
	
	@Column (length=100, nullable=false)
	private String cargo;
	
	@Column (length=100, nullable=false)
	private String email;
	
	@Column(name="data_cadastro", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Setor setor;
	
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
}
