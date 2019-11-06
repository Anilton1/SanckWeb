package br.com.rexpress.rn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import br.com.rexpress.dao.EnderecoDAO;
import br.com.rexpress.dao.InstituicaoDAO;
import br.com.rexpress.dao.MerendaDAO;
import br.com.rexpress.dao.TurnoDAO;
import br.com.rexpress.valuesObjects.Endereco;
import br.com.rexpress.valuesObjects.Instituicao;
import br.com.rexpress.valuesObjects.Merenda;
import br.com.rexpress.valuesObjects.Rota;
import br.com.rexpress.valuesObjects.Turno;

public class RNInstituicao {
	private Endereco aEndereco;
	private Turno aTurno;
	private TurnoDAO aTurnoDAO;
	private InstituicaoDAO aInstituicaoDAO;
	private EnderecoDAO aEnderecoDAO;
	private RNRota aRNRota;

	public RNInstituicao() {
		aEndereco = new Endereco();
		aTurno = new Turno();
		aTurnoDAO = new TurnoDAO();
		aInstituicaoDAO = new InstituicaoDAO();
		aEnderecoDAO = new EnderecoDAO();

		aRNRota = new RNRota();

	}

	public Instituicao valida(String pDescricaoInstituicao) {

		ArrayList<Instituicao> instituicaoArray = (ArrayList<Instituicao>) aInstituicaoDAO
				.listarTudo();
		Instituicao aInstituicao = null;

		Iterator<Instituicao> ite = instituicaoArray.iterator();
		while (ite.hasNext()) {
			Instituicao instituicao = ite.next();
			if ((instituicao.getNomeInstituicao().equals(pDescricaoInstituicao))) {
				aInstituicao = instituicao;
			}
		}
		return aInstituicao;
	}

	public Endereco inserirEndereco(Endereco pEndereco) {
		aEnderecoDAO.inserir(pEndereco);

		ArrayList<Endereco> enderecoArray = (ArrayList<Endereco>) aEnderecoDAO
				.listarTudo();
		Endereco aEndereco = null;

		Iterator<Endereco> ite = enderecoArray.iterator();
		while (ite.hasNext()) {
			Endereco endereco = ite.next();
			if ((endereco.getCep().equals(pEndereco.getCep()))) {
				aEndereco = endereco;
			}
		}

		return aEndereco;

	}

	public void deletarEndereco(Endereco pEndereco) {

		ArrayList<Endereco> enderecoArray = (ArrayList<Endereco>) aEnderecoDAO
				.listarTudo();
		Endereco aEndereco = null;

		Iterator<Endereco> ite = enderecoArray.iterator();
		while (ite.hasNext()) {
			Endereco endereco = ite.next();
			if ((endereco.equals(pEndereco))) {
				aEndereco = endereco;
			}
		}

		aEnderecoDAO.deletar(aEndereco);
	}

	public void inserirTurno(Turno pTurno) {
		aTurnoDAO.inserir(pTurno);

	}

	public void deletarTurno(Turno pTurno) {

		ArrayList<Turno> turnoArray = (ArrayList<Turno>) aTurnoDAO.listarTudo();
		Turno aTurno = null;

		Iterator<Turno> ite = turnoArray.iterator();
		while (ite.hasNext()) {
			Turno turno = ite.next();
			if ((turno.equals(pTurno))) {
				aTurno = turno;
			}
		}

		aTurnoDAO.deletar(aTurno);
	}
	
	public List<Instituicao> completeCreche(String query) {
		aInstituicaoDAO = new InstituicaoDAO(); 
		List<Instituicao> allInstituicao = aInstituicaoDAO.listarCreches();
		List<Instituicao> filteredInstituicao = new ArrayList<Instituicao>();

		for (int i = 0; i < allInstituicao.size(); i++) {
			Instituicao skin = allInstituicao.get(i);
			if (skin.getNomeInstituicao().toLowerCase().startsWith(query.toLowerCase())) {
				filteredInstituicao.add(skin);
			}
		}
		return filteredInstituicao;
	}
	
	public List<Instituicao> completeEscola(String query) {
		aInstituicaoDAO = new InstituicaoDAO(); 
		List<Instituicao> allInstituicao = aInstituicaoDAO.listarEscolas();
		List<Instituicao> filteredInstituicao = new ArrayList<Instituicao>();

		for (int i = 0; i < allInstituicao.size(); i++) {
			Instituicao skin = allInstituicao.get(i);
			if (skin.getNomeInstituicao().toLowerCase().startsWith(query.toLowerCase())) {
				filteredInstituicao.add(skin);
			}
		}
		return filteredInstituicao;
	}
}
