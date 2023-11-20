package med.voll.apiMedico.domain.paciente;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.apiMedico.domain.endereco.DadosEndereco;
import med.voll.apiMedico.domain.endereco.Endereco;
import med.voll.apiMedico.domain.medico.Especialidade;
import med.voll.apiMedico.domain.medico.Medico;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")
public class Paciente {

	@Id 
//	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="ID_PACIENTE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente")
	@SequenceGenerator(name="paciente", sequenceName="S_ID_PACIENTE",allocationSize = 1,initialValue = 1)
	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private String telefone;
	
	@Embedded
	private Endereco endereco;
	
	private Boolean ativo;
	
	public Paciente (DadosCadastroPaciente dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.telefone = dados.telefone();
		this.cpf = dados.cpf();
		this.endereco = new Endereco(dados.endereco());
		
		
	}

	public void atualizarInformacoes(@Valid DadosAtualizacaoPaciente dados) {
		if(dados.nome() != null) {
			this.nome = dados.nome();
		}
		
		if(dados.telefone() != null) {
			this.telefone = dados.telefone();
		}
		
		if(dados.endereco() !=  null) {
			this.endereco.atualizarInformacoes(dados.endereco());
		}
	}
	
	public void excluir() {
        this.ativo = false;
    }
	
	
}
