package br.unibh.seguros.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import br.unibh.seguros.util.CharacterToBooleanUtil;

@Entity
@Table(name="tb_questionario")
public class Questionario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="portador_necessidades_especiais", nullable=false)
	private Character portadorNecessidadesEspeciais;
	
	@Column(name="portador_molestia_grave", nullable=false)
	private Character portadorMolestiaGrave;
	
	@Column(name="utiliza_remedio_controlado", nullable=false)
	private Character utilizaRemedioControlado;
	
	@Past
	@Column(name="data_ultima_internacao")
	@Temporal(TemporalType.DATE)
	private Date dataUltimaInternacao;
	
	@Past
	@Column(name="data_ultima_consulta_medica")
	@Temporal(TemporalType.DATE)
	private Date dataUltimaConsultaMedica;
	
	@Column(name="possui_plano_saude_particular", nullable=false)
	private Character possuiPlanoSaudeParticular;
	
	@Column(name="pratica_esportes", nullable=false)
	private Character praticaEsportes;

	@Column(name="executa_atividade_de_risco", nullable=false)
	private Character executaAtividadeDeRisco;

	@Column(name="se_envolveu_em_acidente_ultimo_ano", nullable=false)
	private Character seEnvolveuEmAcidenteUltimoAno;

	@Column(name="historico_cancer_familia", nullable=false)
	private Character historicoCancerFamilia;

	@Column(name="possui_doenca_contagiosa", nullable=false)
	private Character possuiDoencaContagiosa;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean isPortadorNecessidadesEspeciais() {
		return CharacterToBooleanUtil.getBoolean(portadorNecessidadesEspeciais);
	}
	public void setPortadorNecessidadesEspeciais(Boolean portadorNecessidadesEspeciais) {
		this.portadorNecessidadesEspeciais = CharacterToBooleanUtil.getCharacter(portadorNecessidadesEspeciais);
	}
	public Boolean isPortadorMolestiaGrave() {
		return CharacterToBooleanUtil.getBoolean(portadorMolestiaGrave);
	}
	public void setPortadorMolestiaGrave(Boolean portadorMolestiaGrave) {
		this.portadorMolestiaGrave = CharacterToBooleanUtil.getCharacter(portadorMolestiaGrave);
	}
	public Boolean isUtilizaRemedioControlado() {
		return CharacterToBooleanUtil.getBoolean(utilizaRemedioControlado);
	}
	public void setUtilizaRemedioControlado(Boolean utilizaRemedioControlado) {
		this.utilizaRemedioControlado = CharacterToBooleanUtil.getCharacter(utilizaRemedioControlado);
	}
	public Date getDataUltimaInternacao() {
		return dataUltimaInternacao;
	}
	public void setDataUltimaInternacao(Date dataUltimaInternacao) {
		this.dataUltimaInternacao = dataUltimaInternacao;
	}
	public Date getDataUltimaConsultaMedica() {
		return dataUltimaConsultaMedica;
	}
	public void setDataUltimaConsultaMedica(Date dataUltimaConsultaMedica) {
		this.dataUltimaConsultaMedica = dataUltimaConsultaMedica;
	}
	public Boolean isPossuiPlanoSaudeParticular() {
		return CharacterToBooleanUtil.getBoolean(possuiPlanoSaudeParticular);
	}
	public void setPossuiPlanoSaudeParticular(Boolean possuiPlanoSaudeParticular) {
		this.possuiPlanoSaudeParticular = CharacterToBooleanUtil.getCharacter(possuiPlanoSaudeParticular);
	}
	public Boolean isPraticaEsportes() {
		return CharacterToBooleanUtil.getBoolean(praticaEsportes);
	}
	public void setPraticaEsportes(Boolean praticaEsportes) {
		this.praticaEsportes = CharacterToBooleanUtil.getCharacter(praticaEsportes);
	}
	public Boolean isExecutaAtividadeDeRisco() {
		return CharacterToBooleanUtil.getBoolean(executaAtividadeDeRisco);
	}
	public void setExecutaAtividadeDeRisco(Boolean executaAtividadeDeRisco) {
		this.executaAtividadeDeRisco = CharacterToBooleanUtil.getCharacter(executaAtividadeDeRisco);
	}
	public Boolean isSeEnvolveuEmAcidenteUltimoAno() {
		return CharacterToBooleanUtil.getBoolean(seEnvolveuEmAcidenteUltimoAno);
	}
	public void setSeEnvolveuEmAcidenteUltimoAno(Boolean seEnvolveuEmAcidenteUltimoAno) {
		this.seEnvolveuEmAcidenteUltimoAno = CharacterToBooleanUtil.getCharacter(seEnvolveuEmAcidenteUltimoAno);
	}
	public Boolean isHistoricoCancerFamilia() {
		return CharacterToBooleanUtil.getBoolean(historicoCancerFamilia);
	}
	public void setHistoricoCancerFamilia(Boolean historicoCancerFamilia) {
		this.historicoCancerFamilia = CharacterToBooleanUtil.getCharacter(historicoCancerFamilia);
	}
	public Boolean isPossuiDoencaContagiosa() {
		return CharacterToBooleanUtil.getBoolean(possuiDoencaContagiosa);
	}
	public void setPossuiDoencaContagiosa(Boolean possuiDoencaContagiosa) {
		this.possuiDoencaContagiosa = CharacterToBooleanUtil.getCharacter(possuiDoencaContagiosa);
	}
	
	
	
	
	
	
}
