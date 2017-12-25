package filesystem.path.interfaces.compound;

import filesystem.path.exception.PathsNotMatchingException;
import filesystem.path.impl.compound.RelativeDirectoryPath;
import filesystem.path.impl.compound.RelativeFilePath;
import filesystem.path.interfaces.simple.FilePath;
import filesystem.path.interfaces.simple.RelativePath;

import java.nio.file.Path;

/**
 * Creator: Patrick
 * Created: 02.08.2017
 * Purpose:
 */
public interface RelativeFile extends RelativePath, FilePath {

    @Override
    RelativeFile append(RelativeDirectory rel);

    @Override
    RelativeFile remove(RelativeDirectory removal) throws PathsNotMatchingException;

    @Override
    RelativeFile copy();

    static RelativeFile from(String path){
        return RelativeFilePath.from(path);
    }

    static RelativeFile from(Path path){
        return RelativeFilePath.from(path);
    }
}
