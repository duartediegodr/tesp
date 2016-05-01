package br.unibh.seguros.test;

import java.util.Date;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import br.unibh.seguros.util.Resources;

@Ignore
@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteSetor {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test2.war")
				.addClasses(Resources.class, Dependente.class, Endereco.class, PessoaFisica.class, Proponente.class,
						Proposta.class, Questionario.class, Seguro.class, Setor.class, Tramitacao.class, Usuario.class,
						Vinculo.class)
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
		Setor s = new Setor();
		s.setNome("Tecnologia da Informação");
		em.persist(s);
		Setor aux = (Setor) em.createQuery("select o from Setor o where o.nome = :nome")
				.setParameter("nome", "Tecnologia da Informação").getSingleResult();
		assertNotNull(aux);
	}

	@Test
	public void teste02_inserirComErroValidation1() throws Exception {
		log.info("============> Executando " + Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
			Setor s = new Setor();
			s.setNome("1828!!!");
			em.persist(s);
		} catch (Exception e) {
			assertTrue(checkString(e, "Favor fornecer apenas letras"));
		}
	}

	@Test
	public void teste03_inserirComErroValidation2() throws Exception {
		log.info("============> Executando " + Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
			Setor s = new Setor();
			s.setNome("As");
			em.persist(s);
		} catch (Exception e) {
			assertTrue(checkString(e, "tamanho deve estar entre 3 e 150"));
		}
	}

	@Test
	public void teste04_inserirComErroValidation3() throws Exception {
		log.info("============> Executando " + Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
			Setor s = new Setor();
			s.setNome(
					"0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567891");
			em.persist(s);
		} catch (Exception e) {
			assertTrue(checkString(e, "tamanho deve estar entre 3 e 150"));
		}
	}

	@Test
	public void teste05_atualizar() throws Exception {
		log.info("============> Executando " + Thread.currentThread().getStackTrace()[1].getMethodName());
		Setor s = (Setor) em.createQuery("select o from Setor o where o.nome = :nome")
				.setParameter("nome", "Tecnologia da Informação").getSingleResult();
		s.setNome("Tecnologia da Informação modificado");
		em.flush();
		Setor aux = (Setor) em.createQuery("select o from Setor o where o.nome = :nome")
				.setParameter("nome", "Tecnologia da Informação modificado").getSingleResult();
		assertNotNull(aux);
	}

	@Test
	public void teste06_excluir() throws Exception {
		log.info("============> Executando " + Thread.currentThread().getStackTrace()[1].getMethodName());
		Setor s = (Setor) em.createQuery("select o from Setor o where o.nome = :nome")
				.setParameter("nome", "Tecnologia da Informação modificado").getSingleResult();
		em.remove(s);
		em.flush();
		assertEquals(em.createQuery("select o from Setor o where o.nome = :nome")
				.setParameter("nome", "Tecnologia da Informação modificado").getResultList().size(), 0);
	}

	@Test
	public void teste07_incluirUsuario() throws Exception {
		log.info("============> Executando " + Thread.currentThread().getStackTrace()[1].getMethodName());
		Setor s = new Setor();
		s.setNome("Tecnologia da Informação");
		em.persist(s);
		Setor aux = (Setor) em.createQuery("select o from Setor o where o.nome = :nome")
				.setParameter("nome", "Tecnologia da Informação").getSingleResult();
		Usuario u = new Usuario("João da Silva", "joaosilva", "12345678", "Administrador", "Analista", "joao@gmail.com",
				new Date(), aux);
		em.persist(u);
		Usuario aux2 = (Usuario) em.createQuery("select o from Usuario o where o.nome = :nome")
				.setParameter("nome", "João da Silva").getSingleResult();
		assertEquals(aux2.getSetor().getNome(), "Tecnologia da Informação");
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