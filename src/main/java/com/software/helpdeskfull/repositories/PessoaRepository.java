package com.software.helpdeskfull.repositories;

import com.software.helpdeskfull.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

/*Arquivo que vai servir para gravar os dados no banco
* Aqui ele espera como parametros uma classe e o tipo primitivo
* que define aquela classe no nosso caso é o ID pois ele é da tabela pessoa
* a chave primaria  */
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
