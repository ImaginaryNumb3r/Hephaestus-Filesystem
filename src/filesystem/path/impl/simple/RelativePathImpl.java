package filesystem.path.impl.simple;

import filesystem.path.exception.PathsNotMatchingException;
import filesystem.path.impl.BasePathImpl;
import filesystem.path.impl.compound.RelativeDirectoryPath;
import filesystem.path.impl.compound.RelativeFilePath;
import filesystem.path.interfaces.BasePath;
import filesystem.path.interfaces.compound.RelativeDirectory;
import filesystem.path.interfaces.simple.RelativePath;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Creator: Patrick
 * Created: 02.08.2017
 * Purpose:
 */
// TODO: Make only module visibility
public class RelativePathImpl extends BasePathImpl implements RelativePath{

    public RelativePathImpl(Path path) {
        super(path);
    }

    protected RelativePathImpl(String path) {
        super(path);
    }

    public String getRelativePath() {
        return _path.toString();
    }

    @Override
    public RelativePath copy(){
        return new RelativePathImpl(_path.toAbsolutePath());
    }

    @Override
    public BasePath getTop() {
        Path top = _path.getFileName();
        return new AbsolutePathImpl(top);
    }

    @Override
    public RelativePathImpl remove(RelativeDirectory removal) throws PathsNotMatchingException{
        String path = removeEnd(this, removal);
        if (path == null) throw new PathsNotMatchingException();

        return new RelativePathImpl(Paths.get(path));
    }

    @Override
    public RelativePathImpl append(RelativeDirectory rel){
        Path combined = _path.resolve(rel.getPath());
        return new RelativePathImpl(combined);
    }

    @Override
    public RelativeDirectoryPath getParentPath() {
        Path parent = _path.getParent();
        return RelativeDirectoryPath.from(parent);
    }
}
