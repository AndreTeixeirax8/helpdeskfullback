package com.software.helpdeskfull.repositories;

import com.software.helpdeskfull.domain.Cliente;
import com.software.helpdeskfull.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

/*Arquivo que vai servir para gravar os dados no banco
* Aqui ele espera como parametros uma classe e o tipo primitivo
*/
public interface TecnicoRepository extends JpaRepository<Tecnico,Integer> {

}
