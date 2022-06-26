package com.software.helpdeskfull.services;

import com.software.helpdeskfull.domain.Chamado;
import com.software.helpdeskfull.domain.Cliente;
import com.software.helpdeskfull.domain.Tecnico;
import com.software.helpdeskfull.domain.enums.Perfil;
import com.software.helpdeskfull.domain.enums.Prioridade;
import com.software.helpdeskfull.domain.enums.Status;
import com.software.helpdeskfull.repositories.ChamadoRepository;
import com.software.helpdeskfull.repositories.ClienteRepository;
import com.software.helpdeskfull.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB(){
    // aqui os dados de ingeção para testes

        Tecnico tec1 = new Tecnico(null,"André","605.628.650-98","andre@email.com","123");
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null,"Linus Torvalds","80527954780","linus@email.com","123");

        Chamado c1 =  new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO,"Chamado 01","Primeiro",tec1,cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(c1));
    }
}
