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
		estoque.addProduct(arroz, 35);
		estoque.addProduct(acucar, 25);
		System.out.println(estoque.toString());
		
		System.out.println("Quantidade do item: " + estoque.getItemEstoqueById(0).getQuantidadeTotal());
		Venda venda = new Venda(Instant.now(), "DÉBITO");
		venda.addProduct(estoque.getItemEstoqueById(0), 35, new BigDecimal("24.99"));
		System.out.println("Nova quantidade do item após a venda: " + estoque.getItemEstoqueById(0).getQuantidadeTotal());
		venda.removeProduct(0);
		System.out.println("Nova quantidade do item após o cancelamento da venda: " + estoque.getItemEstoqueById(0).getQuantidadeTotal());
	}

}
