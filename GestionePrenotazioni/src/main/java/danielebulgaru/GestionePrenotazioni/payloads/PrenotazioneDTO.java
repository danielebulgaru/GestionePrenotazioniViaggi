package danielebulgaru.GestionePrenotazioni.payloads;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record PrenotazioneDTO(
        @NotNull(message = "L'ID del dipendente è obbligatorio")
        Long dipendenteId,

        @NotNull(message = "L'ID del viaggio è obbligatorio")
        Long viaggioId,

        @NotNull(message = "La data della richiesta è obbligatoria")
        LocalDate dataRichiesta,

        String note
) {}
