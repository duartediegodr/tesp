package br.unibh.escola.entidades;

import java.io.File;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_tramitacao")
public class Tramitacao {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column (name="etapa_processo",length=30, nullable=false)
	private String etapaProcesso;
	
	@Column(name="data_hora", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHora;

	@Column (name="situacao_inicial",length=50, nullable=false)
	private String situacaoInicial;

	@Column (name="situacao_final",length=50, nullable=false)
	private String situacaoFinal;

	@Column (name="tipo_decisao",length=100, nullable=false)
	private String tipoDecisao;

	@Column (columnDefinition="TEXT(4000)", nullable=false)
	private String comentario;

	@Lob
	@Column
	private File documento;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Proposta proposta;

	@JoinColumn(name="setor_responsavel")
	@ManyToOne(fetch=FetchType.EAGER)
	private Setor setorResponsavel;
	
	@JoinColumn(name="usuario_decisao")
	@ManyToOne(fetch=FetchType.EAGER)
	private Usuario usuarioDecisao;
	
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
	public File getDocumento() {
		return documento;
	}
	public void setDocumento(File documento) {
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
	
	
	
	
	
	
	
	
}
