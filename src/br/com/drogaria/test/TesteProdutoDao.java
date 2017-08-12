package br.com.drogaria.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

import com.br.drogaria.dao.ProdutoDAO;

public class TesteProdutoDao {
	
	private Fabricante fabricante;
	private Produto produto;
	private ProdutoDAO dao;
	
	
	
	public Fabricante getFabricante() {
		if(fabricante == null){
			fabricante = new Fabricante();
		}
		return fabricante;
	}



	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}



	public Produto getProduto() {
		if(produto == null){
			produto = new Produto();
		}
		return produto;
		
	}



	public void setProduto(Produto produto) {
		this.produto = produto;
	}



	public ProdutoDAO getDao() {
		if (dao == null) 
			dao = new ProdutoDAO();
		return dao;
	}



	public void setDao(ProdutoDAO dao) {
		this.dao = dao;
	}



	@Test
	@Ignore
	public void testaListar() throws SQLException {
		

		ArrayList<Produto> lista = getDao().listar();
		for (Produto produto : lista) {
			System.out.println(produto);
		}

	}
	
	
	@Test
	@Ignore
	public void testaEditar() throws SQLException{
		getProduto().setCodigo(1);
		getProduto().setDescricao("Zenfone zoom");
		getProduto().setQuantidade(2);
		
	
		getDao().editar(produto);
	
	}
	
	@Test
	@Ignore
	public void testaSalvar() throws SQLException{
		getFabricante().setCodigo(8);
		
		getProduto().setDescricao("Cruzeiro");
		getProduto().setPreco(900.0);
		getProduto().setQuantidade(1);
		getProduto().setFabricante(getFabricante());
		
		getDao().salvar(getProduto());
		System.out.println("salvo");
		
		
	}
	@Test
	@Ignore
	public void testaDeletar() throws SQLException{
		getDao().deletar(4);
	}
	@Test
	public void testaposicao() throws SQLException{//quem chamou esse metodos ? kk ninguém  o erro não será impresso o JUnit te mostra o erro mas não na forma que fica didatica
		
		Produto lista = getDao().findById(1);// opa, alguém mandou uma exessão pra mim, mas pera tem um throws em mi, isso quer dizser que tenho que mandar para que me chamou
		
		System.out.println(lista);

	}
}


