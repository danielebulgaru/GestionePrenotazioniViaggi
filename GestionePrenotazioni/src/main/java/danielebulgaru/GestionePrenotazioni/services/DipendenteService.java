package danielebulgaru.GestionePrenotazioni.services;

import danielebulgaru.GestionePrenotazioni.entities.Dipendente;
import danielebulgaru.GestionePrenotazioni.exceptions.BadRequestException;
import danielebulgaru.GestionePrenotazioni.exceptions.NotFoundException;
import danielebulgaru.GestionePrenotazioni.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    // 1. SALVA UN NUOVO DIPENDENTE
    public Dipendente save(Dipendente body) {
        // Controlliamo se l'email esiste già
        if (dipendenteRepository.findByEmail(body.getEmail()).isPresent()) {
            throw new BadRequestException("L'email " + body.getEmail() + " è già in uso!");
        }
        // Controlliamo se lo username esiste già
        if (dipendenteRepository.findByUsername(body.getUsername()).isPresent()) {
            throw new BadRequestException("Lo username " + body.getUsername() + " è già in uso!");
        }
        return dipendenteRepository.save(body);
    }

    // 2. RECUPERA TUTTI I DIPENDENTI
    public List<Dipendente> findAll() {
        return dipendenteRepository.findAll();
    }

    // 3. RECUPERA UN DIPENDENTE PER ID
    public Dipendente findById(Long id) {
        return dipendenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dipendente con ID " + id + " non trovato!"));
    }

    // 4. MODIFICA UN DIPENDENTE
    public Dipendente findByIdAndUpdate(Long id, Dipendente body) {
        Dipendente found = this.findById(id);

        // Se l'utente cambia email, controlliamo che la nuova non sia già di qualcun altro
        if (!found.getEmail().equals(body.getEmail()) && dipendenteRepository.findByEmail(body.getEmail()).isPresent()) {
            throw new BadRequestException("La nuova email " + body.getEmail() + " è già in uso!");
        }

        found.setUsername(body.getUsername());
        found.setNome(body.getNome());
        found.setCognome(body.getCognome());
        found.setEmail(body.getEmail());
        if (body.getImmagineProfiloUrl() != null) {
            found.setImmagineProfiloUrl(body.getImmagineProfiloUrl());
        }

        return dipendenteRepository.save(found);
    }

    // 5. CANCELLA UN DIPENDENTE
    public void findByIdAndDelete(Long id) {
        Dipendente found = this.findById(id);
        dipendenteRepository.delete(found);
    }
}
