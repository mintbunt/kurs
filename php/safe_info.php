<?php 
 $con = mysqli_connect("localhost", "root", "1234");
    mysqli_select_db ($con, "kurs");
    
    $id = $_POST["id"];
$name = $_POST["name"];
$surname = $_POST["surname"];
$patronymic = $_POST["patronymic"];

    
    $statement = mysqli_prepare($con, "UPDATE users u SET u.name = ?, u.surname = ?, u.patronymic = ? WHERE u.id = ?");
mysqli_stmt_bind_param($statement, "isss", $id, $name, $surname, $patronymic);
   mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);


    
    $response = array();
    $response["success"] = false; 
    
    while(mysqli_stmt_fetch($statement)){
	$response["success"] = true; 

	$response["name"] = $colName;
        $response["surname"] = $colSurname;
	$response["patronymic"] = $colPatronymic;
	
    }
    
    echo json_encode($response);

?>