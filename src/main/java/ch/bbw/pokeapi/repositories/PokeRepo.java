package ch.bbw.pokeapi.repositories;

import ch.bbw.pokeapi.model.LikedPokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface PokeRepo extends JpaRepository<LikedPokemon, UUID> {

    List<LikedPokemon> findAllByDateTime(Date time);
}
