package br.com.alura.loja.modelo;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

public class Projeto {
	
	private Long id;
	private String nome;
	private Integer anoInicio;
		
	public Projeto() {}

	public Projeto(Long id, String nome, Integer anoInicio) {
		this.id = id;
		this.nome = nome;
		this.anoInicio = anoInicio;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Integer getAnoInicio() {
		return anoInicio;
	}
	
	public String toXML() {
		return new XStream().toXML(this);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String toJson() {
		return new Gson().toJson(this);
	}
}
