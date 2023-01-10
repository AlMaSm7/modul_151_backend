package ch.bbw.pokeapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "speciesId")
    private Long speciesId;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "ability")
    private String ability;

    @Column(name = "level")
    private int level;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "pokemon_moves",
            joinColumns = {
                    @JoinColumn(name = "pokemon_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "move_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Move> moves = new HashSet<>();

    public Pokemon(String nickname, String ability, int level) {
        this.nickname = nickname;
        this.ability = ability;
        this.level = level;
    }
}