<?php 
 $con = mysqli_connect("localhost", "root", "1234");
    mysqli_select_db ($con, "kurs");
    
    $id = $_POST["id"];
$name = $_POST["name"];
$surname = $_POST["surname"];
$patronymic = $_POST["patronymic"];
$date_birth = $_POST["date_birth"];
    
    $statement = "UPDATE users u SET u.name = '$name', u.surname = '$surname', u.patronymic = '$patronymic', u.date_birth = '$date_birth' WHERE u.id = '$id'";

    $response["success"] = false; 
    
if(mysqli_query($con,$statement)){
	$response["success"] = true; 
    } 
    
    echo json_encode($response);

?>