package danielebulgaru.GestionePrenotazioni.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "viaggio_id")
    private Viaggio viaggio;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

    @NotNull
    private LocalDate dataRichiesta;

    private String note;

    public Prenotazione() {}

    public Prenotazione(Viaggio viaggio, Dipendente dipendente, LocalDate dataRichiesta, String note) {
        this.viaggio = viaggio;
        this.dipendente = dipendente;
        this.dataRichiesta = dataRichiesta;
        this.note = note;
    }

    // Getter e Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Viaggio getViaggio() { return viaggio; }
    public void setViaggio(Viaggio viaggio) { this.viaggio = viaggio; }
    public Dipendente getDipendente() { return dipendente; }
    public void setDipendente(Dipendente dipendente) { this.dipendente = dipendente; }
    public LocalDate getDataRichiesta() { return dataRichiesta; }
    public void setDataRichiesta(LocalDate dataRichiesta) { this.dataRichiesta = dataRichiesta; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
