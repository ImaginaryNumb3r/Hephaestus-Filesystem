package filesystem.tree;

import filesystem.path.interfaces.compound.AbsoluteDirectory;
import org.junit.Test;

/**
 * Creator: Patrick
 * Created: 19.11.2017
 * Purpose:
 */
public class DirectoryComparerTest {

    @Test
    public void testCompare() throws Exception {
        String path = "D:\\Spiele\\Legacy\\testdir";
        String path2 = "D:\\Spiele\\Legacy\\testdir2";
        DirectoryTreeMap primaryTree = new DirectoryTreeMap(AbsoluteDirectory.from(path));
        DirectoryTreeMap secondaryTree = new DirectoryTreeMap(AbsoluteDirectory.from(path2));
        primaryTree.updateRecursively();
        secondaryTree.updateRecursively();

        DirectoryComparer<DirectoryTreeMap> comparer = new DirectoryComparer<>(primaryTree);
        comparer.compareTo(secondaryTree);
    }
}
