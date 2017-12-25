package filesystem.attributes;

import functional.exception.FunctionEx;

import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.function.Function;

import static core.util.ComparisonResult.isGreater;
import static core.util.ComparisonResult.isSmaller;

/**
 * Creator: Patrick
 * Created: 20.11.2017
 * Purpose:
 */
public interface FileSnapshot extends BasicFileAttributeView {

    default boolean wasCreatedBefore(BasicFileAttributeView other) throws IOException {
        return wasAttributeBefore(other, attrView -> attrView.readAttributes().creationTime());
    }

    default boolean wasModifiedBefore(BasicFileAttributeView other) throws IOException {
        return wasAttributeBefore(other, attrView -> attrView.readAttributes().lastModifiedTime());
    }

    default boolean wasModifiedAfter(BasicFileAttributeView other) throws IOException {
        return wasAttributeAfter(other, attrView -> attrView.readAttributes().lastModifiedTime());
    }

    default boolean wasLastAccessedBefore(BasicFileAttributeView other) throws IOException {
        return wasAttributeBefore(other, attrView -> attrView.readAttributes().lastAccessTime());
    }

    default boolean wasAttributeBefore(BasicFileAttributeView other, Function<BasicFileAttributeView, FileTime> compareFunction) throws IOException {
        FunctionEx<BasicFileAttributeView, FileTime, IOException> functionEx = compareFunction::apply;
        return wasAttributeBefore(other, functionEx);
    }

    default boolean wasAttributeBefore(BasicFileAttributeView other, FunctionEx<BasicFileAttributeView, FileTime, IOException> compareFunction) throws IOException {
        FileTime thisTime = compareFunction.apply(this);
        FileTime otherTime = compareFunction.apply(other);

        return isGreater(thisTime, otherTime);
    }

    default boolean wasAttributeAfter(BasicFileAttributeView other, FunctionEx<BasicFileAttributeView, FileTime, IOException> compareFunction) throws IOException {
        FileTime thisTime = compareFunction.apply(this);
        FileTime otherTime = compareFunction.apply(other);

        return isSmaller(thisTime, otherTime);
    }

    //<editor-fold desc="Factory Method">
    static FileSnapshot of(BasicFileAttributeView basicFileAttributeView){
        return new FileSnapshot(){

            @Override
            public String name() {
                return basicFileAttributeView.name();
            }

            @Override
            public BasicFileAttributes readAttributes() throws IOException {
                return basicFileAttributeView.readAttributes();
            }

            @Override
            public void setTimes(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime createTime) throws IOException {
                basicFileAttributeView.setTimes(lastModifiedTime, lastAccessTime, createTime);
            }
        };
    }
    //</editor-fold>
}
