package filesystem.path.impl.compound;

import core.exception.NoImplementationException;
import filesystem.path.exception.PathsNotMatchingException;
import filesystem.path.impl.simple.FilePathHelper;
import filesystem.path.impl.simple.RelativePathImpl;
import filesystem.path.interfaces.compound.RelativeDirectory;
import filesystem.path.interfaces.compound.RelativeFile;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Creator: Patrick
 * Created: 03.08.2017
 * Purpose:
 */
public class RelativeFilePath extends RelativePathImpl implements RelativeFile, FilePathHelper {

    protected RelativeFilePath(Path path) {
        super(path);
    }

    protected RelativeFilePath(String path) {
        super(path);
    }

    @Override
    public RelativeFilePath append(RelativeDirectory rel) {
        Path combined = _path.resolve(rel.getPath());
        return new RelativeFilePath(combined);
    }

    @Override
    public RelativeFilePath remove(RelativeDirectory relDir) throws PathsNotMatchingException {
        String path = removeEnd(this, relDir);
        if (path == null) throw new NoImplementationException();

        return new RelativeFilePath(Paths.get(path));
    }

    @Override
    public RelativeDirectoryPath remove(RelativeFile relFile) throws PathsNotMatchingException {
        Path combined = _path.resolve(relFile.getPath());
        return new RelativeDirectoryPath(combined);
    }

    @Override
    public RelativeFile copy() {
        return new RelativeFilePath(this);
    }

    @Override
    public RelativeFilePath getTop() {
        Path root = _path.getFileName();
        return new RelativeFilePath(root);
    }

    public static RelativeFilePath from(String relativePath) {
        return from(Paths.get(relativePath));
    }

    public static RelativeFilePath from(Path path) {
        RelativeFilePath relFile = null;

        if (!path.isAbsolute()){
            relFile = new RelativeFilePath(path);
        }

        return relFile;
    }
}
