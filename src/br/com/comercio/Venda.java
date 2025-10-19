package br.com.comercio;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Venda {
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
		
		int valorTotal = 0;
		for(int i = 0; i < this.produtosVendidos.size(); i++) {
			valorTotal += produtosVendidos.get(i).getSubtotal().intValue();
		}
		
		this.valorTotal = new BigDecimal(valorTotal);
	}
	
	public Venda(Instant dataDaCompra, String formaDePagamento) {
		if(formaDePagamento.length() == 0)
			throw new IllegalArgumentException("Invalid argument of 'formaDePagamento'.");
		
		this.produtosVendidos = new ArrayList<ItemVenda>();
		this.dataDaCompra = dataDaCompra;
		this.formaDePagamento = formaDePagamento;
		
		int valorTotal = 0;
		for(int i = 0; i < this.produtosVendidos.size(); i++) {
			valorTotal += produtosVendidos.get(i).getSubtotal().intValue();
		}
		
		this.valorTotal = new BigDecimal(valorTotal);
	}
	
	
	
	public ItemVenda getProductById(int id) {
		if(id < 0 || id > this.getProdutosVendidos().size())
			throw new IllegalArgumentException("Invalid argument of 'id'");
		return this.getProdutosVendidos().get(id);
	}

	public boolean removeProduct(int id) {
		try {
			this.addQtdItemEstoque(
					this.produtosVendidos.get(id).getProdutoVendido(), 
					this.produtosVendidos.get(id).getQuantidade()
					);
			this.produtosVendidos.remove(id);
			return true;
		} catch (Exception e) {
			throw new NoSuchElementException("Unable to remove product. Invalid position: " + id);
		}
	}
	
	public void addProduct(ItemVenda novoItem) {
		this.removeQtdItemEstoque(novoItem.getProdutoVendido(), novoItem.getQuantidade());
		this.produtosVendidos.add(novoItem);
	}
	
	public void addProduct(ItemEstoque produto, int quantidaDeVendida, BigDecimal precoDaVenda) {
		ItemVenda novoItemVendido = new ItemVenda(produto, quantidaDeVendida, precoDaVenda, produto.getEstoque());
		this.removeQtdItemEstoque(novoItemVendido.getProdutoVendido(), novoItemVendido.getQuantidade());
		this.produtosVendidos.add(novoItemVendido);
	}
	
	private boolean removeQtdItemEstoque(ItemEstoque item, int quantidadeRetirada) {
		item.removeQuantidadeTotal(quantidadeRetirada);
		return true;
	}
	
	private boolean addQtdItemEstoque(ItemEstoque item, int quantidadeAdicionada) {
		item.addQuantidadeTotal(quantidadeAdicionada);;
		return true;
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
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
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
}
