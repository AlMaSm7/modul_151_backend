package ch.bbw.pokeapi.repositories;

import ch.bbw.pokewakeybackend.model.Move;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface MoveRepo extends JpaRepository<Move, Long> {
    HashSet<Move> findByPokemonId(Long id);
}
