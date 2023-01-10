package ch.bbw.pokeapi.controller;

import ch.bbw.pokeapi.model.Pokemon;
import ch.bbw.pokeapi.services.PokemonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/pokemon")
public class PokemonController {
    private final PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<Pokemon>> getPokemon() {
        List<Pokemon>                 pokemon = pokemonService.getPokemon();
        ResponseEntity<List<Pokemon>> res;
        if (pokemon.isEmpty()) {
            res = ResponseEntity.noContent().build();
        } else {
            res = ResponseEntity.ok().body(pokemon);
        }
        return res;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePokemon(@PathVariable long id) {
        // Delete user
        ResponseEntity<String> res;
        try {
            Pokemon pokemon = pokemonService.deletePokemon(id);
            if (pokemon == null) {
                res = ResponseEntity.notFound().build();
            } else {
                res = ResponseEntity.ok().body(pokemon.getNickname() + " Pokemon deleted successfully");
            }
            return res;
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("There was an issue deleting the pokemon");
        }
    }

    @PutMapping
    public ResponseEntity<String> updatePokemon(@RequestBody Pokemon pokemon) {
        ResponseEntity<String> res;
        try {
            if (pokemonService.updatePokemon(pokemon) == null) {
                res = ResponseEntity.notFound().build();
            } else {
                res = ResponseEntity.ok().body(pokemon.getNickname() + "User updated successfully");
            }
            return res;
        } catch (HttpMessageNotWritableException e) {
            return ResponseEntity.internalServerError().body("There was an issue updating the user");
        }
    }
}
