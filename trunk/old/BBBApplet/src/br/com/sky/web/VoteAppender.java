package br.com.sky.web;

import br.com.sky.Configuracao;

public class VoteAppender {

	public static void addVotes(int qtdVotes) {
		String url = Configuracao.URL_VOTE_APPENDER;
		url += "?qtdVotos=" + qtdVotes + "&poolId=" + Configuracao.getPoolId();

		try {
			HttpRequestPoster.getData(url, null, null);
		} catch (Exception ex) {
			System.err.println("Erro ao enviar qtd votos");
			ex.printStackTrace();
		}
	}
}
