package br.com.rexpress.teste;

import junit.framework.TestCase;
import br.com.rexpress.controle.ControleDescartavel;
import br.com.rexpress.valuesObjects.Descartavel;

public class TesteControleDescartavel extends TestCase {
	
	private ControleDescartavel aControleDescartavel;

	public void testeSelecaoDescartavel() {
		aControleDescartavel = new ControleDescartavel();
		Descartavel Teste = new Descartavel();
		Teste.setDescricao("Teste");
		Descartavel retornoEsperado = Teste;

		Descartavel retorno = aControleDescartavel.selecionarDescartavel(Teste);

		assertEquals(retornoEsperado, retorno);

	}
}
