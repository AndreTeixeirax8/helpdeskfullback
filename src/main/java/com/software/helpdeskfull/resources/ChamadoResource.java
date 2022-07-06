package com.software.helpdeskfull.resources;

import com.software.helpdeskfull.domain.Chamado;
import com.software.helpdeskfull.domain.dtos.ChamadoDTO;
import com.software.helpdeskfull.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){

        Chamado obj = service.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));

    }

    @GetMapping
    public  ResponseEntity<List<ChamadoDTO>> findAll(){
        List<Chamado> list =  service.findAll();
        //Converter para uma lista
        List<ChamadoDTO> listDTO= list.stream().map(obj -> new ChamadoDTO(obj)).collect(Collectors.toList());
        return  ResponseEntity.ok().body(listDTO);

    }

    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO objDTO){
        //criar uma instancia padrão
        Chamado obj = service.create(objDTO);
        URI uri= ServletUriComponentsBuilder.
                fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
