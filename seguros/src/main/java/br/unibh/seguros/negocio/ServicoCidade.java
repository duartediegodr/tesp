package br.unibh.seguros.negocio;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.unibh.seguros.entidades.Cidade;

@Stateless
@LocalBean
public class ServicoCidade implements DAO<Cidade, Long> {
	@Inject
	EntityManager em;
	
	@Inject
	private Logger log;
	
	@Override
	public Cidade insert(Cidade t) throws Exception {
		log.info("Persistindo "+t);
		em.persist(t);
		return t;
	}

	@Override
	public Cidade update(Cidade t) throws Exception {
		log.info("Atualizando "+t);
		em.merge(t);
		return t;
	}

	@Override
	public void delete(Cidade t) throws Exception {
		log.info("Removendo "+t);
		Object c = em.merge(t);
		em.remove(c);
	}

	@Override
	public Cidade find(Long k) throws Exception {
		log.info("Encontrando"+k);
		return em.find(Cidade.class, k);
	}

	@Override
	public List<Cidade> findAll() throws Exception {
		log.info("Encontrando todos as Cidades");
		return em.createQuery("select o from Cidade o order by o.cidade asc").getResultList();
	}

	@Override
	public List<Cidade> findByName(String name) throws Exception {
		log.info("Encontrando o "+name);
		return em.createNamedQuery("Cidade.findByName")
				.setParameter("nome", name+"%").getResultList();
	}
}
