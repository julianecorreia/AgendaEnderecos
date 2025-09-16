package br.unipar.devbackend.agendaenderecos;

import br.unipar.devbackend.agendaenderecos.dao.ClienteDAO;
import br.unipar.devbackend.agendaenderecos.dao.EnderecoDAO;
import br.unipar.devbackend.agendaenderecos.model.Cliente;
import br.unipar.devbackend.agendaenderecos.model.Endereco;
import br.unipar.devbackend.agendaenderecos.service.ViaCepService;
import br.unipar.devbackend.agendaenderecos.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainAtividade {

    public static void main(String[] args) throws JAXBException, IOException {
        //O usuário deve informar um CEP
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe um CEP:");
        String cep = scanner.nextLine();

        System.out.println("CEP informado: " + cep);

        // O programa deve consultar esse CEP no banco de dados,
        // via Hibernate, se já existe um endereço cadastrado com o
        // CEP informado.
        EntityManagerUtil.getEmf();

        EnderecoDAO endDAO = new EnderecoDAO(EntityManagerUtil.getEm());
        List<Endereco> enderecosList = endDAO.findByCep(cep);

        if(enderecosList.isEmpty()){
            //Se o CEP não existir: O sistema deve consultar a API ViaCEP
            // para recuperar os atributos completos do endereço
            // (logradouro, bairro, cidade, estado, etc.).
            // E com os dados retornados pela API criar um novo registro
            // de endereço no banco de dados, armazenando também a
            // data e hora da gravação.
            System.out.println("Não tem endereço com esse cep!");

            ViaCepService viaCepService = new ViaCepService();

            Endereco endereco = viaCepService.buscarCep(cep);
            endereco.setDataCadastro(LocalDateTime.now());

            EnderecoDAO enderecoViaCepDAO = new EnderecoDAO(EntityManagerUtil.getEm());
            enderecoViaCepDAO.gravar(endereco);

        } else { //se existir
            // Se o CEP existir no banco de dados:
            // O sistema deve solicitar o cadastro de um novo cliente associado
            // a esse endereço.
            System.out.println("Tem endereço com esse cep =D");

            System.out.println("Então por favor cadastre um novo cliente!");

            Cliente novoCliente = new Cliente();
            System.out.println("Informe o nome do cliente:");
            novoCliente.setNome(scanner.nextLine());
            System.out.println("Informe o email do cliente:");
            novoCliente.setEmail(scanner.nextLine());
            System.out.println("Informe a data de nascimento do cliente:");
            novoCliente.setDataNascimento(new Date(scanner.nextLine()));

            ClienteDAO cliDAO = new ClienteDAO(EntityManagerUtil.getEm());
            cliDAO.gravar(novoCliente);

            //adicionar os endereços aqui nesse for
            for (Endereco endereco : enderecosList) {
                endereco.setCliente(novoCliente);
                System.out.println(endereco.toString());

                EnderecoDAO newEnderDAO = new EnderecoDAO(EntityManagerUtil.getEm());
                newEnderDAO.editar(endereco);
            }
        }
        EntityManagerUtil.closeEmf();
    }
}