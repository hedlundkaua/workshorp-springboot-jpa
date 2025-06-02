package com.hedlundkaua.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hedlundkaua.course.entities.User;
import com.hedlundkaua.course.services.UserService;

//recurso basico baseado na classe user
//a classe vai me disponibilizar um recurso web correspondente a entidade user

//classe é um recurso web, que é implementado por um controlador rest


@RestController //ussamos essa anotação para dizer que a classe é um recurso web, que é implementado por um controlador rest

@RequestMapping(value = "/users")// usamos para dar um nome para esse recurso, no caso o caminho.
public class UserResource {
	
	//vamos chamar o serviço aqui, vamos fazer uma dependencia para o service
	@Autowired
	private UserService service;
	
	
	//metodo end-point para acessar os usuarios
	@GetMapping //para indicar que o metodo responde a requisição do tipo GET do HTTP colocamos na anotation
	public ResponseEntity<List<User>> findAll(){ //a resposta agora vai ser do tipo List User
		//ResponseEntity tipo especifico do spring para retornar respostas para requisições web
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);// o responseEntitiy.ok rertorna a resposta com sucesso no HTTP e vamos chamar o .body()
		//para retornar o corpo da resposta chamando o usuario u que instanciamos.	
	}
	
	@GetMapping(value = "/{id}") //indica que a requisição aceita um id dentro da URL
	public ResponseEntity<User> findById(@PathVariable Long id){ //para o spring aceitar o id que vem da URL
		User obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
}
