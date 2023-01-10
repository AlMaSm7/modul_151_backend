package ch.bbw.pokeapi.services;

import ch.bbw.pokeapi.model.Move;
import ch.bbw.pokeapi.repositories.MoveRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MoveService {
    private final MoveRepo moveRepo;

    //public List<Move> getPokemon() {
    //    return moveRepo.findAll();
    //}

    public Move createNewMove(Long id) {
        Move move = new Move(id);
        moveRepo.save(move);
        return move;
    }
}
