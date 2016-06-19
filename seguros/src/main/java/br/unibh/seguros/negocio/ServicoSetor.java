package br.unibh.seguros.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.unibh.seguros.entidades.Setor;
import br.unibh.seguros.entidades.Usuario;

@Stateless
@LocalBean
public class ServicoSetor implements DAO<Setor, Long> {

	@Inject
	EntityManager em;
	
	@Inject
	private Logger log;



	@Override
	public Setor insert(Setor t) throws Exception {
		log.info("Persistindo " + t);
		em.persist(t);
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		if (t.getMembros() != null) {
			for (Usuario a : t.getMembros()) {
				a.setSetor(t);
				lista.add(em.merge(a));
			}
			t.setMembros(lista);
		}
		return t;
	}

	@Override
	public Setor update(Setor t) throws Exception {
		log.info("Atualizando " + t);
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		if (t.getMembros() != null) {
			for (Usuario o : t.getMembros()) {
				o.setSetor(t);
				lista.add(em.merge(o));
			}
			t.setMembros(lista);
		}
		// Exclusao
		Setor t2 = find(t.getId());
		if (t2.getMembros() != null) {
			for (Usuario o : t2.getMembros()) {
				if (!t.getMembros().contains(o))
					o.setSetor(null);
				em.merge(o);
			}
		}
		return em.merge(t);
	}

	@Override
	public void delete(Setor t) throws Exception {
		log.info("Removendo "+t);
		Object c = em.merge(t);
		em.remove(c);
	}

	@Override
	public Setor find(Long k) throws Exception {
		try {
			log.info("Encontrando" + k);
			return (Setor) em.createNamedQuery("Setor.findOneById").setParameter("id", k).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			/*Setor setor = em.find(Setor.class, k);
			setor.setMembros(new ArrayList<Usuario>());*/
			return null;
		}
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
