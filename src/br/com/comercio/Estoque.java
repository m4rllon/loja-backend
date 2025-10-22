package br.com.comercio;

import java.time.Instant;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Estoque {
	private static int numeroDeEstoques;
	private int id;
	private String descricao;
	private ArrayList<ItemEstoque> ItensEstoque;
	private Instant createdAt;
	private Instant updatedAt;
	
	public Estoque(
			String descricao, 
			ArrayList<ItemEstoque> ItensEstoque) {
		if(descricao.length() == 0)
			throw new IllegalArgumentException("Invalid argument of 'descricao'.");
		
		numeroDeEstoques++;
		this.id = numeroDeEstoques;
		this.descricao = descricao;
		this.ItensEstoque = ItensEstoque;
		this.createdAt = Instant.now();
		this.updatedAt = Instant.now();
	}
	
	public Estoque(String descricao) {
		if(descricao.length() == 0)
			throw new IllegalArgumentException("Invalid argument of 'descricao'.");
		
		numeroDeEstoques++;
		this.id = numeroDeEstoques;
		this.descricao = descricao;
		this.createdAt = Instant.now();
		this.updatedAt = Instant.now();
		this.ItensEstoque = new ArrayList<ItemEstoque>();
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
		setUpdatedAt();
	}
	
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		if(descricao.length() == 0)
			throw new IllegalArgumentException("Invalid argument of 'descricao'.");
		this.descricao = descricao;
		this.setUpdatedAt();
	}

	/**
	 * @return the ItemEstoques
	 */
	public ArrayList<ItemEstoque> getItemEstoques() {
		return ItensEstoque;
	}

	/**
	 * @param ItemEstoques the ItemEstoques to set
	 */
	public void setItemEstoques(ArrayList<ItemEstoque> ItemEstoques) {
		this.ItensEstoque = ItemEstoques;
		this.setUpdatedAt();
	}
	
	/**
	 * @return the ItemEstoque in ItemEstoques
	 */
	public ItemEstoque getItemEstoqueById(int id) {
		for(ItemEstoque item : this.ItensEstoque) {
			if(item.getId() == id) return item;
		}
		throw new IllegalArgumentException("Invalid argument of 'id'");			
	}
	
	public void addProduct(ItemEstoque ItemEstoque) {		
		this.ItensEstoque.add(ItemEstoque);
		this.setUpdatedAt();
	}
	
	//	FACTORY METHOD
	public void addProduct(Produto produto, int quantidade) {		
		ItemEstoque novoItem = new ItemEstoque(this, produto, quantidade);
		this.ItensEstoque.add(novoItem);
		this.setUpdatedAt();
	}
	
	public boolean removeProduct(int idItemEstoque) {
		try {
			this.ItensEstoque.remove(idItemEstoque);
			this.setUpdatedAt();
			return true;
		} catch (Exception e) {
			throw new NoSuchElementException("Unable to remove product. Invalid position: " + idItemEstoque);
		} finally {
			this.setUpdatedAt();
		}
	}
	
	public void setQuantidadeItemEstoque(int itemId, int quantidade) {
		ItemEstoque item = this.getItemEstoqueById(itemId);
		item.setQuantidade(quantidade);
	}
	
	/**
	 * @return the createdAt
	 */
	public Instant getCreatedAt() {
		return createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public Instant getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt() {
		this.updatedAt = Instant.now();
	}
}
