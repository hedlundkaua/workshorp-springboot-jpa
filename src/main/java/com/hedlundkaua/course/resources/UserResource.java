package com.hedlundkaua.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	//o metodo getMapping retorna todos os usuarios quando chamamos o /users na URL
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
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){//pra dizer que o obj vai chagr no modo JSON la na requisição e o json vai ser deserializado para um obj User usamos a anotation @RequestBody
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); // esse motodo faz com que retorne um cabeçalho chamado location contendo um endereço do novo recurso inserido.
		return ResponseEntity.created(uri).body(obj); //usamos o metodo acima para retornar um status 201 que é mais quando estamos inserindo recursos, ele é especifico do HTTP para quando estamos inserindo um novo recurso.
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){//@PathVariable para o long id ser reconhecido como uma variavel da URL
		service.delete(id);
		return ResponseEntity.noContent().build(); //como é uma resposta sem corpo chamamos o metodo noContet, e para chamamos o codigo HTTP de uma resposta que não tem conteudo é o 204.
	}
	
}
