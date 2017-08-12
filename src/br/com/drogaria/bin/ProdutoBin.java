package br.com.drogaria.bin;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

import com.br.drogaria.dao.FabricanteDAO;
import com.br.drogaria.dao.ProdutoDAO;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBin {
	private ArrayList<Produto> itens;
	private ArrayList<Produto> itemFiltrado;
	private Produto produto;
	private Fabricante fabricante;
	private ProdutoDAO produtoDao;
	private String codigo;
	private ArrayList<Fabricante> comboFabricante;

	public Produto getProduto() {
		if (produto == null) {
			produto = new Produto();
			return produto;
		}
		return produto;
	}

	public ArrayList<Produto> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ProdutoDAO getProdutoDao() {
		if (produtoDao == null) {
			produtoDao = new ProdutoDAO();
			return produtoDao;
		}
		return produtoDao;
	}

	public ArrayList<Produto> getItemFiltrado() {
		return itemFiltrado;
	}

	public void setItemFiltrado(ArrayList<Produto> itemFiltrado) {
		this.itemFiltrado = itemFiltrado;
	}

	public void setProdutoDao(ProdutoDAO produtoDao) {
		this.produtoDao = produtoDao;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	

	public ArrayList<Fabricante> getComboFabricante() {
		return comboFabricante;
	}

	public void setComboFabricante(ArrayList<Fabricante> comboFabricante) {
		this.comboFabricante = comboFabricante;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@PostConstruct
	public void listar() {
		itens = getProdutoDao().listar();

	}
	public void salvar() {
		produtoDao.salvar(getProduto());
		itens = produtoDao.listar();
	}

	public void preparaNovo() {
		produto = new Produto();

		
		FabricanteDAO dao = new FabricanteDAO();
		comboFabricante = dao.listar();
		
	}

	public void pegaPosicao() {
		produto = getProdutoDao().findById(getId());
	}
	
	private Integer getId(){
		return Integer.parseInt(codigo);
	}
	
	public void deletar(){
		getProdutoDao().deletar(getId());
		itens = getProdutoDao().listar();
	}
	
	
	public void axiliarEditar(){
		produto = getProdutoDao().findById(getId());
		FabricanteDAO dao = new FabricanteDAO();
		comboFabricante = dao.listar();
		
	}
	public void editar(){
		getProdutoDao().editar(getProduto());
		itens = getProdutoDao().listar();
	}
	
	

}
