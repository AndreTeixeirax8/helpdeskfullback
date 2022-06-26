package com.software.helpdeskfull;

import com.software.helpdeskfull.repositories.ChamadoRepository;
import com.software.helpdeskfull.repositories.ClienteRepository;
import com.software.helpdeskfull.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelpdeskfullApplication implements CommandLineRunner {

	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;


	public static void main(String[] args) {
		SpringApplication.run(HelpdeskfullApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
