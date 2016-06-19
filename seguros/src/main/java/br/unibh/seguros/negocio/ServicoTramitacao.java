package br.unibh.seguros.negocio;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.unibh.seguros.entidades.Tramitacao;


@Stateless
@LocalBean
public class ServicoTramitacao implements DAO<Tramitacao, Long> {

	@Inject
	EntityManager em;
	
	@Inject
	private Logger log;



	@Override
	public Tramitacao insert(Tramitacao t) throws Exception {
		log.info("Persistindo "+t);
		em.persist(t);
		return t;
	}

	@Override
	public Tramitacao update(Tramitacao t) throws Exception {
		log.info("Atualizando "+t);
		em.merge(t);
		return t;
	}

	@Override
	public void delete(Tramitacao t) throws Exception {
		log.info("Removendo "+t);
		Object c = em.merge(t);
		em.remove(c);
	}

	@Override
	public Tramitacao find(Long k) throws Exception {
		log.info("Encontrando"+k);
		return em.find(Tramitacao.class, k);
	}

	@Override
	public List<Tramitacao> findAll() throws Exception {
		log.info("Encontrando todos as Tramitacoes");
		return em.createQuery("from Tramitacao").getResultList();
	}

	@Override
	public List<Tramitacao> findByName(String name) throws Exception {
		return findByEtapaProcesso(name);
	}
	public List<Tramitacao> findByEtapaProcesso(String etapaProcesso) throws Exception {
		log.info("Encontrando o "+etapaProcesso);
		return em.createNamedQuery("Tramitacao.findByEtapaProcesso")
				.setParameter("etapaProcesso", etapaProcesso+"%").getResultList();
	}

}
