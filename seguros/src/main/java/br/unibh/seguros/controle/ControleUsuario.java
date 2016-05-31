package br.unibh.seguros.controle;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.unibh.seguros.entidades.Usuario;
import br.unibh.seguros.negocio.ServicoUsuario;

@ManagedBean(name = "usuariomb")
@ViewScoped
public class ControleUsuario {

	@Inject
	private Logger log;
	
	@Inject
	private ServicoUsuario ejb;
	
	private Usuario usuario;
	private String nomeArg;
	private List<Usuario> lista;
	
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
			if(usuario.getId()==null){
				usuario = ejb.insert(usuario);
			}else{
				usuario = ejb.update(usuario);
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
		usuario = new Usuario();
	}
	
	public void cancelar() {
		usuario = null;
	}
	
	public void editar(Long id) {
		try {
			usuario = ejb.find(id);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		usuario = null;
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
		usuario = null;
		facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão realizada com sucesso!", "");
		FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);
	}
	
	
	
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getNomeArg() {
		return nomeArg;
	}
	public void setNomeArg(String nomeArg) {
		this.nomeArg = nomeArg;
	}
	public List<Usuario> getLista() {
		return lista;
	}
	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}
	
	

}
