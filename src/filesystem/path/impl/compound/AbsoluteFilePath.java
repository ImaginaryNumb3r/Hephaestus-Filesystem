package filesystem.path.impl.compound;

import core.util.contracts.Contract;
import filesystem.path.exception.PathsNotMatchingException;
import filesystem.path.impl.simple.AbsolutePathImpl;
import filesystem.path.impl.simple.FilePathHelper;
import filesystem.path.interfaces.compound.AbsoluteDirectory;
import filesystem.path.interfaces.compound.AbsoluteFile;
import filesystem.path.interfaces.compound.RelativeDirectory;
import filesystem.path.interfaces.compound.RelativeFile;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Creator: Patrick
 * Created: 02.08.2017
 * Purpose:
 */
public class AbsoluteFilePath extends AbsolutePathImpl implements AbsoluteFile, FilePathHelper {

    protected AbsoluteFilePath(Path path) {
        super(path);
    }

    protected AbsoluteFilePath(AbsoluteDirectory absDir, RelativeFile relFile) {
        super(Paths.get(absDir.toString(), relFile.toString()));
    }

    @Override
    public AbsoluteFilePath copy() {
        return new AbsoluteFilePath(this);
    }

    @Override
    public RelativeFilePath getTop() {
        Path root = _path.getFileName();
        return new RelativeFilePath(root);
    }

    @Override
    public boolean hasParent() {
        return _path.getParent() != null;
    }

    @Override
    public RelativeDirectoryPath getParentDirectory() {
        Path parent = _path.getParent();
        Path parentName = parent.getFileName();
        return new RelativeDirectoryPath(parentName);    }

    @Override
    public AbsoluteDirectoryPath getParentPath() {
        return new AbsoluteDirectoryPath(_path.getParent());
    }

    @Override
    public RelativeFilePath remove(AbsoluteDirectory removal) throws PathsNotMatchingException {
        String path = removeStart(this, removal);
        if (path == null) throw new PathsNotMatchingException();

        return new RelativeFilePath(Paths.get(path));
    }

    @Override
    public AbsoluteDirectoryPath remove(RelativeFile removal) throws PathsNotMatchingException {
        String path = removeEnd(this, removal);
        if (path == null) throw new PathsNotMatchingException();

        return new AbsoluteDirectoryPath(Paths.get(path));
    }

    @Override
    public AbsoluteFilePath append(RelativeDirectory rel) {
        Path combined = _path.resolve(rel.getPath());
        return new AbsoluteFilePath(combined);
    }

    @Override
    public AbsoluteFilePath remove(RelativeDirectory removal) throws PathsNotMatchingException {
        String fileName = getTop().toString();

        String path = removeEnd(getParentPath(), removal);
        if (path == null) throw new PathsNotMatchingException();

        return new AbsoluteFilePath(Paths.get(path, fileName));
    }

    @Override
    public RelativeDirectory remove(AbsoluteFile removal) throws PathsNotMatchingException {
        String path = removeStart(this, removal.getParentPath());
        if (path == null) throw new PathsNotMatchingException();

        path = removeEnd(path, removal.getTop().toString());
        if (path == null) throw new PathsNotMatchingException();

        return new RelativeDirectoryPath(Paths.get(path));
    }

    public static AbsoluteFile from(Path path) {
        Contract.checkNull(path, "path");
        if (!path.isAbsolute()) {
            throw new IllegalArgumentException("Cannot create absolute file from non-absolute path");
        }

        return new AbsoluteFilePath(path);
    }
}
