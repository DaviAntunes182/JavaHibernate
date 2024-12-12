package br.com.davi.loja.dao;

import br.com.davi.loja.modelo.Cliente;
import br.com.davi.loja.modelo.Produto;

import javax.persistence.EntityManager;

public class ClienteDao {

	private EntityManager em;

	public ClienteDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Cliente Cliente) {
		this.em.persist(Cliente);
	}
	
	public void atualizar(Cliente Cliente) {
		this.em.merge(Cliente);
	}
	
	public void remover(Cliente Cliente) {
		Cliente = em.merge(Cliente);
		this.em.remove(Cliente);
	}

	public Cliente buscarPorId(Long id) {
		return em.find(Cliente.class, id);
	}

	public Cliente buscarPorNome(String nome) {
		String jpql = "SELECT c FROM Cliente c WHERE c.nome = :nome";
		return em.createQuery(jpql, Cliente.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}
}
