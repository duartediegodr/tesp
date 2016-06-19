package br.unibh.seguros.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJBs;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.unibh.seguros.entidades.Setor;
import br.unibh.seguros.entidades.Usuario;
import br.unibh.seguros.negocio.ServicoSetor;
import br.unibh.seguros.negocio.ServicoUsuario;

@ManagedBean(name = "usuariomb")
@ViewScoped
public class ControleUsuario {

	@Inject
	private Logger log;
	
	@Inject
	private ServicoUsuario ejbUsuario;
	
	@Inject
	private ServicoSetor ejbSetor;
	
	private Usuario usuario;
	private String nomeArg;
	private List<Usuario> lista;
	
	@PostConstruct
	public void inicializaLista(){
		try {
			lista = ejbUsuario.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void gravar(){
		FacesMessage facesMsg;
		try {
			if(usuario.getId()==null){
				usuario = ejbUsuario.insert(usuario);
			}else{
				usuario = ejbUsuario.update(usuario);
			}
			lista = ejbUsuario.findByName(nomeArg);
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
			lista=ejbUsuario.findByName(nomeArg);
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
			usuario = ejbUsuario.find(id);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		usuario = null;
	}
	
	public void excluir(Long id) {
		FacesMessage facesMsg;
		try {
			ejbUsuario.delete(ejbUsuario.find(id));
			lista = ejbUsuario.findAll();
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
	
	public List<Setor> getSetores(){
		try{
			return ejbSetor.findAll(); 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Setor>();
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
