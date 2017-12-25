package filesystem.path.interfaces.compound;

import filesystem.path.exception.PathsNotMatchingException;
import filesystem.path.impl.compound.AbsoluteFilePath;
import filesystem.path.interfaces.simple.AbsolutePath;
import filesystem.path.interfaces.simple.FilePath;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Creator: Patrick
 * Created: 02.08.2017
 * Purpose:
 */
public interface AbsoluteFile extends AbsolutePath, FilePath{

    @Override
    AbsoluteFile copy();

    // Absolute Path

    @Override
    RelativeFile remove(AbsoluteDirectory absDir) throws PathsNotMatchingException;

    // File Path

    @Override
    AbsoluteDirectory remove(RelativeFile relFile) throws PathsNotMatchingException;

    // Absolute Path + File Path

    @Override
    AbsoluteFile append(RelativeDirectory rel);

    @Override
    AbsoluteFile remove (RelativeDirectory removal) throws PathsNotMatchingException;

    // Absolute File

    RelativeDirectory remove(AbsoluteFile absFile) throws PathsNotMatchingException;

    static AbsoluteFile from(String path) {
        return from(Paths.get(path));
    }

    static AbsoluteFile from(Path path) {
        AbsoluteFile absFile = null;

        if (path != null){
            absFile = AbsoluteFilePath.from(path);
        }

        return absFile;
    }
}
