package br.com.comercio;

import java.math.BigDecimal;

public class ItemVenda {
	private Estoque estoqueDoProduto;
	private ItemEstoque produtoVendido;
	private int quantidade;
	private BigDecimal precoDaVenda;
	
	public ItemVenda(ItemEstoque produtoVendido, int quantidade, BigDecimal precoDaVenda, Estoque estoqueDoProduto){
		if(quantidade <= 0)
			throw new IllegalArgumentException("Invalid argument of 'quantidade'.");
		if(precoDaVenda.intValue() <= 0)
			throw new IllegalArgumentException("Invalid argument of 'precoDaVenda'.");
		
		this.produtoVendido = produtoVendido;
		this.quantidade = quantidade;
	}
	
	
	public BigDecimal getSubtotal() {
		BigDecimal subtotal = new BigDecimal(this.getQuantidade()*this.getPrecoDaVenda().intValue());
		return subtotal;
	}

	
	/**
	 * @return the precoDaVenda
	 */
	public BigDecimal getPrecoDaVenda() {
		return precoDaVenda;
	}

	/**
	 * @param precoDaVenda the precoDaVenda to set
	 */
	public void setPrecoDaVenda(BigDecimal precoDaVenda) {
		if(precoDaVenda.intValue() <= 0)
			throw new IllegalArgumentException("Invalid argument of 'precoDaVenda'.");
		this.precoDaVenda = precoDaVenda;
	}

	/**
	 * @return the produtoVendido
	 */
	public ItemEstoque getProdutoVendido() {
		return produtoVendido;
	}

	/**
	 * @param produtoVendido the produtoVendido to set
	 */
	public void setProdutoVendido(ItemEstoque produtoVendido) {
		this.produtoVendido = produtoVendido;
	}

	/**
	 * @return the quantidade
	 */
	public int getQuantidade() {
		return quantidade;
	}

	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(int quantidade) {
		if(quantidade <= 0)
			throw new IllegalArgumentException("Invalid argument of 'quantidad'.");
		this.quantidade = quantidade;
	}
	
	
}
