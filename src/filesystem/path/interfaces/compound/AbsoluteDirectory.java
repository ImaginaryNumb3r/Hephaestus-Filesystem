package filesystem.path.interfaces.compound;

import filesystem.files.FileEntry;
import filesystem.path.exception.PathsNotMatchingException;
import filesystem.path.impl.compound.AbsoluteDirectoryPath;
import filesystem.path.interfaces.simple.AbsolutePath;
import filesystem.path.interfaces.simple.DirectoryPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @author Patrick
 * @description
 * @since 14.06.2017
 */
public interface AbsoluteDirectory extends AbsolutePath, DirectoryPath{

    @Override
    AbsoluteDirectory copy();

    // Absolute Path

    @Override
    RelativeDirectory remove(AbsoluteDirectory absDir) throws PathsNotMatchingException;

    // Directory Path

    @Override
    AbsoluteFile append(RelativeFile relFile);

    // Absolute Path + Directory Path

    @Override
    AbsoluteDirectory append(RelativeDirectory rel);

    @Override
    AbsoluteDirectory remove (RelativeDirectory removal) throws PathsNotMatchingException;

    static AbsoluteDirectory from(Path path) {
        return AbsoluteDirectoryPath.from(path);
    }

    static AbsoluteDirectory from(String path) {
        return AbsoluteDirectoryPath.from(path);
    }
}
