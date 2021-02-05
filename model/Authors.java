package model;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Authors {

    private static final String List = null;

    public Authors() {
        loadAuthors();
    }

    private void loadAuthors() {
        Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
        Path filePath = Paths.get(root.toString(), "data", "books");
        File[] fileList = new File(filePath.toString()).listFiles();
        List<String> fileNames = new ArrayList<String>();
        for (int i = 0; i < fileList.length; i++) {
            if(fileList[i].isFile()) { fileNames.add(fileList[i].getName()); } 
        }
    }

}
