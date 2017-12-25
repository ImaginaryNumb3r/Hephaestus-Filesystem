package filesystem.path.impl;

import filesystem.path.exception.PathsNotMatchingException;
import filesystem.path.impl.compound.AbsoluteDirectoryPath;
import filesystem.path.impl.compound.RelativeDirectoryPath;
import filesystem.path.impl.simple.AbsolutePathImpl;
import filesystem.path.interfaces.BasePath;
import filesystem.path.interfaces.compound.AbsoluteDirectory;
import filesystem.path.interfaces.compound.RelativeDirectory;
import filesystem.path.interfaces.simple.AbsolutePath;
import org.jetbrains.annotations.NotNull;
import stream.streams.CharStream;
import stream.streams.StringCollector;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Patrick
 * @description
 * @since 14.06.2017
 */
public abstract class BasePathImpl implements BasePath {
    protected final Path _path;

    protected BasePathImpl(Path path) {
        this(path.toString());
    }

    protected BasePathImpl(String path) {
        _path = Paths.get(path);
    }

    public int length() {
        return _path.toString().length();
    }

    @Override
    public Path append(RelativeDirectory rel) {
        return null;
    }

    @Override
    public Path remove(RelativeDirectory removal) throws PathsNotMatchingException {
        return null;
    }

    @Override
    public Path copy() {
        return null;
    }

    @Override
    public Path getPath() {
        return _path;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof BasePath && equals((BasePath) other);
    }

    public boolean equals(BasePath other) {
        String otherPath = other.getPath().toString();
        return otherPath.equals(_path.toString());
    }

    @Override
    public int hashCode() {
        return getPath().hashCode();
    }

    @Override
    public String toString() {
        return getPath().toString();
    }

    @Override
    public RelativeDirectoryPath getParentDirectory() {
        Path parent = _path.getParent();
        Path parentName = parent.getFileName();
        return RelativeDirectoryPath.from(parentName);
    }

    @Override
    public boolean hasParent() {
        return _path.getParent() != null;
    }

    @Override
    public boolean isRoot() {
        return _path.getParent() == null;
    }

    protected String removeStart(AbsolutePath base, AbsolutePath removal) {
        return removeStart(base.toString(), removal.toString());
    }

    private String removeStart(String base, String removal) {
        String result = null;

        if (base.startsWith(removal)){
            /*
                In case both parts are equal, return empty string
                Otherwise remove new string from base (along with its preceding slash)
             */
            result = base.length() != removal.length()
                    ? base.substring(removal.length() + 1, base.length())
                    : "";
        }

        return result;
    }

    private String trimSlashes(String path){
        int i;
        for (i = 0; i != path.length() && (path.charAt(i) == '\\' || path.charAt(i) == '/'); ++i){ }

        return path.substring(0, path.length());
    }

    protected String removeEnd(BasePath base, BasePath removal) {
        return removeEnd(base.toString(), removal.toString());
    }

    protected String removeEnd(String base, String removal) {
        return base.endsWith(removal)
                ? base.substring(0, base.length() - removal.length() - 1)
                : null;
    }
}
