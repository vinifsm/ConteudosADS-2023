package br.com.crud.model;/*  Codigo Gerado Automaticamente Pelo Framework JAVA Facil
							* 
							*  Framework Desenvolvido por Eduardo Nicolini Sodre da Silva
							*  www.studiowebmaster.com.br - www.vipsystem.com.br
							*  classe gerada pelo framework em:17052013101234 
							*/

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.towel.el.annotation.Resolvable;

@Entity
@SequenceGenerator(name = "Cliente_SEQ", sequenceName = "Cliente_SEQ")
@Table(name = "cliente")
@SuppressWarnings("serial")
public class Cliente implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Cliente_SEQ")
	@Column(name = "codigo")
	@Resolvable(colName = "Codigo")
	private Integer codigo;
	@Column(name = "cancelado")
	private String cancelado;
	@Column(name = "causaCancel")
	private String causaCancel;
	@Column(name = "razaoSocial", length = 60)
	@Resolvable(colName = "Razao Social")
	private String razaoSocial;
	@Column(name = "dataNascimento")
	@Resolvable(colName = "Data de Nascimento")
	private Date dataNascimento;
	@Column(name = "cpf")
	@Resolvable(colName = "CPF")
	private String cpf;
	@Column(name = "endereco")
	@Resolvable(colName = "Endereco")
	private String endereço;
	@Column(name = "complemento")
	@Resolvable(colName = "Complemento")
	private String complemento;
	@Column(name = "bairro")
	@Resolvable(colName = "Bairro")
	private String bairro;
	@Column(name = "numero")
	@Resolvable(colName = "Numero")
	private String numero;
	@Column(name = "rg")
	@Resolvable(colName = "RG")
	private String rg;
	@Column(name = "ufrg")
	@Resolvable(colName = "UF RG")
	private String ufrg;
	@Column(name = "telefone1")
	@Resolvable(colName = "Telefone 01")
	private String telefone1;
	@Column(name = "telefone2")
	@Resolvable(colName = "Telefone 02")
	private String telefone2;
	@Column(name = "celular")
	@Resolvable(colName = "Celular")
	private String celular;

	@Column(name = "cep")
	@Resolvable(colName = "CEP")
	private String cep;

	@Column(name = "cidade")
	@Resolvable(colName = "Cidade")
	private String cidade;

	@Column(name = "uf")
	@Resolvable(colName = "UF")
	private String uf;

	@Column(name = "email")
	@Resolvable(colName = "E-mail")
	private String email;

	@Column(name = "site")
	@Resolvable(colName = "Site")
	private String site;

	@Column(name= "nomeFantasia")
	@Resolvable(colName = "Nome Fantasia")
	private String nomeFatasia;
	
	
	public String getNomeFatasia() {
		return nomeFatasia;
	}

	public void setNomeFatasia(String nomeFatasia) {
		this.nomeFatasia = nomeFatasia;
	}

	public String getRg() {
		return rg;
	}

	public String getUfrg() {
		return ufrg;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public String getCelular() {
		return celular;
	}

	public String getCep() {
		return cep;
	}

	public String getCidade() {
		return cidade;
	}

	public String getUf() {
		return uf;
	}

	public String getEmail() {
		return email;
	}

	public String getSite() {
		return site;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public void setUfrg(String ufrg) {
		this.ufrg = ufrg;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEndereço() {
		return endereço;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public String getNumero() {
		return numero;
	}

	// -------------------

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setEndereço(String endereço) {
		this.endereço = endereço;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	// -----------------

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getCancelado() {
		return cancelado;
	}

	public void setCancelado(String cancelado) {
		this.cancelado = cancelado;
	}

	public String getCausaCancel() {
		return causaCancel;
	}

	// -----------------------

	public void setCausaCancel(String causaCancel) {
		this.causaCancel = causaCancel;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}



}
