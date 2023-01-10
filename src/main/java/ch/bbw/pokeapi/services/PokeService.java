package ch.bbw.pokeapi.services;

import ch.bbw.pokeapi.dto.PokemonFilter;
import ch.bbw.pokeapi.model.LikedPokemon;
import ch.bbw.pokeapi.repositories.PokeRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PokeService {
    private PokeRepo repo;

    public List<PokemonFilter> pokemons() {
        System.out.println(Date.valueOf(LocalDate.now()));
        List<LikedPokemon> pokemons = repo.findAllByDateTime(Date.valueOf(LocalDate.now()));
        pokemons.forEach(System.out::println);
        return pokemons.stream()
                .collect(Collectors.groupingBy(LikedPokemon::getName, Collectors.counting()))
                .entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(10)
                .map(e -> new PokemonFilter(e.getKey(), e.getValue()))
                .toList();
    }

    public void addPokemon(LikedPokemon likedPokemon) {
        likedPokemon.setDateTime(Date.valueOf(LocalDate.now()));
        repo.save(likedPokemon);
    }
}