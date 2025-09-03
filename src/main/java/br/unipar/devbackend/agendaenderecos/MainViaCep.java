package br.unipar.devbackend.agendaenderecos;

import br.unipar.devbackend.agendaenderecos.model.Endereco;
import br.unipar.devbackend.agendaenderecos.service.ViaCepService;
import jakarta.xml.bind.JAXBException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainViaCep {

    public static void main(String[] args) throws IOException, JAXBException {
        String cep = "85900120"; //cep que estou buscando

        //nosso serviço de busca de cep
        ViaCepService viaCep = new ViaCepService();
        Endereco endereco = viaCep.buscarCep(cep);

        System.out.println("CEP: " + endereco.getCep() +
                ", Logradouro: " + endereco.getLogradouro() +
                ", Bairro: " + endereco.getBairro() +
                ", Localidade: " + endereco.getLocalidade() +
                "/" + endereco.getUf());

        //buscar um cep qualquer
        //verificar se esse cep existe no banco de dados
            //se existir, pede pra adicionar um cliente pra ele
            //se não existir, grava o novo cep com data e hora da gravação

    }
}
