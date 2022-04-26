package file.readers;

import org.junit.jupiter.api.Test;
import world.State;
import world.World;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapReaderTest {

    File imageFile = new File("resources/europeMapProvinces.png");
    MapReader mapReader = new MapReader();
    World world = new World();

    @Test
    public void getStatesTest(){


        List<State> states = mapReader.getStates(imageFile, world);
        System.out.println(states.size());
        assertTrue(states.size() > 100);

        }
}
