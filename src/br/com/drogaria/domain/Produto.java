package br.com.drogaria.domain;

public class Produto {
	private Integer codigo;
	private String descricao;
	private Integer quantidade;
	private Double preco;
	private Fabricante fabricante = new Fabricante();

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	@Override
	public String toString() {
		String retorno = "Descricao " + getDescricao() + " -- " + "Quantidade " + getQuantidade()+ " -- " +"Preco "+getPreco() + " Fabricante: " + getFabricante().getDescricao();
		return retorno;
	}
}
