package com.wmm.parse.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class MyIoUtils {
    public static String read(String pathStr) throws IOException {
       return new String(Files.readAllBytes(new File(pathStr).toPath()), StandardCharsets.UTF_8);
    }

}
