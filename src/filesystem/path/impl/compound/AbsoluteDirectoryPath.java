package filesystem.path.impl.compound;

import core.util.contracts.Contract;
import filesystem.path.exception.PathsNotMatchingException;
import filesystem.path.impl.simple.AbsolutePathImpl;
import filesystem.path.interfaces.compound.AbsoluteDirectory;
import filesystem.path.interfaces.compound.RelativeDirectory;
import filesystem.path.interfaces.compound.RelativeFile;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Patrick
 * @description
 * @since 14.06.2017
 */
public class AbsoluteDirectoryPath extends AbsolutePathImpl implements AbsoluteDirectory {

    //<editor-fold desc="Construction">
    protected AbsoluteDirectoryPath(Path path) {
        super(path);
    }

    /**
     * Factory method for paths that point to an absolute directory
     * @param path that is an absolute path in string form
     * @return  null if {@code path} is not a valid absolute path.<br>
     *          Otherwise returns the AbsoluteDirectoryPath
     */
    public static AbsoluteDirectoryPath from(String path){
        return from(Paths.get(path));
    }

    /**
     * Factory method for paths that point to an absolute directory
     * @param path that is an absolute path in java.nio.Path form
     * @return  null if {@code path} is not a valid absolute path.<br>
     *          Otherwise returns the AbsoluteDirectoryPath
     */
    public static AbsoluteDirectoryPath from(Path path){
        Contract.checkNull(path, "path");/*
        if (!path.isAbsolute()) {
            throw new IllegalArgumentException("Cannot create absolute file from non-absolute path");
        }*/

        return path.isAbsolute() ?
                new AbsoluteDirectoryPath(path)
                : null;
    }
    //</editor-fold>

    @Override
    public AbsoluteDirectoryPath copy() {
        return new AbsoluteDirectoryPath(this);
    }

    @Override
    public RelativeDirectoryPath getTop() {
        Path top = _path.getFileName();
        return new RelativeDirectoryPath(top);
    }

    @Override
    public AbsoluteDirectoryPath getParentPath() {
        return new AbsoluteDirectoryPath(_path.getParent());
    }

    @Override
    public AbsoluteFilePath append(RelativeFile relFile) {
        Path combined = _path.resolve(relFile.getPath());
        return new AbsoluteFilePath(combined);
    }

    @Override
    public AbsoluteDirectoryPath append(RelativeDirectory rel) {
        Path combined = _path.resolve(rel.getPath());

        return new AbsoluteDirectoryPath(combined);
    }

    @Override
    public AbsoluteDirectoryPath remove(RelativeDirectory removal) throws PathsNotMatchingException {
        String path = removeEnd(this, removal);
        if (path == null) throw new PathsNotMatchingException();

        return new AbsoluteDirectoryPath(Paths.get(path));
    }

    @Override
    public RelativeDirectoryPath remove(AbsoluteDirectory removal) throws PathsNotMatchingException {
        String path = removeStart(this, removal);
        if (path == null) throw new PathsNotMatchingException();

        return new RelativeDirectoryPath(Paths.get(path));
    }
}
