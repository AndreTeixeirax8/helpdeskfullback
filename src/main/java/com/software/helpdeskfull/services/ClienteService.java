package com.software.helpdeskfull.services;

import com.software.helpdeskfull.domain.Pessoa;
import com.software.helpdeskfull.domain.Cliente;
import com.software.helpdeskfull.domain.dtos.ClienteDTO;
import com.software.helpdeskfull.repositories.PessoaRepository;
import com.software.helpdeskfull.repositories.ClienteRepository;
import com.software.helpdeskfull.services.exceptions.DataIntegrityViolationException;
import com.software.helpdeskfull.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired //consulta o Cliente repositorio
    private ClienteRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id){
        //ele retorna um optinal ou seja ele pode enctrar um técnico como pode não encotrar
        Optional<Cliente> obj =repository.findById(id);
        return obj.orElseThrow(()-> new ObjectnotFoundException("Objeto não encontrado! ID:" +id)
        );//retorna o objeto ou retorna uma exceção se não encotrar
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEmail(objDTO);
        Cliente newObj = new Cliente(objDTO);
        return  repository.save(newObj);
    }

    public Cliente update(Integer id, ClienteDTO objDto) {
        objDto.setId(id);
        Cliente oldObj = findById(id);
        validaPorCpfEmail(objDto);
        oldObj =  new Cliente(objDto);
        return repository.save(oldObj);

    }

    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (obj.getChamados().size()>0){//exceção de violação de integridade dos dados
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço não pode ser deletado!");
        }
            repository.deleteById(id);

    }


    private void validaPorCpfEmail(ClienteDTO objDTO) {
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

