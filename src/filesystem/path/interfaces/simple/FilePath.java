package filesystem.path.interfaces.simple;

import filesystem.path.interfaces.BasePath;
import filesystem.path.interfaces.compound.RelativeDirectory;
import filesystem.path.interfaces.compound.RelativeFile;
import filesystem.path.exception.PathsNotMatchingException;

import java.util.Optional;


/**
 * @author Patrick
 * @description
 * @since 14.06.2017
 */
public interface FilePath extends BasePath {

    // Path

    FilePath append(RelativeDirectory rel);

    FilePath remove (RelativeDirectory removal) throws PathsNotMatchingException;

    // File Path

    DirectoryPath remove(RelativeFile relFile) throws PathsNotMatchingException;

    Optional<String> getPostfix();

    FilePath copy();

//    boolean isSubPath(RelativeFile filePath);
}
