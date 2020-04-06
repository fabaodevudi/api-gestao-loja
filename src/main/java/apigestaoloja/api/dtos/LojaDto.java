package apigestaoloja.api.dtos;

public class LojaDto {

	private Long id;
	private String nome;
	private String descricao;
	private String localizacao;
	private UsuarioDto usuarioDto;
	private CategoriaLojaDto categoriaLojaDto;
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
	public UsuarioDto getUsuarioDto() {
		return usuarioDto;
	}
	public void setUsuarioDto(UsuarioDto usuarioDto) {
		this.usuarioDto = usuarioDto;
	}
	public CategoriaLojaDto getCategoriaLojaDto() {
		return categoriaLojaDto;
	}
	public void setCategoriaLojaDto(CategoriaLojaDto categoriaLojaDto) {
		this.categoriaLojaDto = categoriaLojaDto;
	}
	

}
