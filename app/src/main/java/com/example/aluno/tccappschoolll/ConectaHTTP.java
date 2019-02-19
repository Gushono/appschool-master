package com.example.aluno.tccappschoolll;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConectaHTTP extends AsyncTask<String, Void, String> {
	private URL url = null;
	private String[] parametros;
	private String[] valores;
	
	public ConectaHTTP(String url, String[] parametros, String[] valores){
		try {
			this.url = new URL("http://10.87.106.37/gu/webservicetcc.asmx/"+url);
			this.parametros = parametros;
			this.valores = valores;
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	private String concatenaParametros(){
		String retorno = "";
		
		for (int i=0 ; i<parametros.length ;  i++) {
			retorno += parametros[i];
			retorno += "=";
			retorno += valores[i];
			
			if(parametros.length > 1){
				retorno+= "&";
			}
		}
		
		Log.i("testeInfo", "Método concatenaParametros: " + retorno);
		return retorno;
	}
	
	public String acessaWebService(){
		Log.i("testeInfo", "Entrou no cadastraValores");
		
		Log.i("testeInfo", "URL: " + url.toString());
		
		//Conteúdo a ser retornado pelo método
		StringBuffer resposta = new StringBuffer();
		String retorno = "";
		
		try{
			//Configuração dos parâmetros exigidos pelo método no webservice
			String parametros = concatenaParametros();

			//Montagem do protocolo HTTP e conexão na URL especificada
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
				
			//Indicando qual método será utilizado para requisição
			conexao.setRequestMethod("GET");
			
			//Estabelecendo que a conexão irá retornar algo (output)
			conexao.setDoOutput(true);
			
			//Criação de objeto para armazenar o conteúdo a ser enviado para o webservice
			DataOutputStream dos = new DataOutputStream(conexao.getOutputStream());
			
			//Passagem dos parâmetros
			dos.writeBytes(parametros);

			//Envia o conteúdo armazenado dentro do DataOutputStream
			dos.flush();
			
			//Fecha o fluxo de dados/conexão
			dos.close();

			Log.i("testeInfo", "Response code: " + conexao.getResponseCode());
			
			//Criação de objeto para armazenar o conteúdo recebido do webservice
			BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
			
			//String para armazenar o conteúdo no formato de texto
			String linhas;

			//Enquanto o conteúdo tiver linhas para leitura
			while ((linhas = in.readLine()) != null) {
				//Concatena cada linha na String
				resposta.append(linhas);
			}
			
			//Após a leitura, fecha o fluxo de entrada
			in.close();
			
			retorno = resposta.toString();
		}
		catch (Exception e){
			//Log.i("testeInfo", e.getMessage());
			e.printStackTrace();
		}
		
		return retorno;
	}

	@Override
	protected String doInBackground(String... params) {
		return acessaWebService();
	}	
}