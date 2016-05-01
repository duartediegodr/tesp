package br.unibh.seguros.test;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import br.unibh.seguros.entidades.Cidade;
import br.unibh.seguros.util.Resources;

//@Ignore
@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteCidade {
	
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test3.war")
				.addClasses(Resources.class,Cidade.class)
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Inject
	private Logger log;
	@Inject
	private EntityManager em;
	@Inject
	protected UserTransaction utx;
	
	@Before
	public void preparePersistenceTest() throws Exception {
		utx.begin();
		em.joinTransaction();
	}

	@After
	public void commitTransaction() throws Exception {
		try {
			utx.commit();
		} catch (Exception e) {
		}
	}
	

	@Test
	public void teste01_inserirSemErro() throws Exception {
		log.info("============> Executando " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		Cidade cidade = new Cidade("MG","Belo Horizonte");
		
		em.persist(cidade);
		
		Cidade aux = (Cidade) em.createQuery("select o from Cidade o where o.cidade = :cidade")
				.setParameter("cidade", "Belo Horizonte").getSingleResult();
		assertNotNull(aux);
	}

	public void teste02_inserirComErro() throws Exception {
		log.info("============> Executando " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		try {
			Cidade cidade = new Cidade("mg","Ribeirão das Trevas");
		
			em.persist(cidade);
		}catch (Exception e) {
			
			assertTrue(checkString(e, "Deverá ter apenas letras Maiúsculas sem acento"));
		}
	}
	
	
	public void teste03_inserirComErro() throws Exception {
		log.info("============> Executando " + Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
			Cidade cidade = new Cidade("MG","asdddfajhjkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"
				+ "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
		
			em.persist(cidade);
		}catch (Exception e) {
			
			assertTrue(checkString(e, "tamanho deve ser menor que 100"));
		}
	}
	
	public void teste04_inserirComErro() throws Exception {
		log.info("============> Executando " + Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
			Cidade cidade = new Cidade("MG","asdddfajh46856787 865468");
		
			em.persist(cidade);
		}catch (Exception e) {
			
			assertTrue(checkString(e, "Deverá conter apenas Letras e Espaço"));
		}
	}

	@Test
	public void teste05_update() throws Exception {
		log.info("============> Executando " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		Cidade cidadeRecuperada = (Cidade) em.createQuery("select c from Cidade c where c.cidade = :cidade")
				.setParameter("cidade", "Belo Horizonte").getSingleResult();
		cidadeRecuperada.setCidade("Contagem");
			
		em.flush();
		
		assertEquals(em.createQuery("select c from Cidade c where c.cidade = :cidade")
				.setParameter("cidade", "Belo Horizonte").getResultList().size(), 0);
		
	}
	
	@Test
	public void teste06_Excluir() throws Exception {
		log.info("============> Executando " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		Cidade cidadeRecuperada = (Cidade) em.createQuery("select c from Cidade c where c.cidade = :cidade")
				.setParameter("cidade", "Contagem").getSingleResult();
		
			
		em.remove(cidadeRecuperada);
		em.flush();
		assertEquals(em.createQuery("select c from Cidade c where c.cidade = :cidade")
				.setParameter("cidade", "Contagem").getResultList().size(), 0);
		
	}

	
	private boolean checkString(Throwable e, String str) {
		if (e.getMessage().contains(str)) {
			return true;
		} else if (e.getCause() != null) {
			return checkString(e.getCause(), str);
		}
		return false;
	}	
	
	
	
	
	
	
	
	

}
