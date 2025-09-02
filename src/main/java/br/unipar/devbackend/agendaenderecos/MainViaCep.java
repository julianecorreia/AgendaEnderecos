package br.unipar.devbackend.agendaenderecos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainViaCep {

    public static void main(String[] args) throws IOException {
        String cep = "85900120"; //cep que estou buscando
        String url = "https://viacep.com.br/ws/" + cep + "/xml/"; //url viacep

        URL urlViaCep = new URL(url); //objeto com a url do via cep
        HttpURLConnection connection = (HttpURLConnection) urlViaCep.openConnection(); //abre conexão
        connection.setRequestMethod("GET"); //metodo da requisição é GET

        //ler o que está vindo da requisição connection
        BufferedReader leitor = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String linha; //linha que está vindo
        StringBuilder resposta = new StringBuilder(); //resposta completa (construida)

        while ((linha = leitor.readLine()) != null) { //enquanto tiver linha (diferente de nulo)
            resposta.append(linha); //adiciona a linha na resposta
        }

        leitor.close(); //fecha o leitor
        System.out.println(resposta);
    }
}
