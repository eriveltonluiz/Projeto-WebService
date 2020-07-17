package com.project.demo.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.project.demo.model.Usuario;
import com.project.demo.repositories.UsuarioRepository;
import com.project.demo.services.exception.DatabaseException;
import com.project.demo.services.exception.ResourceNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;

	public List<Usuario> findAll(){
		return repository.findAll();
	}
	
	public Usuario findById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Usuario inserir(Usuario user) {
		return repository.save(user);
	}
	
	public void deletar(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Usuario atualizar(Long id, Usuario user) {
		try {
			Usuario entidade = repository.getOne(id);
			atualizarDados(entidade, user);
			return repository.save(entidade);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException();
		}
	}

	private void atualizarDados(Usuario entidade, Usuario user) {
		entidade.setNome(user.getNome());
		entidade.setEmail(user.getEmail());
		entidade.setTelefone(user.getTelefone());
	}
}
