package br.com.drogaria.test;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.bin.ProdutoBin;

public class TestaProdutoBin {
	@Test
	@Ignore
	public void testaListar(){
		ProdutoBin bin = new ProdutoBin();
		bin.listar();
	}
	@Test
	public void testaPraparaNovo(){
		ProdutoBin bin = new ProdutoBin();
		bin.preparaNovo();
		System.out.println(bin.getComboFabricante());
	}
}
