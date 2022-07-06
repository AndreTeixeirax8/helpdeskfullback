package com.software.helpdeskfull.services;

import com.software.helpdeskfull.domain.Chamado;
import com.software.helpdeskfull.domain.Cliente;
import com.software.helpdeskfull.domain.Tecnico;
import com.software.helpdeskfull.domain.dtos.ChamadoDTO;
import com.software.helpdeskfull.domain.enums.Prioridade;
import com.software.helpdeskfull.domain.enums.Status;
import com.software.helpdeskfull.repositories.ChamadoRepository;
import com.software.helpdeskfull.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository; //esse cara faz a comunicação com o banco
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id){
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectnotFoundException("Objeto não encotrado! ID: "+id));

    }

    public List<Chamado> findAll() {
        return repository.findAll();//retorna todos os chamados do banco
    }

    public Chamado create(@Valid ChamadoDTO objDTO) {

        return repository.save(newChamado(objDTO));
    }

    public Chamado update(Integer id,@Valid ChamadoDTO objDTO) {
        objDTO.setId(id);
        Chamado oldObj = findById(id);
        oldObj = newChamado(objDTO);
        return  repository.save(oldObj);
    }

    private Chamado newChamado(ChamadoDTO obj){
        Tecnico tecnico =  tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        Chamado chamado =  new Chamado();
        //se o id desse chamado estiver vazio siguinifica que ele quer criar um  se não é atualizar um
        if(obj.getId() != null ){
            chamado.setId(obj.getId());
        }

        if(obj.getStatus().equals(2)){
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());
        return chamado;

    }


}
