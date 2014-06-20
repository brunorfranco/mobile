package com.notiufg.entity;


public class Configuracao {

	private Long id;
	private Long idUsuario;
	//vou separar os ids dos grupos de envio para esse usuario por ponto-e-virgula, nao sei fazer
	//o mapeamento no sqlLite
	private String idsGruposEnvio;
	
	public Long getId() {
		return id;
	}

	public void setId(Long idConfiguracao) {
		this.id = idConfiguracao;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdsGruposEnvio() {
		return idsGruposEnvio;
	}

	public void setIdsGruposEnvio(String idsGruposEnvio) {
		this.idsGruposEnvio = idsGruposEnvio;
	}

	public Configuracao(Long idConfiguracao, Long idUsuario,
			String idsGruposEnvio) {
		super();
		this.id = idConfiguracao;
		this.idUsuario = idUsuario;
		this.idsGruposEnvio = idsGruposEnvio;
	}
	
}
