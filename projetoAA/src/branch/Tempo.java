package branch;

/**
 * Classe geral para tarefas de tempo
 */
public class Tempo {
	long inicio;
	long parada;
	public final static String[] unidades = { "μs", "ms", "s", "ks", "Ms" };

	/**
	 * Inicie o temporizador
	 */
	public void start() {
		inicio = System.nanoTime();
	}

	/**
	 * para o temporizador
	 */
	public void stop() {
		parada = System.nanoTime();
	}

	/**
	 * Obter o tempo decorrido em nanossegundos
	 *
	 * @return Tempo em nanossegundos
	 */
	public long getTime() {
		return parada - inicio;
	}

	/**
	 * Obter uma string de horário formatada que ajuste o número e inclua unidades
	 *
	 * @return Tempo formatado
	 */
	public String getFormattedTime() {
		long tempo = getTime();
		int unidade = (int)((Math.log10(tempo) - 9 - 2) / 3); //-9 porque nano, -2 porque escolhe uma unidade com -1 a 2 dígitos
		if(unidade > 2)
			unidade = 2;
		else if(unidade < -2)
			unidade = -2;

		return (tempo / Math.pow(10, unidade * 3 + 9)) + unidades[unidade + 2]; //+9 porque nano, +2 porque os arrays não podem ter índices negativos
	}
}
