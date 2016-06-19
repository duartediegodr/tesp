package br.unibh.seguros.controle;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.unibh.seguros.entidades.Proposta;
import br.unibh.seguros.negocio.ServicoProposta;

@ManagedBean(name = "propostamb")
@ViewScoped
public class ControleProposta {

	@Inject
	private Logger log;
	
	@Inject
	private ServicoProposta ejb;
	private Proposta proposta;
	private String nomeArg;
	private List<Proposta> lista;
	
	public Proposta getProposta() {
		return proposta;
	}
	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}
	
	public String getNomeArg() {
		return nomeArg;
	}
	
	public void setNomeArg(String nomeArg) {
		this.nomeArg = nomeArg;
	}
	public List<Proposta> getLista() {
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
			if (proposta.getId() == null) {
				proposta = ejb.insert(proposta);
			} else {
				proposta = ejb.update(proposta);
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
			lista = ejb.findByConta(nomeArg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void novo(){
		proposta = new Proposta();
	}
	
	public void cancelar(){
		proposta = null;
	}
	
	public void editar(Long id){
		try {
			proposta = ejb.find(id);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		proposta = null;
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
		proposta = null;
		facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão realizada com sucesso!", "");
		FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);
	}
}