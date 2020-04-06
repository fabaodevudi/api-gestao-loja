package apigestaoloja.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import apigestaoloja.api.dtos.CategoriaLojaDto;
import apigestaoloja.api.dtos.UsuarioDto;

@Entity
@Table(name = "loja") 
public class Loja {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_loja", nullable = false)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@Column(name = "localizacao", nullable = false)
	private String localizacao;
	
	@ManyToOne
    @JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne
    @JoinColumn(name = "id_categoria")
	private CategoriaLoja categoriaLoja;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public CategoriaLoja getCategoriaLoja() {
		return categoriaLoja;
	}
	public void setCategoriaLoja(CategoriaLoja categoriaLoja) {
		this.categoriaLoja = categoriaLoja;
	}

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	

}
