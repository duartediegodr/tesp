package br.unibh.seguros.controle;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.unibh.seguros.entidades.Proponente;
import br.unibh.seguros.negocio.ServicoProponente;

@ManagedBean(name = "proponentemb")
@ViewScoped
public class ControleProponete {

	@Inject
	private Logger log;
	
	@Inject
	private ServicoProponente ejb;
	private Proponente proponente;
	private String nomeArg;
	private List<Proponente> lista;
	
	public Proponente getProponente() {
		return proponente;
	}
	public void setProponente(Proponente proponente) {
		this.proponente = proponente;
	}
	
	public String getNomeArg() {
		return nomeArg;
	}
	
	public void setNomeArg(String nomeArg) {
		this.nomeArg = nomeArg;
	}
	public List<Proponente> getLista() {
		return lista;
	}
	
	@PostConstruct
	public void inicializaLista(){
		try {
			lista = ejb.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void gravar() {
		
		FacesMessage facesMsg;
		try {
			if (proponente.getId() == null) {
				proponente.setDataCadastro(new Date());
				proponente = ejb.insert(proponente);
			} else {
				proponente = ejb.update(proponente);
			}
		
			lista = ejb.findByName(nomeArg);
		} catch (Exception e){
			facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:"+e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("messagePanel",facesMsg);
			log.warning("Erro: "+e.getMessage());
			return;
		}
		
		facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Gravação realizada com sucesso!", "");
		FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);
	}
	
	public void pesquisar() {
		try {
			lista = ejb.findByName(nomeArg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void novo(){
		proponente = new Proponente();
	}
	
	public void cancelar(){
		proponente = null;
	}
	
	public void editar(Long id){
		try {
			proponente = ejb.find(id);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		proponente = null;
	}
	
	public void excluir(Long id){
		FacesMessage facesMsg;
		
		try {
			ejb.delete(ejb.find(id));
			lista = ejb.findByName(nomeArg);
		} catch (Exception e) {
			e.printStackTrace();
			facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: "+ e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("messagePanel",facesMsg);
			log.warning("Erro: "+e.getMessage());
			return;
		}
		proponente = null;
		facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão realizada com sucesso!", "");
		FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);
	}}
