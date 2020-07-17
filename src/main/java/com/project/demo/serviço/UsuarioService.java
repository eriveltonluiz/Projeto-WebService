package com.project.demo.serviço;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.model.Usuario;
import com.project.demo.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;

	public List<Usuario> findAll(){
		return repository.findAll();
	}
	
	public Usuario findById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.get();
	}
	
	public Usuario inserir(Usuario user) {
		return repository.save(user);
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
	public Usuario atualizar(Long id, Usuario user) {
		Usuario entidade = repository.getOne(id);
		atualizarDados(entidade, user);
		return repository.save(entidade);
	}

	private void atualizarDados(Usuario entidade, Usuario user) {
		entidade.setNome(user.getNome());
		entidade.setEmail(user.getEmail());
		entidade.setTelefone(user.getTelefone());
	}
}
