package ch.bbw.pokeapi.repositories;

import ch.bbw.pokeapi.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface PokemonRepo extends JpaRepository<Pokemon, Long> {

    List<Pokemon> findAllByDateTime(Date time);
}
