package danielebulgaru.GestionePrenotazioni.repositories;

import danielebulgaru.GestionePrenotazioni.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    // Per verificare se un'email è già presa
    Optional<Dipendente> findByEmail(String email);

    // Per verificare se l'username esiste già
    Optional<Dipendente> findByUsername(String username);
}