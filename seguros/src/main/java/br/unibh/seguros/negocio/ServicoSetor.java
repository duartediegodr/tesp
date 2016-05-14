package br.unibh.seguros.negocio;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.unibh.seguros.entidades.Setor;

@Stateless
@LocalBean
public class ServicoSetor implements DAO<Setor, Long> {

	@Inject
	EntityManager em;
	
	@Inject
	private Logger log;



	@Override
	public Setor insert(Setor t) throws Exception {
		log.info("Persistindo "+t);
		em.persist(t);
		return t;
	}

	@Override
	public Setor update(Setor t) throws Exception {
		log.info("Atualizando "+t);
		em.merge(t);
		return t;
	}

	@Override
	public void delete(Setor t) throws Exception {
		log.info("Removendo "+t);
		Object c = em.merge(t);
		em.remove(c);
	}

	@Override
	public Setor find(Long k) throws Exception {
		log.info("Encontrando"+k);
		return em.find(Setor.class, k);
	}

	@Override
	public List<Setor> findAll() throws Exception {
		log.info("Encontrando todos os setores");
		return em.createQuery("from Setor").getResultList();
	}

	@Override
	public List<Setor> findByName(String name) throws Exception {
		log.info("Encontrando o "+name);
		return em.createNamedQuery("Setor.findByName")
				.setParameter("nome", name+"%").getResultList();
	}

}
