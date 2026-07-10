package danielebulgaru.GestionePrenotazioni.controllers;

import danielebulgaru.GestionePrenotazioni.payloads.DipendenteDTO;
import danielebulgaru.GestionePrenotazioni.entities.Dipendente;
import danielebulgaru.GestionePrenotazioni.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente createDipendente(@RequestBody @Validated DipendenteDTO body) {
        return dipendenteService.save(body);
    }

    @GetMapping
    public List<Dipendente> getAllDipendenti() { return dipendenteService.findAll(); }

    @GetMapping("/{id}")
    public Dipendente getDipendenteById(@PathVariable Long id) { return dipendenteService.findById(id); }

    @PutMapping("/{id}")
    public Dipendente updateDipendente(@PathVariable Long id, @RequestBody @Validated DipendenteDTO body) {
        return dipendenteService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDipendente(@PathVariable Long id) { dipendenteService.findByIdAndDelete(id); }
}
