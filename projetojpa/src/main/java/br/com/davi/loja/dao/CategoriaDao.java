package br.com.davi.loja.dao;

import br.com.davi.loja.model.Categoria;
import br.com.davi.loja.model.Produto;

import javax.persistence.EntityManager;

public class CategoriaDao {
    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Categoria categoria) {
        this.em.persist(categoria);
    }
}
