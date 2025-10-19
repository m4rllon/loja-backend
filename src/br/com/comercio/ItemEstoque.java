package br.com.comercio;

public class ItemEstoque {
	private static int numItensNoEstoque;
	private int id;
	private Estoque estoque;
	private Produto produto;
	private int quantidadeTotal;
	
	public ItemEstoque(Estoque estoque, Produto produto, int quantidadeTotal) {
		if(quantidadeTotal <= 0)
			throw new IllegalArgumentException("Invalid argument of 'quantidadeTotal'.");
		this.estoque = estoque;
		this.produto = produto;
		this.quantidadeTotal = quantidadeTotal;
		numItensNoEstoque++;
		this.id = numItensNoEstoque;
	}

	/**
	 * @return the estoque
	 */
	public Estoque getEstoque() {
		return estoque;
	}

	/**
	 * @param estoque the estoque to set
	 */
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	/**
	 * @return the produto
	 */
	public Produto getProduto() {
		return produto;
	}

	/**
	 * @param produto the produto to set
	 */
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	/**
	 * @return the quantidadeTotal
	 */
	public int getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public void addQuantidadeTotal(int quantidade) {
		if(quantidade <= 0)
			throw new IllegalArgumentException("Invalid argument of 'quantidade'.");
		this.quantidadeTotal += quantidade;
	}
	
	public void removeQuantidadeTotal(int quantidade) {
		if(quantidade <= 0 || quantidade > this.quantidadeTotal)
			throw new IllegalArgumentException("Invalid argument of 'quantidade'.");
		this.quantidadeTotal -= quantidade;
	}
}
