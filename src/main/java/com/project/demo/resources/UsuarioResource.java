package com.project.demo.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.model.Usuario;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@GetMapping
	public ResponseEntity<Usuario> findAll(){
		Usuario user = new Usuario(1L, "Maria", "23423423", "yesye@gmail.com", "teste");
		return ResponseEntity.ok().body(user);
	}
}
