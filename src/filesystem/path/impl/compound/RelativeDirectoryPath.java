package filesystem.path.impl.compound;

import filesystem.path.exception.PathsNotMatchingException;
import filesystem.path.impl.simple.RelativePathImpl;
import filesystem.path.interfaces.BasePath;
import filesystem.path.interfaces.compound.RelativeDirectory;
import filesystem.path.interfaces.compound.RelativeFile;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Creator: Patrick
 * Created: 02.08.2017
 * Purpose:
 */
public class RelativeDirectoryPath extends RelativePathImpl implements RelativeDirectory {

    protected RelativeDirectoryPath(Path path) {
        super(path);
    }

    protected RelativeDirectoryPath(String path) {
        super(path);
    }

    @Override
    public RelativeFilePath append(RelativeFile relFile) {
        Path combined = _path.resolve(relFile.getPath());
        return new RelativeFilePath(combined);
    }

    @Override
    public RelativeDirectoryPath append(RelativeDirectory relDir) {
        Path combined = _path.resolve(relDir.getPath());
        return new RelativeDirectoryPath(combined);
    }

    @Override
    public RelativeDirectoryPath remove(RelativeDirectory relDir) throws filesystem.path.exception.PathsNotMatchingException {
        String path = removeEnd(this, relDir);
        if (path == null) throw new PathsNotMatchingException();

        return new RelativeDirectoryPath(Paths.get(path));
    }

    @Override
    public RelativeDirectory copy() {
        return new RelativeDirectoryPath(this);
    }

    @Override
    public RelativeDirectoryPath getTop() {
        Path root = _path.getFileName();
        return new RelativeDirectoryPath(root);
    }

    @Override
    public String getRelativePath() {
        return _path.toString();
    }

    public static RelativeDirectoryPath from(Path path) {
        RelativeDirectoryPath relDir = null;

        if (!path.isAbsolute()){
            relDir = new RelativeDirectoryPath(path);
        }

        return relDir;
    }

    public static RelativeDirectoryPath from(String path) {
        return from(Paths.get(path));
    }
}
