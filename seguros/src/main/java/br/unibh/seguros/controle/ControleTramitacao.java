package br.unibh.seguros.controle;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.unibh.seguros.entidades.Tramitacao;
import br.unibh.seguros.negocio.ServicoTramitacao;

@ManagedBean(name = "tramitacaomb")
@ViewScoped
public class ControleTramitacao {

	@Inject
	private Logger log;
	
	@Inject
	private ServicoTramitacao ejb;
	
	private Tramitacao tramitacao;
	private String nomeArg;
	private List<Tramitacao> lista;
	
	@PostConstruct
	public void inicializaLista(){
		try {
			lista = ejb.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void gravar(){
		FacesMessage facesMsg;
		try {
			if(tramitacao.getId()==null){
				tramitacao = ejb.insert(tramitacao);
			}else{
				tramitacao = ejb.update(tramitacao);
			}
			lista = ejb.findByName(nomeArg);
		} catch (Exception e) {
			facesMsg = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,"Erro: "+e.getMessage(),"");
			log.warning("Erro: "+e.getMessage());
			return;
		}
		facesMsg = new FacesMessage(
				FacesMessage.SEVERITY_ERROR,"Gravação realizada com sucesso!","");
		FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);
	}
	
	public void pesquisar(){
		try {
			lista=ejb.findByName(nomeArg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void novo() {
		tramitacao = new Tramitacao();
	}
	
	public void cancelar() {
		tramitacao = null;
	}
	
	public void editar(Long id) {
		try {
			tramitacao = ejb.find(id);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		tramitacao = null;
	}
	
	public void excluir(Long id) {
		FacesMessage facesMsg;
		try {
			ejb.delete(ejb.find(id));
			lista = ejb.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: " + e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);
			log.warning("Erro: "+e.getMessage());
			return;
		}
		tramitacao = null;
		facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão realizada com sucesso!", "");
		FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);
	}
	
	
	
	
	
	public Tramitacao getTramitacao() {
		return tramitacao;
	}
	public void setTramitacao(Tramitacao tramitacao) {
		this.tramitacao = tramitacao;
	}
	public String getNomeArg() {
		return nomeArg;
	}
	public void setNomeArg(String nomeArg) {
		this.nomeArg = nomeArg;
	}
	public List<Tramitacao> getLista() {
		return lista;
	}
	public void setLista(List<Tramitacao> lista) {
		this.lista = lista;
	}
	
	

}
