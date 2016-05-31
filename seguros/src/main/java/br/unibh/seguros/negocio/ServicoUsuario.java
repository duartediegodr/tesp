package br.unibh.seguros.negocio;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.unibh.seguros.entidades.Usuario;

@Stateless
@LocalBean
public class ServicoUsuario implements DAO<Usuario, Long> {

	@Inject
	EntityManager em;
	
	@Inject
	private Logger log;



	@Override
	public Usuario insert(Usuario t) throws Exception {
		log.info("Persistindo "+t);
		em.persist(t);
		return t;
	}

	@Override
	public Usuario update(Usuario t) throws Exception {
		log.info("Atualizando "+t);
		em.merge(t);
		return t;
	}

	@Override
	public void delete(Usuario t) throws Exception {
		log.info("Removendo "+t);
		Object c = em.merge(t);
		em.remove(c);
	}

	@Override
	public Usuario find(Long k) throws Exception {
		log.info("Encontrando"+k);
		return em.find(Usuario.class, k);
	}

	@Override
	public List<Usuario> findAll() throws Exception {
		log.info("Encontrando todos os Usuarios");
		return em.createQuery("from Usuario").getResultList();
	}

	@Override
	public List<Usuario> findByName(String name) throws Exception {
		log.info("Encontrando o "+name);
		return em.createNamedQuery("Usuario.findByName")
				.setParameter("nome", name+"%").getResultList();
	}

}
