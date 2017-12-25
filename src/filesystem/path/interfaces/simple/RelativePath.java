package filesystem.path.interfaces.simple;

import filesystem.path.interfaces.BasePath;
import filesystem.path.interfaces.compound.AbsoluteDirectory;
import filesystem.path.interfaces.compound.RelativeDirectory;
import filesystem.path.exception.PathsNotMatchingException;


/**
 * @author Patrick
 * @description
 * @since 14.06.2017
 */
public interface RelativePath extends BasePath {

    // Relative Path

    RelativePath append(RelativeDirectory rel);

    RelativePath remove (RelativeDirectory removal) throws PathsNotMatchingException;

    String getRelativePath();

    RelativePath copy();

//    boolean isSubPath(RelativePath filePath);

    RelativeDirectory getParentPath();

}
