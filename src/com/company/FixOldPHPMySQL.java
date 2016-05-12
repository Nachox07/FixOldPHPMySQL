package com.company;

import org.apache.commons.io.*;
import java.io.*;

/**
 * @author Nacho González-Garilleti <nachox07@users.noreply.github.com>
 */
public class FixOldPHPMySQL {

    /**
     * @Param String path This is the path of the directory where files will be modified
     * @Param String cnvar PHP var which contains the MySQL connection
     * */
    public FixOldPHPMySQL(String p, String cnvar) {

        File path = new File(p);
        cnvar = "\\$" + cnvar;

        for (String file : path.list()) {

            try {

                if (file.substring(file.lastIndexOf(".")).equalsIgnoreCase(".php")) {

                    String lines = FileUtils.readFileToString(new File(p + file), "UTF-8");

                    lines = lines.replaceAll("mysql_query\\(", "mysqli_query\\(" + cnvar + ", ");
                    lines = lines.replaceAll("mysql_fetch_array", "mysqli_fetch_assoc");
                    lines = lines.replaceAll("mysql_", "mysqli_");
                    lines = lines.replaceAll(", " + cnvar, "");

                    File tmp = new File(p + file);
                    FileUtils.writeStringToFile(tmp, lines, "UTF-8");

                    System.out.println("File " + file + " changed");

                }

            } catch (IOException | StringIndexOutOfBoundsException e) {

                System.out.println(file + " ignored: Is a directory or an IO error creating the new file");

            }

        }

    }

}
