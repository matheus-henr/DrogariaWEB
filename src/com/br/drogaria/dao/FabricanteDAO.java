package com.br.drogaria.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.JSFUtil;

import com.br.drogaria.factory.ConexaoFactory;
import com.mysql.jdbc.Connection;

public class FabricanteDAO {

	public void salvar(Fabricante fabricante)  {
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO Fabricante ");
			sql.append("(descricao) VALUES (?) ");

			Connection conexao = ConexaoFactory.conecta();
			PreparedStatement comando;
			comando = conexao.prepareStatement(sql.toString());
			comando.setString(1, fabricante.getDescricao());
			comando.executeUpdate();

			comando.close();
			conexao.close();
			JSFUtil.adicionaMensagemSucesso("Salvo com sucesso  com Sucesso");
		} catch (SQLException e) {
			JSFUtil.adicionaMensagemErro(e.getMessage());
			e.printStackTrace();
		}
		
	}

	public void deletar(Fabricante fabricante){
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM Fabricante ");
			sql.append("WHERE codigo = ? ");

			Connection conexao = ConexaoFactory.conecta();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setLong(1, fabricante.getCodigo());
			comando.executeUpdate();

			comando.close();
			conexao.close();
			JSFUtil.adicionaMensagemSucesso("Deletado com Sucesso");
		} catch (SQLException e) {
			JSFUtil.adicionaMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public void editar(Fabricante fabricante){
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE Fabricante ");
			sql.append("SET descricao = ? ");
			sql.append("WHERE codigo = ?");

			Connection conexao = ConexaoFactory.conecta();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setString(1, fabricante.getDescricao());
			comando.setInt(2, fabricante.getCodigo());
			comando.executeUpdate();

			comando.close();
			conexao.close();
			JSFUtil.adicionaMensagemSucesso("Editado  com Sucesso");
		} catch (SQLException e) {
			JSFUtil.adicionaMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public Fabricante buscaPorCodigo(Fabricante fabricante) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT codigo, descricao ");
		sql.append("FROM Fabricante ");
		sql.append("WHERE codigo = ?");

		Connection conexao = ConexaoFactory.conecta();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, fabricante.getCodigo());
		ResultSet resutado = comando.executeQuery();

		Fabricante retorno = null;

		if (resutado.next()) {
			retorno = new Fabricante();
			retorno.setCodigo(resutado.getInt("codigo"));
			retorno.setDescricao(resutado.getString("descricao"));
		}
		comando.close();
		conexao.close();

		return retorno;
	}

	public Fabricante findById(Integer id) {
		try {
			StringBuilder sql = new StringBuilder();

			sql.append("SELECT codigo, descricao ");
			sql.append("FROM Fabricante ");
			sql.append("WHERE codigo = ?");

			Connection conexao = ConexaoFactory.conecta();
			PreparedStatement comando = conexao
					.prepareStatement(sql.toString());
			comando.setLong(1, id);
			ResultSet resutado = comando.executeQuery();

			Fabricante retorno = null;

			if (resutado.next()) {
				retorno = new Fabricante();
				retorno.setCodigo(resutado.getInt("codigo"));
				retorno.setDescricao(resutado.getString("descricao"));
			}
			comando.close();
			conexao.close();

			return retorno;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Fabricante> listar() {
		try {
			StringBuilder sql = new StringBuilder();

			sql.append("SELECT codigo, descricao ");
			sql.append("FROM Fabricante ");
			sql.append("ORDER BY descricao ASC");

			Connection conexao = ConexaoFactory.conecta();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			ResultSet resutado = comando.executeQuery();

			ArrayList<Fabricante> lista = new ArrayList<Fabricante>();

			while (resutado.next()) {
				Fabricante fabricante = new Fabricante();
				fabricante.setCodigo(resutado.getInt("codigo"));
				fabricante.setDescricao(resutado.getString("descricao"));
				lista.add(fabricante);
			}
			return lista;
		} catch (SQLException e) {
			JSFUtil.adicionaMensagemErro(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Fabricante> listarPorDescricao(Fabricante fabricante)
			throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT codigo, descricao ");
		sql.append("FROM Fabricante ");
		sql.append("WHERE descricao LIKE ?");
		sql.append("ORDER BY descricao ASC");

		Connection conexao = ConexaoFactory.conecta();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + fabricante.getDescricao() + "%");
		ResultSet resutado = comando.executeQuery();

		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();

		while (resutado.next()) {
			Fabricante f = new Fabricante();
			f.setCodigo(resutado.getInt("codigo"));
			f.setDescricao(resutado.getString("descricao"));
			lista.add(f);
		}
		return lista;
	}


}
