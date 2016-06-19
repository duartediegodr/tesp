package br.unibh.seguros.controle;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.unibh.seguros.entidades.Seguro;
import br.unibh.seguros.negocio.ServicoSeguro;

@ManagedBean(name = "seguromb")
@ViewScoped
public class ControleSeguro {

	@Inject
	private Logger log;
	
	@Inject
	private ServicoSeguro ejb;
	private Seguro seguro;
	private String nomeArg;
	private List<Seguro> lista;
	
	public Seguro getSeguro() {
		return seguro;
	}
	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}
	
	public String getNomeArg() {
		return nomeArg;
	}
	
	public void setNomeArg(String nomeArg) {
		this.nomeArg = nomeArg;
	}
	public List<Seguro> getLista() {
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
			if (seguro.getId() == null) {
				seguro = ejb.insert(seguro);
			} else {
				seguro = ejb.update(seguro);
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
			lista = ejb.findByCodigoSusep(nomeArg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void novo(){
		seguro = new Seguro();
	}
	
	public void cancelar(){
		seguro = null;
	}
	
	public void editar(Long id){
		try {
			seguro = ejb.find(id);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		seguro = null;
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
		seguro = null;
		facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão realizada com sucesso!", "");
		FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);
	}
}
