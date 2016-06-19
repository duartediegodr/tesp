package br.unibh.seguros.entidades;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="tb_tramitacao")
@NamedQueries({
	@NamedQuery(name="Tramitacao.findByEtapaProcesso", 
			query="select o from Tramitacao o where o.etapaProcesso like :etapaProcesso")
})
public class Tramitacao implements Serializable{
	private static final long serialVersionUID = 1L;
	@Version
	@Column(columnDefinition="bigint NOT NULL DEFAULT 0")
	private Long version;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Size(max=30)
	@Column (name="etapa_processo",length=30, nullable=false)
	private String etapaProcesso;
	
	@Column(name="data_hora", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHora;

	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Size(max=50)
	@Column (name="situacao_inicial",length=50, nullable=false)
	private String situacaoInicial;

	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Size(max=50)
	@Column (name="situacao_final",length=50, nullable=false)
	private String situacaoFinal;

	@NotBlank
	@Pattern(regexp="[A-zÀ-ú ]*",message="Deverá ter apenas Letras e Espaço")
	@Size(max=100)
	@Column (name="tipo_decisao",length=100, nullable=false)
	private String tipoDecisao;


	@NotBlank
	@Size(max=4000)
	@Column (columnDefinition="TEXT(4000)", nullable=false)
	private String comentario;

	@Lob
	@Column
	private String documento;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Proposta proposta;

	@JoinColumn(name="setor_responsavel")
	@ManyToOne(fetch=FetchType.EAGER)
	private Setor setorResponsavel;
	
	@JoinColumn(name="usuario_decisao")
	@ManyToOne(fetch=FetchType.EAGER)
	private Usuario usuarioDecisao;

	
	public Tramitacao() {
		super();
	}

	public Tramitacao(String etapaProcesso, Date dataHora, String situacaoInicial, String situacaoFinal,
			String tipoDecisao, String comentario, String documento, Proposta proposta, Setor setorResponsavel,
			Usuario usuarioDecisao) {
		super();
		this.etapaProcesso = etapaProcesso;
		this.dataHora = dataHora;
		this.situacaoInicial = situacaoInicial;
		this.situacaoFinal = situacaoFinal;
		this.tipoDecisao = tipoDecisao;
		this.comentario = comentario;
		this.documento = documento;
		this.proposta = proposta;
		this.setorResponsavel = setorResponsavel;
		this.usuarioDecisao = usuarioDecisao;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEtapaProcesso() {
		return etapaProcesso;
	}
	public void setEtapaProcesso(String etapaProcesso) {
		this.etapaProcesso = etapaProcesso;
	}
	public Date getDataHora() {
		return dataHora;
	}
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	public String getSituacaoInicial() {
		return situacaoInicial;
	}
	public void setSituacaoInicial(String situacaoInicial) {
		this.situacaoInicial = situacaoInicial;
	}
	public String getSituacaoFinal() {
		return situacaoFinal;
	}
	public void setSituacaoFinal(String situacaoFinal) {
		this.situacaoFinal = situacaoFinal;
	}
	public String getTipoDecisao() {
		return tipoDecisao;
	}
	public void setTipoDecisao(String tipoDecisao) {
		this.tipoDecisao = tipoDecisao;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public Proposta getProposta() {
		return proposta;
	}
	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}
	public Setor getSetorResponsavel() {
		return setorResponsavel;
	}
	public void setSetorResponsavel(Setor setorResponsavel) {
		this.setorResponsavel = setorResponsavel;
	}
	public Usuario getUsuarioDecisao() {
		return usuarioDecisao;
	}
	public void setUsuarioDecisao(Usuario usuarioDecisao) {
		this.usuarioDecisao = usuarioDecisao;
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
		result = prime * result + ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + ((dataHora == null) ? 0 : dataHora.hashCode());
		result = prime * result + ((documento == null) ? 0 : documento.hashCode());
		result = prime * result + ((etapaProcesso == null) ? 0 : etapaProcesso.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((proposta == null) ? 0 : proposta.hashCode());
		result = prime * result + ((setorResponsavel == null) ? 0 : setorResponsavel.hashCode());
		result = prime * result + ((situacaoFinal == null) ? 0 : situacaoFinal.hashCode());
		result = prime * result + ((situacaoInicial == null) ? 0 : situacaoInicial.hashCode());
		result = prime * result + ((tipoDecisao == null) ? 0 : tipoDecisao.hashCode());
		result = prime * result + ((usuarioDecisao == null) ? 0 : usuarioDecisao.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}
	
}
