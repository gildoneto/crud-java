package br.com.cafe.aplicacao;
import br.com.cafe.dao.PratoDAO;
import br.com.cafe.model.Prato;

public class Main {

	public static void main(String[] args) {

		PratoDAO pratoDAO = new PratoDAO();
		
		// ===============================================
		// CREATE -> INSERT -> OK
		Prato pratoDeMozart = new Prato();
		pratoDeMozart.setCpf("00011122200");
		pratoDeMozart.setNome("TESTE CPF OK");
		pratoDeMozart.setAlimento("Pão");
		
		pratoDAO.save(pratoDeMozart);
		// ===============================================
		
		// ===============================================
		// READ -> SELECT => OK
		//visualização de todos os registros do BD
		//int cont = 1;
		//for(Prato p : pratoDAO.getPratos()) {
		//	System.out.println("Prato "+ cont + " -> " + p.getCpf() + " " + p.getNome() + " " + p.getAlimento());
		//	cont++;
		//}
		// ===============================================
		
		// ===============================================
		// UPDATE -> UPDATE -> OK
		//Prato pratoDeAnita = new Prato();
		//pratoDeAnita.setCpf("01333344459");
		//pratoDeAnita.setNome("Anita Abusada");
		//pratoDeAnita.setAlimento("Refri Punk");
		
		//pratoDAO.update(pratoDeAnita);
		// ===============================================
		
		// ===============================================
		// DELETE -> DELETE
		//remove prato pelo CPF
		//pratoDAO.deleteByCPF("01333344456");
		
		// ===============================================
	}

}
