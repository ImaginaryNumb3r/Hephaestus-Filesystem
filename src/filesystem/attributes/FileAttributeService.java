package filesystem.attributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

/**
 * Creator: Patrick
 * Created: 20.11.2017
 * Purpose:
 */
public class FileAttributeService {

    public static BasicFileAttributeView makeBasicFileAttributeView(Path path, BasicFileAttributes attrs){
        return new BasicFileAttributeView() {
            @Override
            public String name() {
                return path.getFileName().toString();
            }

            @Override
            public BasicFileAttributes readAttributes() throws IOException {
                return attrs;
            }

            @Override
            public void setTimes(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime createTime) throws IOException {
                if (lastModifiedTime != null){
                    Files.setLastModifiedTime(path, lastModifiedTime);
                }
                if (lastAccessTime != null){
                    Files.setAttribute(path, "lastAccessTime", lastAccessTime.toMillis());
                }
                if (createTime != null){
                    Files.setAttribute(path, "creationTime", createTime.toMillis());
                }
            }
        };
    }
}
