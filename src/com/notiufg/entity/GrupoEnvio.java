package com.notiufg.entity;

public class GrupoEnvio {
	
	private Long id;
	private String nomeGrupoEnvio;
	private String ativo;
	//sqlite nao possui boolean ou bool, entao estou usando um int
	private int isPublico; // 0 nao eh publico, 1 eh publico
	private Long idCurso;
	
	public GrupoEnvio(Long idGrupoEnvio, String nomeGrupoEnvio, String ativo, int isPublico, Long idCurso) {
		super();
		this.id = idGrupoEnvio;
		this.nomeGrupoEnvio = nomeGrupoEnvio;
		this.ativo = ativo;
		this.isPublico = isPublico;
		this.idCurso = idCurso;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long idGrupoEnvio) {
		this.id = idGrupoEnvio;
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

	public int getIsPublico() {
		return isPublico;
	}

	public void setIsPublico(int isPublico) {
		this.isPublico = isPublico;
	}

	public Long getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
	}

}
