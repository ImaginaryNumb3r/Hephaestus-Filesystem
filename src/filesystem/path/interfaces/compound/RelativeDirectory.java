package filesystem.path.interfaces.compound;

import filesystem.path.exception.PathsNotMatchingException;
import filesystem.path.impl.compound.RelativeDirectoryPath;
import filesystem.path.interfaces.simple.DirectoryPath;
import filesystem.path.interfaces.simple.RelativePath;

import java.nio.file.Path;

/**
 * Creator: Patrick
 * Created: 02.08.2017
 * Purpose:
 */
public interface RelativeDirectory extends RelativePath, DirectoryPath{

    // Directory Path

    @Override
    RelativeFile append(RelativeFile relFile);

    // Relative Path + Directory Path

    @Override
    RelativeDirectory append(RelativeDirectory rel);

    @Override
    RelativeDirectory remove (RelativeDirectory removal) throws PathsNotMatchingException;

    @Override
    RelativeDirectory copy();

    static RelativeDirectory from(String path){
        return RelativeDirectoryPath.from(path);
    }

    static RelativeDirectory from(Path path){
        return RelativeDirectoryPath.from(path);
    }
}
