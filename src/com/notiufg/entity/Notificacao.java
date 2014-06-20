package com.notiufg.entity;


public class Notificacao {

	private Long id;
	private String nomeRemetente;
	private String texto;
	private String dataEnvio;
	private Long idGrupoEnvio;
	private int foiLida; // 0 nao foi lida, 1 foi lida
	
	public Notificacao(Long id, String nomeRemetente, String texto,
			String dataEnvio, Long idGrupoEnvio, int foiLida) {
		super();
		this.id = id;
		this.nomeRemetente = nomeRemetente;
		this.texto = texto;
		this.dataEnvio = dataEnvio;
		this.idGrupoEnvio = idGrupoEnvio;
		this.foiLida = foiLida;
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

	public Long getIdGrupoEnvio() {
		return idGrupoEnvio;
	}

	public void setIdGrupoEnvio(Long idGrupoEnvio) {
		this.idGrupoEnvio = idGrupoEnvio;
	}

	public int getFoiLida() {
		return foiLida;
	}

	public void setFoiLida(int foiLida) {
		this.foiLida = foiLida;
	}
	
}
