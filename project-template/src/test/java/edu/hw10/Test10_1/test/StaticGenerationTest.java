package edu.hw10.Test10_1.test;

import edu.hw10.Task10_1.util.JROFactory;
import edu.hw10.Test10_1.dto.Player;
import edu.hw10.Test10_1.generators.PlayerGenerator;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.UUID;
import javax.naming.ConfigurationException;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StaticGenerationTest {

    @Test
    public void embeddingTest() throws ConfigurationException {
        Iterator<PlayerGenerator> iterator = JROFactory.create(PlayerGenerator.class).iterator();
        TreeMap<UUID, Player> playerMap = new TreeMap<>();

        while (iterator.hasNext()) {
            Player p = iterator.next().getDto();
            playerMap.put(p.getUuid(), p);
        }

        for (Player player : playerMap.values()) {
            assertNotNull(player);
            System.out.println(player);
        }
        assertEquals(PlayerGenerator.N_PLAYERS, playerMap.values().size());
    }
}
