package filesystem.path.interfaces.simple;

import filesystem.path.exception.PathsNotMatchingException;
import filesystem.path.impl.simple.AbsolutePathImpl;
import filesystem.path.interfaces.BasePath;
import filesystem.path.interfaces.compound.AbsoluteDirectory;
import filesystem.path.interfaces.compound.RelativeDirectory;

import java.nio.file.Path;

/**
 * @author Patrick
 * @description
 * @since 14.06.2017
 */
public interface AbsolutePath extends BasePath {

    // Path

    @Override
    AbsolutePath append(RelativeDirectory rel);

    @Override
    AbsolutePath remove (RelativeDirectory removal) throws PathsNotMatchingException;

    // Absolute Path

    RelativePath remove(AbsoluteDirectory absDir) throws PathsNotMatchingException;

    // Absolute Path

    String getAbsolutePath();

    @Override
    AbsolutePath copy();

    AbsoluteDirectory getParentPath();

    // Factory Method

    static AbsolutePath from(Path path){
        return new AbsolutePathImpl(path);
    }

}
