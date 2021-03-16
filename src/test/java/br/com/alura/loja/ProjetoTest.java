package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProjetoTest {

	private HttpServer server;
	
	@Before
	public void before() {
		server = Servidor.iniciaServidor();
	}
	
	@After
	public void after() {
		server.stop();
	}

	@Test
	public void testaQueAConexaoComOServidorFunciona() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		String conteudo = target.path("/projetos/1").request().get(String.class);
		System.out.println(conteudo);
		Assert.assertTrue(conteudo.contains("Itália"));
	}
}
