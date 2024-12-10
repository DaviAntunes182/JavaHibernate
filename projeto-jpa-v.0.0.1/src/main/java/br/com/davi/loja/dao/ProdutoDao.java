package br.com.davi.loja.dao;

import br.com.davi.loja.model.Produto;

import javax.persistence.EntityManager;

public class ProdutoDao {
    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto){
        this.em.persist(produto);
    }
}
