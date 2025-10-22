package br.com.comercio;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Venda {
	private int id;
	private ArrayList<ItemVenda> produtosVendidos;
	private Instant dataDaCompra;
	private BigDecimal valorTotal;
	private String formaDePagamento;
	
	public Venda(ArrayList<ItemVenda> produtosVendidos, Instant dataDaCompra, String formaDePagamento) {
		if(produtosVendidos.size() == 0)
			throw new IllegalArgumentException("Invalid argument of 'produtosVendidos'.");
		if(formaDePagamento.length() == 0)
			throw new IllegalArgumentException("Invalid argument of 'formaDePagamento'.");
		this.produtosVendidos = produtosVendidos;
		this.dataDaCompra = dataDaCompra;
		this.formaDePagamento = formaDePagamento;
		this.setValorTotal();
	}
	
	public Venda(Instant dataDaCompra, String formaDePagamento) {
		if(formaDePagamento.length() == 0)
			throw new IllegalArgumentException("Invalid argument of 'formaDePagamento'.");
		this.produtosVendidos = new ArrayList<ItemVenda>();
		this.dataDaCompra = dataDaCompra;
		this.formaDePagamento = formaDePagamento;
		this.valorTotal = BigDecimal.ZERO;
	}
	
//	ADICIONAR RECURSO DE RETIRAR QUANTIDADE DO PRODUTO NO ESTOQUE
	public void addProduto(ItemVenda novoItem) {
		this.produtosVendidos.add(novoItem);
	}
	public void addProduto(ItemEstoque item, int quantidadeVendida, BigDecimal precoDaVenda) {
		ItemVenda novoItem = new ItemVenda(item, quantidadeVendida, precoDaVenda, item.getEstoque());
		this.produtosVendidos.add(novoItem);
	}
	
//	ADICIONAR RECURSO DE ADICIONAR QUANTIDADE DO PRODUTO NO ESTOQUE
	public boolean removeProduto(int itemId) {
		for(ItemVenda item : this.produtosVendidos) {
			if(itemId == item.getId()) this.produtosVendidos.remove(item);
		}
		return true;
	}
	
	
	public void setQtdItemVendaById(int itemId, int quantidade) {
		this.setNewQtdEstoque(itemId, quantidade);
		this.getItemVendaById(itemId).setQuantidade(quantidade);
		this.setValorTotal();		
	}
	
	public void setNewQtdEstoque(int itemVendaId, int quantidade) {
		int quantidadeVendidaInicial = this.getItemVendaById(itemVendaId).getQuantidade();
		System.out.println(this.getItemVendaById(itemVendaId).toString());
		Estoque estoque = this.getItemVendaById(itemVendaId).getEstoqueDoProduto();
		int idItemEstoque = itemVendaId;
		ItemEstoque itemEstoque = this.getItemVendaById(itemVendaId).getProdutoVendido();
//		o método precisa receber a quantidade certa que precisa estar no estoque
		int novaQuantidadeNoEstoque = itemEstoque.getQuantidadeTotal() + (quantidadeVendidaInicial - quantidade);
		estoque.setQuantidadeItemEstoque(idItemEstoque, novaQuantidadeNoEstoque);		
	}
	
	public ItemVenda getItemVendaById(int itemId) {
		for(ItemVenda item : this.produtosVendidos) {
			if(item.getProdutoVendido().getId() == itemId) return item;
		}
		throw new IllegalArgumentException("Invalid argument of 'itemId'.");
	}
	
	public boolean setPrecoDaVendaDoProduto(int itemId, BigDecimal novoPreco) {
		try {
			this.getItemVendaById(itemId).setPrecoDaVenda(novoPreco);
			this.setValorTotal();
			return true;
		} catch(Exception e) {
			throw new IllegalArgumentException("Invalid argument of 'itemId' or 'novoPreco'.");
		}
	}
	
	/**
	 * @return the produtosVendidos
	 */
	public ArrayList<ItemVenda> getProdutosVendidos() {
		return produtosVendidos;
	}

	/**
	 * @param produtosVendidos the produtosVendidos to set
	 */
	public void setProdutosVendidos(ArrayList<ItemVenda> produtosVendidos) {
		this.produtosVendidos = produtosVendidos;
		this.setValorTotal();
	}

	/**
	 * @return the dataDaCompra
	 */
	public Instant getDataDaCompra() {
		return dataDaCompra;
	}

	/**
	 * @param dataDaCompra the dataDaCompra to set
	 */
	public void setDataDaCompra(Instant dataDaCompra) {
		this.dataDaCompra = dataDaCompra;
	}

	/**
	 * @return the valorTotal
	 */
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal() {
		BigDecimal newValorTotal = BigDecimal.ZERO;
		for(ItemVenda item : this.produtosVendidos) {
			BigDecimal subtotalItem = item.getSubtotal();
			newValorTotal = newValorTotal.add(subtotalItem);
		}
		this.valorTotal = newValorTotal;
	}

	/**
	 * @return the formaDePagamento
	 */
	public String getFormaDePagamento() {
		return formaDePagamento;
	}

	/**
	 * @param formaDePagamento the formaDePagamento to set
	 */
	public void setFormaDePagamento(String formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	@Override
	public String toString() {
		return "Venda [produtosVendidos=" + produtosVendidos + ", dataDaCompra=" + dataDaCompra + ", valorTotal="
				+ this.getValorTotal()+ ", formaDePagamento=" + formaDePagamento + "]";
	}
}
