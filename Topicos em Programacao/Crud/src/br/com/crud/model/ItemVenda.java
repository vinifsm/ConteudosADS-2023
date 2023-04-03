package br.com.crud.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.towel.el.annotation.Resolvable;

@Entity
@SequenceGenerator(name = "ItemVenda_SEQ", sequenceName = "ItemVenda_SEQ")
@Table(name = "ItemVenda")
@SuppressWarnings("serial")

public class ItemVenda implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ItemVenda_SEQ")
	@Column(name = "codigo")
	@Resolvable(colName = "Codigo")
	private Integer codigo;

	@Column(name = "preco")
	@Resolvable(colName = "Preço")
	private Double preco;

	@Column(name = "desconto")
	@Resolvable(colName = "Desconto")
	private Double desconto;

	@Column(name = "quantidadeItens")
	@Resolvable(colName = "Quantidade de Itens")
	private Double quantidadeItens;

	@Column(name = "cancel")
	private String cancelado;

	@Column(name = "causaCancel")
	private String causaCancel;

	
	
	
	@ManyToOne // um pra varios, referenciadas com outra entidade que vai ter valores unicos
	@JoinColumn(name = "produto_codigo")
	@Resolvable(colName = "Codigo do produto")
	private Produto produto;

	@ManyToOne 
	@JoinColumn(name = "produto_nomeProduto")
	@Resolvable(colName = "Nome do produto")
	private Produto nomeProduto;
	
	@ManyToOne
	@JoinColumn(name = "preVenda_codigo")
	private PreVenda preVenda;

	// getters and setters

	public Integer getCodigo() {
		return codigo;
	}

	public PreVenda getPreVenda() {
		return preVenda;
	}

	public void setPreVenda(PreVenda preVenda) {
		this.preVenda = preVenda;
	}

	public String getCancelado() {
		return cancelado;
	}

	public String getCausaCancel() {
		return causaCancel;
	}

	public void setCancelado(String cancelado) {
		this.cancelado = cancelado;
	}

	public void setCausaCancel(String causaCancel) {
		this.causaCancel = causaCancel;
	}

	public Double getPreco() {
		return preco;
	}

	public Double getDesconto() {
		return desconto;
	}

	public Double getQuantidadeItens() {
		return quantidadeItens;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Produto getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(Produto nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public void setQuantidadeItens(Double quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
