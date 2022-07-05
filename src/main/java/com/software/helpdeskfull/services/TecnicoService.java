package com.software.helpdeskfull.services;

import com.software.helpdeskfull.domain.Pessoa;
import com.software.helpdeskfull.domain.Tecnico;
import com.software.helpdeskfull.domain.dtos.TecnicoDTO;
import com.software.helpdeskfull.repositories.PessoaRepository;
import com.software.helpdeskfull.repositories.TecnicoRepository;
import com.software.helpdeskfull.services.exceptions.DataIntegrityViolationException;
import com.software.helpdeskfull.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired //consulta o tecnico repositori
    private TecnicoRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id){
        //ele retorna um optinal ou seja ele pode enctrar um técnico como pode não encotrar
        Optional<Tecnico> obj =repository.findById(id);
        return obj.orElseThrow(()-> new ObjectnotFoundException("Objeto não encontrado! ID:" +id)
        );//retorna o objeto ou retorna uma exceção se não encotrar
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEmail(objDTO);
        Tecnico newObj = new Tecnico(objDTO);
        return  repository.save(newObj);
    }

    public Tecnico update(Integer id, TecnicoDTO objDto) {
        objDto.setId(id);
        Tecnico oldObj = findById(id);
        validaPorCpfEmail(objDto);
        oldObj =  new Tecnico(objDto);
        return repository.save(oldObj);

    }

    private void validaPorCpfEmail(TecnicoDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
            if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
                throw  new DataIntegrityViolationException("CPF já cadastrado no sistema");
            }

            obj =pessoaRepository.findByEmail(objDTO.getEmail());

        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw  new DataIntegrityViolationException("E-mail já cadastrado no sistema");
        }

        }


}

