package filesystem.path.interfaces.simple;

import filesystem.path.exception.PathsNotMatchingException;
import filesystem.path.interfaces.BasePath;
import filesystem.path.interfaces.compound.RelativeDirectory;
import filesystem.path.interfaces.compound.RelativeFile;

/**
 * @author Patrick
 * @description
 * @since 14.06.2017
 */
public interface DirectoryPath extends BasePath {

    // Path

    DirectoryPath append(RelativeDirectory rel);

    DirectoryPath remove (RelativeDirectory removal) throws PathsNotMatchingException;

    // Directory Path

    FilePath append(RelativeFile relFile);

    DirectoryPath copy();

}
