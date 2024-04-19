package riccardo.U5W3D5.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record EventDTO(@NotEmpty(message = "Il titolo è obbligatorio")
                       @Size(min = 1 , max = 20, message = "Il titolo deve avere la lunghezza dei caratteri compresa tra 1 e 20")
                       String title,
                       @NotEmpty(message = "La descrizione è obbligatoria")
                       @Size(min = 1 , max = 100, message = "La descrizione deve avere la lunghezza dei caratteri compresa tra 1 e 100")
                       String description,
                       @NotNull (message = "La data è obbligatoria")
                       LocalDate date,
                       @NotEmpty(message = "Il luogo è obbligatorio")
                       @Size(min = 1 , max = 20, message = "Il luogo deve avere la lunghezza dei caratteri compresa tra 1 e 20")
                       String place,
                       @Min(1)
                       @Max(100)
                       int numberOfPlacesAvailable) {
}
