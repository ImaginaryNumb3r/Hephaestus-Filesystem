package filesystem.path.impl.compound;

import filesystem.path.interfaces.compound.AbsoluteDirectory;
import filesystem.path.interfaces.compound.RelativeDirectory;
import junit.framework.TestCase;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Creator: Patrick
 * Created: 03.08.2017
 * Purpose:
 */
public class AbsoluteDirectoryPathTest extends TestCase {
    private static final String ABSOLUTE_PATH = "D:\\Main\\Sub";
    private static final String PARENT_DIR_OF_VALID_PATH = "Main";
    private static final String PARENT_PATH_OF_VALID_PATH = "D:\\Main";
    private static final String RELATIVE_PATH = "new";
    private static final String RELATIVE_FILE = "new.txt";
    private static final String SIMPLE_ROOT = "D:\\";
    private static final String INVALID_PATH = "invalid";

    public void testValidPath() throws Exception {
        Path path = Paths.get(ABSOLUTE_PATH);
        AbsoluteDirectoryPath absDirStr = AbsoluteDirectoryPath.from(ABSOLUTE_PATH);
        AbsoluteDirectoryPath absDirPath = AbsoluteDirectoryPath.from(path);

        assert absDirStr != null;
        assert absDirPath != null;
    }

    public void testInvalidPath() throws Exception {
        Path path = Paths.get(INVALID_PATH);
        AbsoluteDirectoryPath absDirStr = AbsoluteDirectoryPath.from(INVALID_PATH);
        AbsoluteDirectoryPath absDirPath = AbsoluteDirectoryPath.from(path);

        assert absDirStr == null;
        assert absDirPath == null;
    }

    public void testisRoot() throws Exception {
        AbsoluteDirectoryPath absDirPath = AbsoluteDirectoryPath.from(SIMPLE_ROOT);

        assert absDirPath.isRoot();
        assert !absDirPath.hasParent();
    }

    public void testHasParent() throws Exception {
        AbsoluteDirectoryPath absDirPath = AbsoluteDirectoryPath.from(ABSOLUTE_PATH);

        assert !absDirPath.isRoot();
        assert absDirPath.hasParent();
    }

    public void testParentDirectory() throws Exception {
        AbsoluteDirectoryPath absDirPath = AbsoluteDirectoryPath.from(ABSOLUTE_PATH);
        final RelativeDirectoryPath expected = RelativeDirectoryPath.from(PARENT_DIR_OF_VALID_PATH);

        RelativeDirectory result = absDirPath.getParentDirectory();
        assert result.equals(expected);
    }

    public void testParentPath() throws Exception {
        AbsoluteDirectoryPath absDirPath = AbsoluteDirectoryPath.from(ABSOLUTE_PATH);
        final AbsoluteDirectoryPath expected = AbsoluteDirectoryPath.from(PARENT_PATH_OF_VALID_PATH);

        AbsoluteDirectoryPath result = absDirPath.getParentPath();
        assert result.equals(expected);
    }

    public void testCopy() throws Exception {
        AbsoluteDirectoryPath path = AbsoluteDirectoryPath.from(ABSOLUTE_PATH);
        AbsoluteDirectoryPath copy = path.copy();

        boolean equals = path.equals(copy);
        assert equals;
    }

    public void testAppendDirectory() throws Exception {
        RelativeDirectoryPath relDir = RelativeDirectoryPath.from(RELATIVE_PATH);
        AbsoluteDirectoryPath absDir = AbsoluteDirectoryPath.from(ABSOLUTE_PATH);
        final String expectedPath = ABSOLUTE_PATH + "\\" + RELATIVE_PATH;

        AbsoluteDirectoryPath resultedPath = absDir.append(relDir);
        assert expectedPath.equals(resultedPath.toString());
    }

    public void testAppendFile() throws Exception {
        RelativeFilePath relFile = RelativeFilePath.from(RELATIVE_FILE);
        AbsoluteDirectoryPath absDir = AbsoluteDirectoryPath.from(ABSOLUTE_PATH);
        final String expectedPath = ABSOLUTE_PATH + "\\" + RELATIVE_FILE;

        AbsoluteFilePath resultedPath = absDir.append(relFile);
        assert expectedPath.equals(resultedPath.toString());
    }

    public void testRemoveRelativeSubPath() throws Exception {
        AbsoluteDirectoryPath absDir = AbsoluteDirectoryPath.from(ABSOLUTE_PATH);
        RelativeDirectoryPath parentDir = absDir.getTop();
        final AbsoluteDirectoryPath expected = absDir.getParentPath();

        AbsoluteDirectoryPath result = absDir.remove(parentDir);

        assert expected.equals(result);
    }

    public void testRemoveAbsoluteSubPath() throws Exception {
        AbsoluteDirectoryPath absDir = AbsoluteDirectoryPath.from(ABSOLUTE_PATH);
        AbsoluteDirectory parentDir = absDir.getParentPath();
        final RelativeDirectoryPath expected = RelativeDirectoryPath.from(absDir.getTop());

        RelativeDirectoryPath result = absDir.remove(parentDir);

        assert expected.equals(result);
    }
}