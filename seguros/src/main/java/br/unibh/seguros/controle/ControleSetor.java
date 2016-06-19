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

import org.primefaces.model.DualListModel;

import br.unibh.seguros.entidades.Setor;
import br.unibh.seguros.entidades.Usuario;
import br.unibh.seguros.negocio.ServicoSetor;
import br.unibh.seguros.negocio.ServicoUsuario;

@ManagedBean(name = "setormb")
@ViewScoped
public class ControleSetor {

	@Inject
	private Logger log;
	
	@Inject
	private ServicoSetor ejbSetor;
	@Inject
	private ServicoUsuario ejbUsuario;

	@SuppressWarnings("unused")
	private DualListModel<Usuario> usuarios;
	
	private Setor setor;
	private String nomeArg;
	private List<Setor> lista;
	
	@PostConstruct
	public void inicializaLista(){
		try {
			lista = ejbSetor.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void gravar(){
		FacesMessage facesMsg;
		try {
			if(setor.getId()==null){
				setor = ejbSetor.insert(setor);
			}else{
				setor = ejbSetor.update(setor);
			}
			lista = ejbSetor.findByName(nomeArg);
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
			lista=ejbSetor.findByName(nomeArg);
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
			setor = ejbSetor.find(id);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		setor = null;
	}
	
	public void excluir(Long id) {
		FacesMessage facesMsg;
		try {
			ejbSetor.delete(ejbSetor.find(id));
			lista = ejbSetor.findAll();
		} catch (Exception e) {
			if (checkString(e, "a foreign key constraint fails")) {
				facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Não é possível excluir o Setor pois possui setores filhos vinculados", "");
				FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);
				return;
			} else {
				e.printStackTrace();
				facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: " + e.getMessage(), "");
				FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);
				log.warning("Erro: " + e.getMessage());
				return;
			}
		}
		setor = null;
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

	public DualListModel<Usuario> getUsuarios() throws Exception {
		List<Usuario> source = new ArrayList<Usuario>();
		List<Usuario> target = new ArrayList<Usuario>();
		if (setor != null && setor.getId() != null) {
			setor = ejbSetor.find(setor.getId());
			if (setor.getMembros() != null) {
				target = new ArrayList<Usuario>(setor.getMembros());
			}
		}
		source = ejbUsuario.findAll();
		if (target.size() > 0) {
			for (Usuario a : target) {
				source.remove(a);
			}
		}
		return new DualListModel<Usuario>(source, target);
	}

	public void setUsuarios(DualListModel<Usuario> usuarios) {
		this.usuarios = usuarios;
		setor.setMembros(usuarios.getTarget());
	}

	private boolean checkString(Throwable e, String str) {
		if (e.getMessage().contains(str)) {
			return true;
		} else if (e.getCause() != null) {
			return checkString(e.getCause(), str);
		}
		return false;
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
