# FixOldPHPMySQL
Project which I created to revive an old project made in PHP 5.3 with old MySQL functions like mysql_connect, mysql_query (replaced by mysqli) and a fix to get an associative array with mysqli_fetch_assoc. 

You will need the Apache Commons IO library.

How to use when you compile:

`java -jar FixOldPHPMySQL.jar ./PHP_files_path var_without_dollar`
