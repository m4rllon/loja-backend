package br.com.comercio;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Month;

public class Produto {
	private static int numeroDeProdutos;
	private int id;
	private int code;
	private String nome;
	private String descricao;
	private String marca;
	private int quantidade;
	private int preco;
	private LocalDate dataFabricacao;
	private LocalDate dataVencimento;
	private Instant createdAt;
	private Instant updatedAt;
	private ProductStatus status;
	
	public Produto(
			String nome, 
			String descricao, 
			String marca, 
			BigDecimal preco, 
			LocalDate dataFabricacao,
			LocalDate dataVencimento,
			int code) {
		if(dataVencimento.isBefore(LocalDate.now()) || dataFabricacao.isAfter(LocalDate.now()))
			throw new DateTimeException("Invalid value for dataFabricação or dataVencimento.");
		if(preco.intValue() <= 0)
			throw new IllegalArgumentException("Invalid value of 'quantidade' or 'preco'.");
		
		this.nome = nome;
		this.descricao = descricao;
		this.marca = marca;
		this.dataFabricacao = dataFabricacao;
		this.dataVencimento = dataVencimento;
		this.createdAt = Instant.now();
		this.updatedAt = Instant.now();
		this.code = code;
		numeroDeProdutos++;
		this.id = numeroDeProdutos;	
		this.setPreco(preco);		
		this.setStatus();
	}
	
	public Produto(
			String nome, 
			String descricao, 
			String marca, 
			BigDecimal preco,
			int code) {
		if(preco.intValue() <= 0)
			throw new IllegalArgumentException("Invalid value of 'quantidade' or 'preco'.");
		
		this.nome = nome;
		this.descricao = descricao;
		this.marca = marca;
		this.dataFabricacao = null;
		this.dataVencimento = null;
		this.createdAt = Instant.now();
		this.updatedAt = Instant.now();
		this.code = code;
		numeroDeProdutos++;
		this.id = numeroDeProdutos;
		this.setPreco(preco);		
		this.setStatus();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		if(nome.length() == 0)
			throw new IllegalArgumentException("Invalid argument of 'nome'.");
		this.nome = nome;
		this.setUpdatedAt();
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
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		if(marca.length() == 0) 
			throw new IllegalArgumentException("Invalid argument of 'marca'.");
		this.marca = marca;
		this.setUpdatedAt();
	}

	/**
	 * @return the preco as BigDecimal
	 */
	public BigDecimal getPrecoAsBigDecimal() {
		return new BigDecimal(this.preco).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
	}
	
	/**
	 * @return the preco as BigDecimal
	 */
	public double getPrecoAsDoouble() {
		return this.getPrecoAsBigDecimal().doubleValue();
	}

	/**
	 * @param preco the preco to set
	 */
	public void setPreco(BigDecimal preco) {
		if(preco.intValue() <= 0)
			throw new IllegalArgumentException("Invalid argument of 'preco'.");
		
		this.preco = preco.multiply(new BigDecimal("100")).intValue();
		this.setUpdatedAt();
	}
	
	/**
	 * @param preco the preco to set
	 */
	public void setPreco(double preco) {
		if(preco <= 0) 
			throw new IllegalArgumentException("Invalid argument of 'preco'.");
		
		this.setPreco(new BigDecimal(String.valueOf(preco)));
		this.setUpdatedAt();
	}

	/**
	 * @return the dataFabricacao
	 */
	public LocalDate getDataFabricacao() {
		return dataFabricacao;
	}

	/**
	 * @param dataFabricacao the dataFabricacao to set
	 */
	public void setDataFabricacao(LocalDate dataFabricacao) {
		if(dataFabricacao.isAfter(LocalDate.now()))
			throw new DateTimeException("Invalid value for dataFabricação.");
		this.dataFabricacao = dataFabricacao;
		this.setUpdatedAt();
	}
	
	/**
	 * @param dataFabricacao the dataFabricacao to set
	 */
	public void setDataFabricacao(int year, int mounth, int day) {
		this.setDataFabricacao(LocalDate.of(year, Month.of(mounth), day));
	}

	/**
	 * @return the dataVencimento
	 */
	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	/**
	 * @param dataVencimento the dataVencimento to set
	 */
	public void setDataVencimento(LocalDate dataVencimento) {
		if(dataVencimento.isBefore(LocalDate.now()))
			throw new DateTimeException("Invalid value of dataVencimento.");
		this.dataVencimento = dataVencimento;
		this.setUpdatedAt();
	}
	
	/**
	 * @param dataVencimento the dataVencimento to set
	 */
	public void setDataVencimento(int year, int mounth, int day) {
		this.setDataVencimento(LocalDate.of(year, mounth, day));
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
	
	/**
	 * @return the createdAt
	 */
	public Instant getCreatedAt() {
		return this.createdAt;
	}
	
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
		this.setUpdatedAt();
	}
	
	/**
	 * @return the numeroDeProdutos
	 */
	public int getNumOfProducts() {
		return numeroDeProdutos;
	}
	
	/**
	 * @return the status
	 */
	public ProductStatus getStatus() {
		return this.status;
	}
	
	/**
	 * @param set product status
	 */
	private void setStatus() {
		if(this.dataVencimento.isAfter(LocalDate.now()))
			this.status = ProductStatus.VENCIDO;
		else 
			this.status = ProductStatus.VALIDO;
		
		this.setUpdatedAt();
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", code=" + code + ", nome=" + nome + ", descricao=" + descricao + ", marca="
				+ marca + ", preco=" + this.getPrecoAsDoouble() + ", dataFabricacao=" + dataFabricacao + ", dataVencimento="
				+ dataVencimento + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", status=" + status + "]";
	}
}
