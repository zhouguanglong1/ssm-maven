package com.zhougl.javadesignpatterndemo.composite_pattern.example_file;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/3/5 17:59
 */
public class File extends FileSystemNode {

    public File(String path) {
        super(path);
    }

    @Override
    public int countNumOfFiles() {
        return 1;
    }

    @Override
    public long countSizeOfFiles() {
        java.io.File file = new java.io.File(path);
        if (!file.exists()) return 0;
        return file.length();
    }
}
