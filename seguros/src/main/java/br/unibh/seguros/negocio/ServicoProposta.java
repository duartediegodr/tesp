package br.unibh.seguros.negocio;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.unibh.seguros.entidades.Proposta;
import br.unibh.seguros.entidades.Questionario;
import br.unibh.seguros.entidades.Seguro;

@Stateless
@LocalBean
public class ServicoProposta implements DAO<Proposta, Long> {

	@Inject
	EntityManager em;
	
	@Inject
	private Logger log;



	@Override
	public Proposta insert(Proposta t) throws Exception {
		//FIXME deve ser corrigido --------- 
		Questionario q = new Questionario();
		q.setId(1L);
		t.setData(new Date());
		t.setQuestionario(q);
		//----------------------------------
		log.info("Persistindo "+t);
		em.persist(t);
		return t;
	}

	@Override
	public Proposta update(Proposta t) throws Exception {
		log.info("Atualizando "+t);
		em.merge(t);
		return t;
	}

	@Override
	public void delete(Proposta t) throws Exception {
		log.info("Removendo "+t);
		Object c = em.merge(t);
		em.remove(c);
	}

	@Override
	public Proposta find(Long k) throws Exception {
		log.info("Encontrando"+k);
		return em.find(Proposta.class, k);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proposta> findAll() throws Exception {
		log.info("Encontrando todos as Proposta");
		return em.createQuery("from Proposta").getResultList();
	}

	@Override
	public List<Proposta> findByName(String name) throws Exception {
		return findByConta(name);
	}
	@SuppressWarnings("unchecked")
	public List<Proposta> findByConta(String conta) throws Exception {
		return em.createNamedQuery("Proposta.findByConta")
				.setParameter("conta", conta+"%").getResultList();
	}

}
