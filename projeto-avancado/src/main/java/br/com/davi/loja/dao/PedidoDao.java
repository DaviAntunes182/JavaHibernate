package br.com.davi.loja.dao;

import br.com.davi.loja.modelo.Pedido;
import br.com.davi.loja.vo.RelatorioVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class PedidoDao {

	private EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Pedido Pedido) {
		this.em.persist(Pedido);
	}
	
	public void atualizar(Pedido Pedido) {
		this.em.merge(Pedido);
	}
	
	public void remover(Pedido Pedido) {
		Pedido = em.merge(Pedido);
		this.em.remove(Pedido);
	}

	public Pedido buscarPorId(Long id) {
		return em.find(Pedido.class, id);
	}

	public BigDecimal vendasTotais(){
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}

//	public List<Object[]> relatarVendas(){
//		String jpql = "SELECT produto.nome, " +
//						"SUM(item.quantidade), " +
//						"MAX(p.data) FROM Pedido p " +
//						"JOIN p.itens item " +
//						"JOIN item.produto produto " +
//						"GROUP BY produto.nome " +
//						"ORDER BY SUM(item.quantidade) DESC";
//		return em.createQuery(jpql, Object[].class).getResultList();
//	}
	public List<RelatorioVendasVo> relatarVendas(){
		String jpql = "SELECT new br.com.davi.loja.vo.RelatorioVendasVo(" +
						"produto.nome, " +
						"SUM(item.quantidade), " +
						"MAX(p.data)) " +
						"FROM Pedido p " +
						"JOIN p.itens item " +
						"JOIN item.produto produto " +
						"GROUP BY produto.nome " +
						"ORDER BY SUM(item.quantidade) DESC";
		return em.createQuery(jpql, RelatorioVendasVo.class).getResultList();
	}

}
