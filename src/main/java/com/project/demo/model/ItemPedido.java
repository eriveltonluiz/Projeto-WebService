package com.project.demo.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.project.demo.model.pk.ItemPedidoPk;

@Entity
public class ItemPedido implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@JsonProperty(access = Access.WRITE_ONLY)
	private ItemPedidoPk id = new ItemPedidoPk();
	
	private Integer qtd;
	private Double preco;
	
	public ItemPedido() {
	}
	
	public ItemPedido(Pedido pedido, Produto produto ,Integer qtd, Double preco) {
		id.setPedido(pedido);
		id.setProduto(produto);
		this.qtd = qtd;
		this.preco = preco;
	}
	
	public Double getSubTotal() {
		return preco * qtd;
	}
	
	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}
	
	public void setPedido(Pedido pedido) {
		id.setPedido(pedido);
	}
	
	public Produto getProduto() {
		return id.getProduto();
	}
	
	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}

	public ItemPedidoPk getId() {
		return id;
	}

	public void setId(ItemPedidoPk id) {
		this.id = id;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
