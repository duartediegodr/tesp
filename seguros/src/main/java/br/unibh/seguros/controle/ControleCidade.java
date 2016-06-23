package br.unibh.seguros.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.unibh.seguros.entidades.Setor;
import br.unibh.seguros.entidades.Cidade;
import br.unibh.seguros.negocio.ServicoSetor;
import br.unibh.seguros.negocio.ServicoCidade;

@ManagedBean(name = "cidademb")
@ViewScoped
public class ControleCidade {

	@Inject
	private Logger log;
	
	@Inject
	private ServicoCidade ejbCidade;
	
	@Inject
	private ServicoSetor ejbSetor;
	
	private Cidade Cidade;
	private String nomeArg;
	private List<Cidade> lista;
	
	@PostConstruct
	public void inicializaLista(){
		try {
			lista = ejbCidade.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void gravar(){
		FacesMessage facesMsg;
		try {
			if(Cidade.getId()==null){
				Cidade = ejbCidade.insert(Cidade);
			}else{
				Cidade = ejbCidade.update(Cidade);
			}
			lista = ejbCidade.findByName(nomeArg);
		} catch (Exception e) {
			facesMsg = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,"Erro: "+e.getMessage(),"");
			log.warning("Erro: "+e.getMessage());
			return;
		}
		facesMsg = new FacesMessage(
				FacesMessage.SEVERITY_INFO,"Gravação realizada com sucesso!","");
		FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);
		Cidade=null;
	}
	
	public void pesquisar(){
		try {
			lista=ejbCidade.findByName(nomeArg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void novo() {
		Cidade = new Cidade();
	}
	
	public void cancelar() {
		Cidade = null;
	}
	
	public void editar(Long id) {
		try {
			Cidade = ejbCidade.find(id);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		Cidade = null;
	}
	
	public void excluir(Long id) {
		FacesMessage facesMsg;
		try {
			ejbCidade.delete(ejbCidade.find(id));
			lista = ejbCidade.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: " + e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);
			log.warning("Erro: "+e.getMessage());
			return;
		}
		Cidade = null;
		facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão realizada com sucesso!", "");
		FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);
	}
	
	public List<Setor> getSetores(){
		try{
			return ejbSetor.findAll(); 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Setor>();
	}
	
	
	
	
	
	public Cidade getCidade() {
		return Cidade;
	}
	public void setCidade(Cidade Cidade) {
		this.Cidade = Cidade;
	}
	public String getNomeArg() {
		return nomeArg;
	}
	public void setNomeArg(String nomeArg) {
		this.nomeArg = nomeArg;
	}
	public List<Cidade> getLista() {
		return lista;
	}
	public void setLista(List<Cidade> lista) {
		this.lista = lista;
	}
}
