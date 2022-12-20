package it.prova.materialapirest.repository.ruolo;

import org.springframework.data.repository.CrudRepository;

import it.prova.materialapirest.model.Ruolo;


public interface RuoloRepository extends CrudRepository<Ruolo, Long>{
	Ruolo findByDescrizioneAndCodice(String descrizione, String codice);
	
	Ruolo findByCodice(String codice);


}
