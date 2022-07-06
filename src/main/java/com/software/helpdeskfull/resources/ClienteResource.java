package com.software.helpdeskfull.resources;
/*A camada que recebe as requisições http é camada REST  ou seja Resources*/

import com.software.helpdeskfull.domain.Cliente;
import com.software.helpdeskfull.domain.dtos.ClienteDTO;
import com.software.helpdeskfull.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController//por ser um controlador tem que ter essa notação
@RequestMapping(value = "/clientes")//mapeamento principal é o endpoint inicial do http
public class ClienteResource {

    @Autowired
    private ClienteService service; //declaração da instancia que possui os metodos

    /*Ultilizar o responseentity para trabalhar com API pois
     é uma boia pratica ese  Metodo é para buscar um técnico pelo Id*/
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
        Cliente obj = service.findById(id);
        return ResponseEntity.ok().body(new ClienteDTO(obj));//retorna um response com os dados no corpo da requisição

    }

    @GetMapping
    public  ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> list = service.findAll();

        List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    /*Aqui cria um novo técnico e ee exige que essa criação seja repassado a informação
    * pelo corpo da requisição.*/
    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDTO){
        Cliente newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id,@Valid @RequestBody ClienteDTO objDto){
        Cliente obj =service.update(id,objDto);
        return  ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public  ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){//como estou recebendo uma variavel de path tenho que declarar aui
        service.delete(id);
        return  ResponseEntity.noContent().build();


    }

}
