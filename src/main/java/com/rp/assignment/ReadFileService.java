package com.rp.assignment;

import java.io.File;

@FunctionalInterface
public interface ReadFileService {
    public File readFile(String filename);
}
