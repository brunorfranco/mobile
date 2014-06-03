package com.notiufg.entity;

public class GrupoEnvio {
	
	private Long idGrupoEnvio;
	private String nomeGrupoEnvio;
	private String ativo;
	
	public GrupoEnvio(Long idGrupoEnvio, String nomeGrupoEnvio, String ativo) {
		super();
		this.idGrupoEnvio = idGrupoEnvio;
		this.nomeGrupoEnvio = nomeGrupoEnvio;
		this.ativo = ativo;
	}
	
	public Long getIdGrupoEnvio() {
		return idGrupoEnvio;
	}
	public void setIdGrupoEnvio(Long idGrupoEnvio) {
		this.idGrupoEnvio = idGrupoEnvio;
	}
	public String getNomeGrupoEnvio() {
		return nomeGrupoEnvio;
	}
	public void setNomeGrupoEnvio(String nomeGrupoEnvio) {
		this.nomeGrupoEnvio = nomeGrupoEnvio;
	}
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
}
