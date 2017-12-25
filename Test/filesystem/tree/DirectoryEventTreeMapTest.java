package filesystem.tree;

import filesystem.path.interfaces.compound.AbsoluteDirectory;
import org.junit.Test;

/**
 * Creator: Patrick
 * Created: 18.11.2017
 * Purpose:
 */
public class DirectoryEventTreeMapTest {

    @Test
    public void testCreation() throws Exception {
        String path = "D:\\";
        AbsoluteDirectory absDir = AbsoluteDirectory.from(path);
        DirectoryTreeMap tree = new DirectoryTreeMap(absDir);
        tree.updateRecursively();
    }
}