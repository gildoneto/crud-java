package br.com.cafe.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.ClientPreparedStatement;

import br.com.cafe.factory.ConnectionFactory;
import br.com.cafe.model.Prato;

public class PratoDAO {

	
	public void save(Prato prato) {


		String sql = "INSERT INTO cafemanha(cpf, nome, alimento) VALUES (?, ?, ?)";
		
		Connection conn = null;
		ClientPreparedStatement pstm = null;
		ClientPreparedStatement pstmCPF = null;
		ClientPreparedStatement pstmAlimento = null;
		ResultSet rsetCPF = null;
		ResultSet rsetAlimento = null;
		
		try {
			//cria conexao com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstmCPF = (ClientPreparedStatement) conn.prepareStatement("SELECT * FROM cafemanha WHERE cpf = ?");
			pstmCPF.setString(1, prato.getCpf());
			rsetCPF = pstmCPF.executeQuery();
			
			pstmAlimento = (ClientPreparedStatement) conn.prepareStatement("SELECT * FROM cafemanha WHERE alimento = ?");
			pstmAlimento.setString(1, prato.getAlimento());
			rsetAlimento = pstmAlimento.executeQuery();
			
			if(rsetCPF.next() || rsetAlimento.next()) {
				System.out.println("O CPF "+ prato.getCpf() + " ou prato " + prato.getAlimento() + " já existe no banco de dados!" );
			} else {
				pstm = (ClientPreparedStatement) conn.prepareStatement(sql);
				pstm.setString(1, prato.getCpf());
				pstm.setString(2, prato.getNome());
				pstm.setString(3, prato.getAlimento());
				
				//executa a query
				pstm.execute();
				
				System.out.println("O prato " + prato.getAlimento() + " dx funcionárix " + prato.getNome() +" foi salvo com sucesso no MYSQL!");
			}
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	public void update(Prato prato) {
		String sql = "UPDATE cafemanha SET nome = ?, alimento = ? " +
					"WHERE cpf = ?";
		
		Connection conn = null;
		ClientPreparedStatement pstm = null;
		
		try {
			// cria conexão com banco
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//cria classe pra executar query
			pstm = (ClientPreparedStatement) conn.prepareStatement(sql);
			
			// Adicionar os valores
			pstm.setString(1, prato.getNome());
			pstm.setString(2, prato.getAlimento());
			//Qual ID que deseja atualizar?
			pstm.setString(3, prato.getCpf());
			
			//Executar a query
			pstm.execute();
			
			System.out.println("O prato dx funcionárix " + prato.getNome() +" foi atualizado para " + prato.getAlimento());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void deleteByCPF(String cpf) {
		
		String sql = "DELETE FROM cafemanha WHERE cpf = ?";
		
		Connection conn = null;
		ClientPreparedStatement pstm = null;
		
		try {
			// cria conexão com banco
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//cria classe pra executar query
			pstm = (ClientPreparedStatement) conn.prepareStatement(sql);
			
			pstm.setString(1, cpf);
			
			//Executar a quey
			pstm.execute();
			
			System.out.println("O prato do CPF " + cpf + " foi excluído com sucesso!");
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Prato> getPratos(){
		String sql = "SELECT * FROM cafemanha";
		
		List<Prato> pratos = new ArrayList<Prato>();
		
		Connection conn = null;
		ClientPreparedStatement pstm = null;
		//classe que vai recuperar os dados do banco.
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (ClientPreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			
			while(rset.next()) {
				Prato prato = new Prato();
				
				// recuperar o id
				prato.setCpf(rset.getString("cpf"));
				// recuperar o nome
				prato.setNome(rset.getString("nome"));
				// recupera idade
				prato.setAlimento(rset.getString("alimento"));
				
				pratos.add(prato);
			}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				
				try {
					if(rset != null) {
						rset.close();
					}
					if(pstm != null) {
						pstm.close();
					}
					if(conn != null) {
						conn.close();
					}
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			return pratos;
		
	}
}
