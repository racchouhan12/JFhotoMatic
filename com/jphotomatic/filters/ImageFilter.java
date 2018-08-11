package com.jphotomatic.filters;

import java.io.File;
import javax.swing.filechooser.FileFilter;
import com.jphotomatic.model.Utils;

public class ImageFilter extends FileFilter {

    public ImageFilter() {
    }

    public boolean accept(File file) {
        if (file.isDirectory())
            return true;
        String s = Utils.getExtension(file);
        if (s != null)
            return s.equals("jpeg") || s.equals("jpg") || s.equals("png");
        else
            return false;
    }

    public String getDescription() {
        return "JPEG Images(*.jpg) And PNG Images(*.png)";
    }
}
