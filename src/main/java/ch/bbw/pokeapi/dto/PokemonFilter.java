package ch.bbw.pokeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PokemonFilter {
    private String name;
    private Long count;
}
