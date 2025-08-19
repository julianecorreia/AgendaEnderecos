package br.unipar.devbackend.agendaenderecos;

import br.unipar.devbackend.agendaenderecos.model.Endereco;
import br.unipar.devbackend.agendaenderecos.utils.EntityManagerUtil;

public class Main {

    public static void main(String[] args) {
        EntityManagerUtil.getEmf();
        System.out.println("Hello World");
    }
}
