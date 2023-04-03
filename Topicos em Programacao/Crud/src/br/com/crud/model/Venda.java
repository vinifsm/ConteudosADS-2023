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
@SequenceGenerator(name = "Venda_SEQ", sequenceName = "Venda_SEQ")
@Table(name = "venda")
@SuppressWarnings("serial")
public class Venda implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Venda_SEQ")
	@Column(name = "codigo")
	@Resolvable(colName = "Codigo")
	private Integer codigo;

	@Column(name = "valorTotal")
	@Resolvable(colName = "Valor Total")
	private Double valorTotal;

	@Column(name = "cancelado")
	@Resolvable(colName = "Cancelado")
	private String cancelado;

	@Column(name = "causaCancel")
	@Resolvable(colName = "Causa Cancel")
	private String causaCancel;

	@Column(name = "vendedor")
	private Vendedor vendedor;
	
	@Column(name = "dinheiro")
	@Resolvable(colName = "Dinheiro")
	private Double dinheiro;
	
	@Column(name = "cartao")
	@Resolvable(colName = "Cartão")
	private Double cartao;
	
	@Column(name = "crediario")
	@Resolvable(colName = "Crediario")
	private Double crediario;

	@ManyToOne
	@JoinColumn(name = "produto_codigo")
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "preVenda_codigo")
	private PreVenda preVenda;
	
	
	public Double getDinheiro() {
		return dinheiro;
	}

	public Double getCartao() {
		return cartao;
	}

	public Double getCrediario() {
		return crediario;
	}

	public void setCancelado(String cancelado) {
		this.cancelado = cancelado;
	}

	public void setDinheiro(Double dinheiro) {
		this.dinheiro = dinheiro;
	}

	public void setCartao(Double cartao) {
		this.cartao = cartao;
	}

	public void setCrediario(Double crediario) {
		this.crediario = crediario;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public String getCancelado() {
		return cancelado;
	}

	public String getCausaCancel() {
		return causaCancel;
	}

	public Produto getProduto() {
		return produto;
	}

	public PreVenda getPreVenda() {
		return preVenda;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setCancel(String cancel) {
		this.cancelado = cancel;
	}

	public void setCausaCancel(String causaCancel) {
		this.causaCancel = causaCancel;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setPreVenda(PreVenda preVenda) {
		this.preVenda = preVenda;
	}

}
