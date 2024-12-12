package br.com.davi.loja.dao;

import javax.persistence.EntityManager;

import br.com.davi.loja.modelo.Categoria;
import br.com.davi.loja.modelo.Cliente;
import br.com.davi.loja.modelo.Produto;

import java.util.List;

public class CategoriaDao {
	
	private EntityManager em;

	public CategoriaDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Categoria categoria) {
		this.em.persist(categoria);
	}
	
	public void atualizar(Categoria categoria) {
		this.em.merge(categoria);
	}
	
	public void remover(Categoria categoria) {
		categoria = em.merge(categoria);
		this.em.remove(categoria);
	}

	public Categoria buscarPorId(Long id) {
		return em.find(Categoria.class, id);
	}

	public Categoria buscarPorNome(String nome) {
		String jpql = "SELECT c FROM Categoria c WHERE c.nome = :nome";
		return em.createQuery(jpql, Categoria.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}
}
