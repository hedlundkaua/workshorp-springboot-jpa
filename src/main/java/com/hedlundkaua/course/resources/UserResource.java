package com.hedlundkaua.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hedlundkaua.course.entities.User;

//recurso basico baseado na classe user
//a classe vai me disponibilizar um recurso web correspondente a entidade user

//classe é um recurso web, que é implementado por um controlador rest


@RestController //ussamos essa anotação para dizer que a classe é um recurso web, que é implementado por um controlador rest

@RequestMapping(value = "/users")// usamos para dar um nome para esse recurso, no caso o caminho.
public class UserResource {
	
	//metodo end-point para acessar os usuarios
	@GetMapping //para indicar que o metodo responde a requisição do tipo GET do HTTP colocamos na anotation
	public ResponseEntity<User> findAll(){ //ResponseEntity tipo especifico do spring para retornar respostas para requisições web
		User u = new User(1L, "maria", "maria@gmail.com", "999999", "12345");
		return ResponseEntity.ok().body(u);// o responseEntitiy.ok rertorna a resposta com sucesso no HTTP e vamos chamar o .body()
		//para retornar o corpo da resposta chamando o usuario u que instanciamos.
	}
}
