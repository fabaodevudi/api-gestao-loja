package apigestaoloja.api.dtos;

public class UsuarioDto {
	
	private Long id;
	private String nome;
	private String telefone;
	private String cpf;
	private UsuarioLoginDto usuarioLoginDto;

	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UsuarioLoginDto getUsuarioLoginDto() {
		return usuarioLoginDto;
	}
	public void setUsuarioLoginDto(UsuarioLoginDto usuarioLoginDto) {
		this.usuarioLoginDto = usuarioLoginDto;
	}

	
}
