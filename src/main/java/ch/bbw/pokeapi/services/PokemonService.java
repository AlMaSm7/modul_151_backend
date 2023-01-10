package ch.bbw.pokeapi.services;

import ch.bbw.pokeapi.model.Move;
import ch.bbw.pokeapi.model.Pokemon;
import ch.bbw.pokeapi.repositories.MoveRepo;
import ch.bbw.pokeapi.repositories.PokeRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PokemonService {
    private final PokeRepo pokeRepo;
    private final MoveRepo moveRepo;

    public List<Pokemon> getPokemon() {
        return pokeRepo.findAll();
    }

    public Optional<Pokemon> getPokemonById(long id) {
        return pokeRepo.findById(id);
    }

    public Pokemon createNewPokemon(Pokemon pokemon) {
        pokeRepo.save(pokemon);
        return pokemon;
    }

    @Transactional
    public Pokemon deletePokemon(long id) {
        Optional<Pokemon> pokemon1 = pokeRepo.findById(id);
        if (pokemon1.isPresent()) {
            HashSet<Move> moves = moveRepo.findByPokemonId(pokemon1.get().getId());
            moves.forEach(move -> {
                moveRepo.findById(move.getId());
            });
            pokeRepo.delete(pokemon1.get());
        }
        return pokemon1.get();
    }

    public Pokemon updatePokemon(Pokemon pokemon) {
        Optional<Pokemon> pokemonToUpdate = pokeRepo.findById(pokemon.getId());
        Pokemon           updatedPokemon;
        if (pokemonToUpdate.isPresent()) {
            updatedPokemon = pokemonToUpdate.get();
            //Add edited values to User found in db
            updatedPokemon.setAbility(pokemon.getAbility());
            updatedPokemon.setNickname(pokemon.getNickname());
            updatedPokemon.setSpeciesId(pokemon.getSpeciesId());
            updatedPokemon.setMoves(pokemon.getMoves());
            pokeRepo.save(updatedPokemon);

        } else {
            updatedPokemon = null;
        }
        return updatedPokemon;
    }

}
