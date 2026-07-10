package danielebulgaru.GestionePrenotazioni.repositories;

import danielebulgaru.GestionePrenotazioni.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {
}
