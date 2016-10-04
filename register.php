<?php
$link = mysqli_connect("127.0.0.1", "root", "", "user");

$name = $_POST["name"];
$username = $_POST["username"];
$password = $_POST["password"];
     
        $statement = mysqli_prepare($con, "INSERT INTO user (name, username, password) VALUES (?, ?, ?)");
        mysqli_stmt_bind_param($statement, "sss", $name, $username, $password);
        mysqli_stmt_execute($statement);
            
    
   
    
    $response = array();
    $response["success"] = true;  
    
    
    echo json_encode($response);
?>