package br.com.comercio;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class Comercio {

	public static void main(String[] args) {
		Produto arroz = new Produto(
				"Arroz Ceará 5kg",
				"Pacote de arroz marca Ceará de 5kg",
				"Cerará",
				new BigDecimal("24"),
				LocalDate.now(),
				LocalDate.now(),
				12341234);
		Produto acucar = new Produto(
				"Açucar Ceará 5kg",
				"Pacote de Açucar marca Ceará de 5kg",
				"Cerará",
				new BigDecimal("22.99"),
				LocalDate.now(),
				LocalDate.now(),
				12351235);
		
		Estoque estoque = new Estoque("Estoque para alimentos não perecíveis");
		estoque.addProduct(arroz, 135);
		estoque.addProduct(acucar, 125);
			
		Venda venda = new Venda(Instant.now(), "DÉBITO");
		
		venda.addProduto(
				estoque.getItemEstoqueById(1), 
				35, 
				new BigDecimal("19.38")
				);

// 		TESTE: Quando a quantidade de um item vendido muda, o valor total da venda deve mudar também.
//		System.out.println(venda.getValorTotal());
//		venda.setQtdItemVendaById(1, 5);
//		System.out.println(venda.getValorTotal());
		
//		TESTE: Quando os valores da venda de cada produto mudar, o preço total da venda deve mudar também:
//		System.out.println("Preço total inicial da venda: " + venda.getValorTotal());
//		System.out.println("Preço inicial do produto de id=1: " + venda.getItemVendaById(0).getPrecoDaVenda());
//		venda.setPrecoDaVendaDoProduto(1, new BigDecimal("10.58"));
//		System.out.println("Preço final do produto de id=1: " + venda.getItemVendaById(0).getPrecoDaVenda());
//		System.out.println("Novo preço total da venda: " + venda.getValorTotal());

//		TESTE: Quando a quantidade vendida de um item muda, a quantidade desse item em estoque deve mudar
		System.out.println("Quantidade inicial do item no estoque: " + estoque.getItemEstoqueById(1).getQuantidadeTotal());
		System.out.println("Quantidade vendida inicial: " + venda.getItemVendaById(1).getQuantidade());
		
		venda.setQtdItemVendaById(1, 55);
		
		System.out.println("Quantidade final do item no estoque: " + estoque.getItemEstoqueById(1).getQuantidadeTotal());
		System.out.println("Quantidade vendida final: " + venda.getItemVendaById(1).getQuantidade());
		
//		IMPLEMENTAR METODOS PARA ADICIONAR E REMOVER ITENS DA VENDA 
	}

}


















