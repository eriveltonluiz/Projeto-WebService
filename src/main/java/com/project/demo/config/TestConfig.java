package com.project.demo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.project.demo.model.Categoria;
import com.project.demo.model.ItemPedido;
import com.project.demo.model.Pagamento;
import com.project.demo.model.Pedido;
import com.project.demo.model.Produto;
import com.project.demo.model.Usuario;
import com.project.demo.model.enums.PedidoStatus;
import com.project.demo.repositories.CategoriaRepository;
import com.project.demo.repositories.ItemPedidoRepository;
import com.project.demo.repositories.PedidoRepository;
import com.project.demo.repositories.ProdutoRepository;
import com.project.demo.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Produto p1 = new Produto(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Produto p2 = new Produto(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Produto p3 = new Produto(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Produto p4 = new Produto(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Produto p5 = new Produto(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		Categoria cat = new Categoria(null, "Eletrônicos");
		Categoria cat1 = new Categoria(null, "Livros");
		Categoria cat2 = new Categoria(null, "Computadores");
		
		categoriaRepository.saveAll(Arrays.asList(cat, cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		p1.getCategorias().add(cat1);
		p2.getCategorias().add(cat);
		p2.getCategorias().add(cat2);
		p3.getCategorias().add(cat2);
		p4.getCategorias().add(cat2);
		p5.getCategorias().add(cat1);
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		Usuario user = new Usuario(null, "Maria", "892340923", "maria@gmail.com", "maria123");
		Usuario user1 = new Usuario(null, "Alex", "992434923", "alex@gmail.com", "alex123");

		Pedido o1 = new Pedido(null, Instant.parse("2019-06-20T19:53:07Z"), PedidoStatus.PAID ,user);
		Pedido o2 = new Pedido(null, Instant.parse("2019-07-21T03:42:10Z"), PedidoStatus.WAITING_PAYMENT, user1);
		Pedido o3 = new Pedido(null, Instant.parse("2019-07-22T15:21:22Z"), PedidoStatus.WAITING_PAYMENT, user);
		
		usuarioRepository.saveAll(Arrays.asList(user, user1));
		pedidoRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		ItemPedido oi1 = new ItemPedido(o1, p1, 2, p1.getPreco());
		ItemPedido oi2 = new ItemPedido(o1, p3, 1, p3.getPreco());
		ItemPedido oi3 = new ItemPedido(o2, p3, 2, p3.getPreco());
		ItemPedido oi4 = new ItemPedido(o3, p5, 2, p5.getPreco());
		
		itemPedidoRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		Pagamento pag1 = new Pagamento(null, Instant.parse("2019-06-20T21:53:07Z"), o2);
		o2.setPagamento(pag1);
		
		pedidoRepository.save(o2);
	}

}
