package br.unibh.seguros.negocio;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.unibh.seguros.entidades.Seguro;

@Stateless
@LocalBean
public class ServicoSeguro implements DAO<Seguro, Long> {

	@Inject
	EntityManager em;
	
	@Inject
	private Logger log;



	@Override
	public Seguro insert(Seguro t) throws Exception {
		log.info("Persistindo "+t);
		em.persist(t);
		return t;
	}

	@Override
	public Seguro update(Seguro t) throws Exception {
		log.info("Atualizando "+t);
		em.merge(t);
		return t;
	}

	@Override
	public void delete(Seguro t) throws Exception {
		log.info("Removendo "+t);
		Object c = em.merge(t);
		em.remove(c);
	}

	@Override
	public Seguro find(Long k) throws Exception {
		log.info("Encontrando"+k);
		return em.find(Seguro.class, k);
	}

	@Override
	public List<Seguro> findAll() throws Exception {
		log.info("Encontrando todos os Seguros");
		return em.createQuery("from Setor").getResultList();
	}

	@Override
	public List<Seguro> findByName(String name) throws Exception {
		log.info("Encontrando o "+name);
		return em.createNamedQuery("Setor.findByName")
				.setParameter("nome", name+"%").getResultList();
	}

}
