package com.br.drogaria.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.JSFUtil;

import com.br.drogaria.factory.ConexaoFactory;
import com.mysql.jdbc.Connection;

public class ProdutoDAO {

	public void salvar(Produto produto) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO Produto ");
			sql.append("(descricao, quantidade, preco, Fabricante_codigo) values (?,?,?,?)");

			Connection conexao = ConexaoFactory.conecta();
			PreparedStatement comando;
			comando = conexao.prepareStatement(sql.toString());
			comando.setString(1, produto.getDescricao());
			comando.setInt(2, produto.getQuantidade());
			comando.setDouble(3, produto.getPreco());
			comando.setInt(4, produto.getFabricante().getCodigo());

			comando.executeUpdate();

			conexao.close();
			conexao.close();
			JSFUtil.adicionaMensagemSucesso("Salvo com sucesso");
		} catch (SQLException e) {
			JSFUtil.adicionaMensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	public void deletar(Integer id) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM Produto  ");
			sql.append("WHERE codigo = ?");

			Connection conexao = ConexaoFactory.conecta();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setLong(1, id);

			comando.executeUpdate();
			conexao.close();
			conexao.close();
			
			JSFUtil.adicionaMensagemSucesso("Deletado com sucesso");
		} catch (SQLException e) {
			JSFUtil.adicionaMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}


	public ArrayList<Produto> listar() {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select p.descricao, p.quantidade, p.codigo, p.preco, f.codigo, f.descricao  from produto p ");
			sql.append("inner join fabricante f ");
			sql.append("on f.codigo = ");
			sql.append("p.Fabricante_codigo ");

			Connection conexao = ConexaoFactory.conecta();
			PreparedStatement comando = conexao
					.prepareStatement(sql.toString());

			ResultSet resultado = comando.executeQuery();

			ArrayList<Produto> lista = new ArrayList<Produto>();
			while (resultado.next()) {
				Produto p = new Produto();
				Fabricante f = new Fabricante();
				p.setDescricao(resultado.getString("p.descricao"));
				p.setQuantidade(resultado.getInt("p.quantidade"));
				p.setPreco(resultado.getDouble("p.preco"));
				p.setCodigo(resultado.getInt("p.codigo"));

				f.setCodigo(resultado.getInt("f.codigo"));
				f.setDescricao(resultado.getString("f.Descricao"));

				p.setFabricante(f);

				lista.add(p);

			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public Produto findById(Integer id) {
		try {
			StringBuilder sql = new StringBuilder();

			sql.append("select * from drogaria.Produto p ");
			sql.append("join drogaria.Fabricante f ");
			sql.append("on (p.Fabricante_codigo = ");
			sql.append("f.codigo ");
			sql.append("and p.codigo = ?) ");
			
			Connection conexao = ConexaoFactory.conecta();

			PreparedStatement comando = conexao
					.prepareStatement(sql.toString());
			comando.setInt(1, id);

			ResultSet resultado = comando.executeQuery();

			Produto p = null;
			if (resultado.next()) {
				p = new Produto();
				Fabricante f = new Fabricante();
				p.setDescricao(resultado.getString("p.descricao"));
				p.setQuantidade(resultado.getInt("p.quantidade"));
				p.setPreco(resultado.getDouble("p.preco"));
				p.setCodigo(resultado.getInt("p.codigo"));

				f.setCodigo(resultado.getInt("f.codigo"));
				f.setDescricao(resultado.getString("f.descricao"));

				p.setFabricante(f);

			}
			return p;

		} catch (SQLException s) {
			s.printStackTrace();

			return null;

		}

	}
	
	public void editar(Produto produto){
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE produto ");
			sql.append("SET descricao = ?, ");
			sql.append("quantidade = ?, ");
			sql.append("preco = ?, ");
			sql.append("Fabricante_codigo = ? ");
			sql.append("WHERE codigo = ?");
			//System.out.println(sql.toString());

			Connection conexao = ConexaoFactory.conecta();
			PreparedStatement comando;
			comando = conexao.prepareStatement(sql.toString());
			comando.setString(1, produto.getDescricao());
			comando.setInt(2, produto.getQuantidade());
			comando.setDouble(3, produto.getPreco());
			comando.setInt(4, produto.getFabricante().getCodigo());
			comando.setInt(5,produto.getCodigo());

			comando.executeUpdate();

			conexao.close();
			conexao.close();
			
			JSFUtil.adicionaMensagemSucesso("Editado com sucesso");
		} catch (SQLException e) {
			JSFUtil.adicionaMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

}
