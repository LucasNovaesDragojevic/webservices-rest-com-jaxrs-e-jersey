package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.alura.loja.modelo.Projeto;

public class ProjetoTest {

	private HttpServer server;
	private Client client;
	private WebTarget target;
	
	@Before
	public void before() {
		server = Servidor.iniciaServidor();
		client = ClientBuilder.newClient();
		target = client.target("http://localhost:8080");
	}
	
	@After
	public void after() {
		server.stop();
	}

	@Test
	public void testaQueAConexaoComOServidorFunciona() {
		String conteudo = target.path("/projetos/1").request().get(String.class);
		System.out.println(conteudo);
		Assert.assertTrue(conteudo.contains("Itália"));
	}
	
	@Test
	public void deveSalvarCarrinhoEnviado() {
		Projeto projeto = new Projeto(2L, "Edifício Itália", 2021);
        String xml = projeto.toXML();
		
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
		
		Response response = target.path("/projetos").request().post(entity);
		
		Assert.assertEquals(201, response.getStatus());
		
		String location = response.getHeaderString("Location");
		String conteudo = client.target(location).request().get(String.class);
		
		Assert.assertTrue(conteudo.contains("Edifício"));
	}
}
