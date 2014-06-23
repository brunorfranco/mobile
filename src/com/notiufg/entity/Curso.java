package com.notiufg.entity;

public class Curso {

	private Long id;
	private String nomeCurso;
	private int idExterno;
	
	public Curso(Long id, String nomeCurso, int idExterno) {
		super();
		this.id = id;
		this.nomeCurso = nomeCurso;
		this.idExterno = idExterno;
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

	public int getIdExterno() {
		return idExterno;
	}

	public void setIdExterno(int idExterno) {
		this.idExterno = idExterno;
	}
	
}
