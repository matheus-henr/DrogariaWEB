package br.com.drogaria.bin;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.drogaria.domain.Fabricante;

import com.br.drogaria.dao.FabricanteDAO;

@ManagedBean(name = "MBFabricante")
@ViewScoped
public class FabricanteBin {
	private ListDataModel<Fabricante> itens;
	private Fabricante fabricante;
	private ArrayList<Fabricante> itensFiltrado;
	private String escolhaBusca;
	private String codigo;
	private FabricanteDAO fabrincanteDao;
	
	public String getEscolhaBusca() {
		return escolhaBusca;
	}

	public void setEscolhaBusca(String escolhaBusca) {
		this.escolhaBusca = escolhaBusca;
	}

	public ListDataModel<Fabricante> getItens() {
		return itens;
	}

	public void setItens(ListDataModel<Fabricante> itens) {
		this.itens = itens;
	}

	public ArrayList<Fabricante> getItensFiltrado() {
		return itensFiltrado;
	}

	public void setItensFiltrado(ArrayList<Fabricante> itensFiltrado) {
		this.itensFiltrado = itensFiltrado;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public FabricanteDAO getFabrincanteDao() {
		if (fabrincanteDao == null) 
			fabrincanteDao = new FabricanteDAO();
		return fabrincanteDao;
	}

	public void setFabrincanteDa(FabricanteDAO fabrincanteDao) {
		this.fabrincanteDao = fabrincanteDao;
	}
	

	@PostConstruct
	public void prepararPesquisa() {
		ArrayList<Fabricante> item = getFabrincanteDao().listar();
		itens = new ListDataModel<Fabricante>(item);
	}

	public void preparaNovoFabricante() {
		fabricante = new Fabricante();
	}

	public void novoFabricante() {
		getFabrincanteDao().salvar(fabricante);
		ArrayList<Fabricante> item = getFabrincanteDao().listar();
		itens = new ListDataModel<Fabricante>(item);
		

	}
	
	 

	public void pegaPosicao() {
		fabricante = getFabrincanteDao().findById(getId());
	}
	
	private Integer getId(){
		return Integer.parseInt(codigo);
	}
	
	

	public void deletar() {
		getFabrincanteDao().deletar(fabricante);
		ArrayList<Fabricante> item = getFabrincanteDao().listar();
		itens = new ListDataModel<Fabricante>(item);
		
	}

	public void editar() {
		getFabrincanteDao().editar(fabricante);
		ArrayList<Fabricante> item = getFabrincanteDao().listar();
		itens = new ListDataModel<Fabricante>(item);
		

	}

	/**public void pesquisaPersonalizada() {

		try {
			
			if (escolhaBusca.equals("d")) {
				ArrayList<Fabricante> resultado = dao
						.listarPorDescricao(fabricante);
				itens = new ListDataModel<Fabricante>(resultado);
			} else {
				Fabricante resultado = getFabrincanteDao().buscaPorCodigo(fabricante);
				ArrayList<Fabricante> item = new ArrayList<Fabricante>();
				item.add(resultado);
				itens = new ListDataModel<Fabricante>(item);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}**/



}
