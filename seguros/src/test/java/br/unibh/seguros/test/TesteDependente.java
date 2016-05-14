package br.unibh.seguros.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;
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

import br.unibh.seguros.entidades.Dependente;
import br.unibh.seguros.entidades.Endereco;
import br.unibh.seguros.entidades.PessoaFisica;
import br.unibh.seguros.entidades.Proponente;
import br.unibh.seguros.entidades.Proposta;
import br.unibh.seguros.entidades.Questionario;
import br.unibh.seguros.entidades.Seguro;
import br.unibh.seguros.entidades.Setor;
import br.unibh.seguros.entidades.Tramitacao;
import br.unibh.seguros.entidades.Usuario;
import br.unibh.seguros.entidades.Vinculo;
import br.unibh.seguros.util.CharacterToBooleanUtil;
import br.unibh.seguros.util.Resources;

@Ignore
@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteDependente {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test4.war")
				.addClasses(Resources.class, Dependente.class, Endereco.class, PessoaFisica.class, Proponente.class,
						Proposta.class, Questionario.class, Seguro.class, Tramitacao.class, Usuario.class,
						Vinculo.class, Setor.class,CharacterToBooleanUtil.class)
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
		
		Proponente proponente = new Proponente("Itamar Viana", "12345678945", "(31)98564-8978", 50,
				new Date(), "457878", new Date(), "Aberto", "Aberto", null, null, null, null);
		em.persist(proponente);
		em.flush();
		//em.refresh(proponente);
		
		Dependente d = new Dependente("Diego Duarte", "08088899944", 23, 
				"(31)97892-4578", "Filho", new BigDecimal(2.0),
				true, new Date(), proponente);
		em.persist(d);
		//em.flush();
		Dependente aux = (Dependente) em.createQuery("select o from Dependente o where o.nome = :nome")
				.setParameter("nome", "Diego Duarte").getSingleResult();
		assertTrue(aux.getProponente().getNome().equals("Itamar Viana"));
	}

	@Test
	public void teste02_inserirComErroValidation1() throws Exception {
		log.info("============> Executando " + Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
			Proponente proponente = (Proponente) em.createQuery("select o from Proponente o where o.nome = :nome")
					.setParameter("nome", "Itamar Viana").getSingleResult(); 
			
			Dependente d = new Dependente("Pris !!!!**80***6587", "08088899944", 23, 
					"(31)97892-4578", "Filho", new BigDecimal(2.0),
					true, new Date(), proponente);
			em.persist(d);
			
		} catch (Exception e) {
			assertTrue(checkString(e, "Deverá ter apenas Letras e Espaço"));
		}
	}

	@Test
	public void teste03_inserirComErroValidation2() throws Exception {
		log.info("============> Executando " + Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
			Proponente proponente = (Proponente) em.createQuery("select o from Proponente o where o.nome = :nome")
					.setParameter("nome", "Itamar Viana").getSingleResult(); 
			
			Dependente d = new Dependente("Priscila ", "08088899944", 23, 
					"(31)97892-4578", "Filho", new BigDecimal(20000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0),
					true, new Date(), proponente);
			
			em.persist(d);
			
		} catch (Exception e) {
			assertTrue(checkString(e, "Valor deve ser menor ou igual a 100.0 e maior ou igual que 0.0"));
		}
	}
	
	@Test
	public void teste04_atualizar() throws Exception {
		log.info("============> Executando " + Thread.currentThread().getStackTrace()[1].getMethodName());
		Dependente s = (Dependente) em.createQuery("select o from Dependente o where o.nome = :nome")
				.setParameter("nome", "Diego Duarte").getSingleResult();
		s.setPercentualBeneficio(new BigDecimal(20.0));
		em.flush();
		Dependente aux = (Dependente) em.createQuery("select o from Dependente o where o.nome = :nome")
				.setParameter("nome", "Diego Duarte").getSingleResult();
		assertTrue(aux.getPercentualBeneficio().doubleValue()== 20.0);
	}
	@Test
	public void teste05_excluir() throws Exception {
		log.info("============> Executando " + Thread.currentThread().getStackTrace()[1].getMethodName());
		Dependente s = (Dependente) em.createQuery("select o from Dependente o where o.nome = :nome")
				.setParameter("nome", "Diego Duarte").getSingleResult();
		em.remove(s);
		em.flush();
		assertEquals(em.createQuery("select o from Dependente o where o.nome = :nome")
				.setParameter("nome", "Diego Duarte").getResultList().size(), 0);
	}
	
	@Test
	public void teste06_excluirProponente() throws Exception {
		log.info("============> Executando " + Thread.currentThread().getStackTrace()[1].getMethodName());
		Proponente s = (Proponente) em.createQuery("select o from Proponente o where o.nome = :nome")
				.setParameter("nome", "Itamar Viana").getSingleResult();
		em.remove(s);
		em.flush();
		assertEquals(em.createQuery("select o from Proponente o where o.nome = :nome")
				.setParameter("nome", "Itamar Viana").getResultList().size(), 0);
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
