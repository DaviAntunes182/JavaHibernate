package br.com.davi.loja.modelo;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORIAS")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	public Categoria() {
	}
	
	public Categoria(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
