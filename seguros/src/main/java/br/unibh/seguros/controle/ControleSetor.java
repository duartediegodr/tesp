package br.unibh.seguros.controle;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.unibh.seguros.entidades.Setor;
import br.unibh.seguros.negocio.ServicoSetor;

@ManagedBean(name = "setormb")
@ViewScoped
public class ControleSetor {

	@Inject
	private Logger log;
	
	@Inject
	private ServicoSetor ejb;
	
	private Setor setor;
	private String nomeArg;
	private List<Setor> lista;
	
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
			if(setor.getId()==null){
				setor = ejb.insert(setor);
			}else{
				setor = ejb.update(setor);
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
		setor = new Setor();
	}
	
	public void cancelar() {
		setor = null;
	}
	
	public void editar(Long id) {
		try {
			setor = ejb.find(id);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		setor = null;
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
		setor = null;
		facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão realizada com sucesso!", "");
		FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);
	}
	
	
	
	
	
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	public String getNomeArg() {
		return nomeArg;
	}
	public void setNomeArg(String nomeArg) {
		this.nomeArg = nomeArg;
	}
	public List<Setor> getLista() {
		return lista;
	}
	public void setLista(List<Setor> lista) {
		this.lista = lista;
	}
	
	

}
