package ch.bbw.pokeapi.controller;

import ch.bbw.pokeapi.dto.PokemonFilter;
import ch.bbw.pokeapi.model.LikedPokemon;
import ch.bbw.pokeapi.services.PokeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pokemon")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class MainController {

    private PokeService service;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<PokemonFilter>> getLikesPokemons(){
        List<PokemonFilter> pokemonList = service.pokemons();
        if(pokemonList.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok().body(pokemonList);
        }
    }

    @PostMapping(produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LikedPokemon> addLikedPokemon(@RequestBody LikedPokemon pokemon){
        log.info(pokemon.toString());
        service.addPokemon(pokemon);
        return ResponseEntity.ok().body(pokemon);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error(String.valueOf(ex));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
