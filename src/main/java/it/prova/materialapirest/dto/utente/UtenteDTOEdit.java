package it.prova.materialapirest.dto.utente;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.materialapirest.model.Ruolo;
import it.prova.materialapirest.model.Utente;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UtenteDTOEdit {

	private Long id;

	@NotBlank(message = "{username.notblank}")
	@Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String username;

	@NotBlank(message = "{nome.notblank}")
	private String nome;

	@NotBlank(message = "{cognome.notblank}")
	private String cognome;

	private Date dataNascita;

	private Long[] ruoliIds;

	public UtenteDTOEdit() {
	}

	public UtenteDTOEdit(Long id, String username, String nome, String cognome) {
		super();
		this.id = id;
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
	}

	public UtenteDTOEdit(Long id, String username, String nome, String cognome, Date dataNascita) {
		super();
		this.id = id;
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Long[] getRuoliIds() {
		return ruoliIds;
	}

	public void setRuoli(Long[] ruoliIds) {
		this.ruoliIds = ruoliIds;
	}

	public Utente buildUtenteModel(boolean includeRoles) {
		Utente result = new Utente(this.id, this.username, this.nome, this.cognome,this.dataNascita);
		if (includeRoles && ruoliIds != null)
			result.setRuoli(Arrays.asList(ruoliIds).stream().map(id -> new Ruolo(id)).collect(Collectors.toSet()));

		return result;
	}

	public static UtenteDTOEdit buildUtenteDTOFromModel(Utente utenteModel) {
		UtenteDTOEdit result = new UtenteDTOEdit(utenteModel.getId(), utenteModel.getUsername(), utenteModel.getNome(),
				utenteModel.getCognome(), utenteModel.getDataNascita());

		if (!utenteModel.getRuoli().isEmpty())
			result.ruoliIds = utenteModel.getRuoli().stream().map(r -> r.getId()).collect(Collectors.toList())
					.toArray(new Long[] {});

		return result;
	}

}
