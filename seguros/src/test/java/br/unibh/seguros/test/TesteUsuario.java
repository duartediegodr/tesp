package br.unibh.seguros.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

import br.unibh.seguros.entidades.Cidade;
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


@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteUsuario {

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test5.war")
				.addClasses(Resources.class,Usuario.class,Setor.class,Tramitacao.class, Dependente.class, Endereco.class, PessoaFisica.class, Proponente.class,
						Proposta.class, Questionario.class, Seguro.class, Tramitacao.class, 
						Vinculo.class, CharacterToBooleanUtil.class)
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
//		Setor aux = (Setor) em.createQuery("select o from Setor o where o.nome = :nome")
//				.setParameter("nome", "Tecnologia da Informação").getSingleResult();
		Usuario u = new Usuario("João da Silva", "joaosilva", "12345678", "Administrador", "Analista", "joao@gmail.com",
				new Date(), null);
		em.persist(u);
		Usuario aux2 = (Usuario) em.createQuery("select o from Usuario o where o.nome = :nome")
				.setParameter("nome", "João da Silva").getSingleResult();
		assertEquals(aux2.getNome(), "João da Silva");
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
	@Ignore
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
	@Ignore
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
