package com.company;

import org.apache.commons.io.*;
import java.io.*;

/**
 * @author Nacho González-Garilleti <nachox07@users.noreply.github.com>
 */
public class FixOldPHPMySQL {

    private String cnvar;
    private String lines;

    /**
     * @Param path This is the path of the directory where files will be modified
     * @Param cnvar PHP var which contains the MySQL connection
     * */
    public FixOldPHPMySQL(String p, String cnvar) {

        File path = new File(p);
        this.cnvar = "\\$" + cnvar;

        for (String file : path.list()) {

            try {

                if (file.substring(file.lastIndexOf(".")).equals(".php")) {

                    File tmp = new File(p + file);

                    String lines = FileUtils.readFileToString(tmp, "UTF-8");

                    changeMysql(lines);

                    FileUtils.writeStringToFile(tmp, lines, "UTF-8");

                    System.out.println("File " + file + " changed");

                }

            } catch (IOException | StringIndexOutOfBoundsException e) {

                System.out.println(file + " ignored: Is a directory or an IO error creating the new file");

            }

        }

    }

    /**
     *
     * @param l file content to be replaced with new mysqli functions
     * @return returns all the content changed
     */
    public String changeMysql(String l) {

        l = l.replaceAll("mysql_query\\(", "mysqli_query\\(" + cnvar + ", ");
        l = l.replaceAll("mysql_fetch_array", "mysqli_fetch_assoc");
        l = l.replaceAll("mysql_", "mysqli_");
        l = l.replaceAll(", " + cnvar, "");

        return l;

    }

}
