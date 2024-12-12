package br.com.davi.loja.testes;

import br.com.davi.loja.dao.CategoriaDao;
import br.com.davi.loja.dao.ClienteDao;
import br.com.davi.loja.dao.PedidoDao;
import br.com.davi.loja.dao.ProdutoDao;
import br.com.davi.loja.modelo.*;
import br.com.davi.loja.util.JPAUtil;
import br.com.davi.loja.vo.RelatorioVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastrarPedido {
    public static void main(String[] args) {
        popularBanco();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);

        Produto produto1 = produtoDao.buscarPorId(1L);
        Produto produto2 = produtoDao.buscarPorId(2L);
        Produto produto3 = produtoDao.buscarPorId(3L);
        Cliente cliente = clienteDao.buscarPorId(1L);

        Pedido pedido = new Pedido(cliente);
        Pedido pedido2 = new Pedido(cliente);

        pedido.adicionarItem(new ItemPedido(produto1, pedido, 2));
        pedido.adicionarItem(new ItemPedido(produto2, pedido, 3));

        pedido2.adicionarItem(new ItemPedido(produto3, pedido2, 4));

        em.getTransaction().begin();

        pedidoDao.cadastrar(pedido);
        pedidoDao.cadastrar(pedido2);

        em.getTransaction().commit();
        BigDecimal total = pedidoDao.vendasTotais();
        System.out.println(total);

        List<RelatorioVendasVo> lista = pedidoDao.relatarVendas();
        for (RelatorioVendasVo elemento : lista) {
            System.out.println(elemento.toString());
        }

        em.close();
    }
private static void popularBanco(){
    Categoria celulares = new Categoria("celular");
    Categoria videogames = new Categoria("videogames");
    Categoria informatica = new Categoria("informatica");
    Produto celular = new Produto("Redmi", "Celular", new BigDecimal("800"), celulares);
    Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("2800"), videogames);
    Produto informatico = new Produto("Computador", "Computador", new BigDecimal("4800"), informatica);
    Cliente cliente = new Cliente("Davi", "12345");

    EntityManager em = JPAUtil.getEntityManager();
    CategoriaDao categoriaDao = new CategoriaDao(em);
    ProdutoDao produtoDao = new ProdutoDao(em);
    PedidoDao pedidoDao = new PedidoDao(em);
    ClienteDao clienteDao = new ClienteDao(em);

    em.getTransaction().begin();

    categoriaDao.cadastrar(celulares);
    categoriaDao.cadastrar(videogames);
    categoriaDao.cadastrar(informatica);

    produtoDao.cadastrar(celular);
    produtoDao.cadastrar(videogame);
    produtoDao.cadastrar(informatico);

    clienteDao.cadastrar(cliente);

    em.getTransaction().commit();
    em.close();
}
}

