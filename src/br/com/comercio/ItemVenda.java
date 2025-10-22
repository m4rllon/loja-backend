package br.com.comercio;

import java.math.BigDecimal;

public class ItemVenda {
	private int id;
	private Estoque estoqueDoProduto;
	private ItemEstoque produtoVendido;
	private int quantidade;
	private BigDecimal precoDaVenda;
	private BigDecimal subtotal;
	
	public ItemVenda(ItemEstoque produtoVendido, int quantidade, BigDecimal precoDaVenda, Estoque estoqueDoProduto){
		if(quantidade <= 0)
			throw new IllegalArgumentException("Invalid argument of 'quantidade'.");
		if(precoDaVenda.intValue() <= 0)
			throw new IllegalArgumentException("Invalid argument of 'precoDaVenda'.");
		
		this.produtoVendido = produtoVendido;
		this.quantidade = quantidade;
		this.precoDaVenda = precoDaVenda;
		this.subtotal = this.precoDaVenda.multiply(new BigDecimal(String.valueOf(this.quantidade)));
		this.id = produtoVendido.getId();
		this.estoqueDoProduto= estoqueDoProduto;
	}
	
	public ItemVenda(ItemEstoque produtoVendido, int quantidade, Estoque estoqueDoProduto){
		if(quantidade <= 0)
			throw new IllegalArgumentException("Invalid argument of 'quantidade'.");
		if(precoDaVenda.intValue() <= 0)
			throw new IllegalArgumentException("Invalid argument of 'precoDaVenda'.");
		
		this.produtoVendido = produtoVendido;
		this.quantidade = quantidade;
		this.precoDaVenda = produtoVendido.getProduto().getPrecoAsBigDecimal();
		this.subtotal = this.precoDaVenda.multiply(new BigDecimal(String.valueOf(this.quantidade)));
		this.id = produtoVendido.getId();
		this.estoqueDoProduto= estoqueDoProduto;
	}
	
	/**
	 * @param subtotal the subtotal to set
	 */
	protected void setSubtotal() {
		this.subtotal = this.precoDaVenda.multiply(
				new BigDecimal(String.valueOf(this.quantidade))
				);		
	}
	
	/**
	 * @return the subtotal
	 */
	public BigDecimal getSubtotal() {
		return this.subtotal;
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
	protected void setPrecoDaVenda(BigDecimal precoDaVenda) {
		if(precoDaVenda.intValue() <= 0)
			throw new IllegalArgumentException("Invalid argument of 'precoDaVenda'.");
		this.precoDaVenda = precoDaVenda;
		this.setSubtotal();
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
	protected void setProdutoVendido(ItemEstoque produtoVendido) {
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
	protected void setQuantidade(int quantidade) {
		if(quantidade <= 0)
			throw new IllegalArgumentException("Invalid argument of 'quantidad'.");
		this.quantidade = quantidade;
		this.setSubtotal();
	}

	/**
	 * @return the estoqueDoProduto
	 */
	public Estoque getEstoqueDoProduto() {
		return estoqueDoProduto;
	}

	/**
	 * @param estoqueDoProduto the estoqueDoProduto to set
	 */
	protected void setEstoqueDoProduto(Estoque estoqueDoProduto) {
		this.estoqueDoProduto = estoqueDoProduto;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ItemVenda [id=" + id + ", estoqueDoProduto=" + ", produtoVendido=" + produtoVendido
				+ ", quantidade=" + quantidade + ", precoDaVenda=" + precoDaVenda + ", subtotal=" + subtotal + "]";
	}
	
	
}
