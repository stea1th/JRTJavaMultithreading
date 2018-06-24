package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        //String suffix = f.getName().substring(f.getName().indexOf("."));
        String suffix = f.getName().toLowerCase();
        if(f.isDirectory())
            return true;
        else if(!f.isDirectory()&&f.getName().contains(".")){
            //return suffix.equalsIgnoreCase(".html") || suffix.equalsIgnoreCase(".htm");
            return suffix.endsWith(".html") || suffix.endsWith(".htm");
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
