package com.software.helpdeskfull.repositories;

import com.software.helpdeskfull.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

/*Arquivo que vai servir para gravar os dados no banco
* Aqui ele espera como parametros uma classe e o tipo primitivo
*/
public interface ChamadoRepository extends JpaRepository<Chamado,Integer> {

}
