package edu.hw10.Test10_1.generators;
import edu.hw10.Task10_1.annotations.Generate;
import edu.hw10.Task10_1.annotations.Generator;
import edu.hw10.Task10_1.provider.FileStringProvider;
import edu.hw10.Task10_1.provider.IntegerProvider;
import edu.hw10.Task10_1.provider.UUIDProvider;
import edu.hw10.Test10_1.dto.Player;
import java.util.UUID;
@Generator
public class PlayerGenerator {
    public static final int N_PLAYERS = 100;

    @Generate(provider = UUIDProvider.class, unique = true, min = 1, max = N_PLAYERS + 1)
    UUID uuid;


    @Generate(provider = FileStringProvider.class, unique = true, source = "player-names.dta", quantity = N_PLAYERS)
    String nickname;

    @Generate(provider = IntegerProvider.class)
    int blocksMined;

    public Player getDto() {
        return new Player(uuid, nickname, blocksMined);
    }
}
