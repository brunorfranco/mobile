package com.notiufg.entity;


public class Notificacao {

	private Long id;
	private String nomeRemetente;
	private String texto;
	private String dataEnvio;
	
	public Notificacao(Long id, String nomeRemetente, String texto,
			String dataEnvio) {
		super();
		this.id = id;
		this.nomeRemetente = nomeRemetente;
		this.texto = texto;
		this.dataEnvio = dataEnvio;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeRemetente() {
		return nomeRemetente;
	}
	public void setNomeRemetente(String nomeRemetente) {
		this.nomeRemetente = nomeRemetente;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(String dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	
	
}
