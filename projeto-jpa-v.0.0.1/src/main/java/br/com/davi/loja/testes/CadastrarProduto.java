package br.com.davi.loja.testes;

import br.com.davi.loja.dao.ProdutoDao;
import br.com.davi.loja.model.Categoria;
import br.com.davi.loja.model.Produto;
import br.com.davi.loja.util.JpaUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastrarProduto {
    public static void main(String[] args) {
        Produto celular = new Produto(
                "Redmi",
                "Celular",
                new BigDecimal("1200"),
                Categoria.CELULARES
                );

        EntityManager em = JpaUtil.getEntityManager();
        ProdutoDao pd = new ProdutoDao(em);

        em.getTransaction().begin();
        pd.cadastrar(celular);
        em.getTransaction().commit();
        em.close();
    }
}
