package com.software.helpdeskfull.services;

import com.software.helpdeskfull.domain.Chamado;
import com.software.helpdeskfull.repositories.ChamadoRepository;
import com.software.helpdeskfull.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository; //esse cara faz a comunicação com o banco

    public Chamado findById(Integer id){
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectnotFoundException("Objeto não encotrado! ID: "+id));

    }

}
