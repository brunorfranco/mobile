package com.notiufg.entity;

public class Curso {

	private Long id;
	private String nomeCurso;
	
	public Curso(Long id, String nomeCurso) {
		super();
		this.id = id;
		this.nomeCurso = nomeCurso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	
}
