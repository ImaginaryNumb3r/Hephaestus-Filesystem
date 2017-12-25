package filesystem.path.impl.simple;

import filesystem.path.exception.PathsNotMatchingException;
import filesystem.path.impl.BasePathImpl;
import filesystem.path.impl.compound.AbsoluteDirectoryPath;
import filesystem.path.impl.compound.RelativeDirectoryPath;
import filesystem.path.interfaces.BasePath;
import filesystem.path.interfaces.compound.AbsoluteDirectory;
import filesystem.path.interfaces.compound.RelativeDirectory;
import filesystem.path.interfaces.simple.AbsolutePath;
import filesystem.path.interfaces.simple.RelativePath;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Patrick
 * @description
 * @since 14.06.2017
 */
// TODO: Make only module visibility
public class AbsolutePathImpl extends BasePathImpl implements AbsolutePath{

    public AbsolutePathImpl(Path path) {
        super(path);
    }

    protected AbsolutePathImpl(String path) {
        super(path);
    }

    @Override
    public AbsolutePath append(RelativeDirectory rel){
        Path combined = _path.resolve(rel.getPath());
        return new AbsolutePathImpl(combined);    }

    @Override
    public AbsolutePath remove(RelativeDirectory removal) throws PathsNotMatchingException{
        String path = removeEnd(this, removal);
        if (path == null) throw new PathsNotMatchingException();

        return new AbsolutePathImpl(Paths.get(path));
    }

    @Override
    public RelativePath remove(AbsoluteDirectory removal) throws PathsNotMatchingException{
        String path = removeStart(this, removal);
        if (path == null) throw new PathsNotMatchingException();

        return new RelativePathImpl(path);
    }

    @Override
    public String getAbsolutePath() {
        return _path.toAbsolutePath().toString();
    }

    @Override
    public AbsolutePath copy(){
        return new AbsolutePathImpl(_path.toAbsolutePath());
    }

    @Override
    public BasePath getTop() {
        Path top = _path.getFileName();
        return new AbsolutePathImpl(top);
    }

    @Override
    public AbsoluteDirectory getParentPath(){
        return AbsoluteDirectory.from(_path.getParent());
    }
}
