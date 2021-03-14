<?php 
 $con = mysqli_connect("localhost", "root", "1234");
    mysqli_select_db ($con, "kurs");
    
    $id = $_POST["id"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM doctors d  WHERE d.id_user = ?");
    mysqli_stmt_bind_param($statement, "i", $id);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $colId, $colId_user, $colId_fillial, $colSpeciality, $colStaj);

    
    $response = array();
    $response["success"] = false; 
    
    while(mysqli_stmt_fetch($statement)){
	$response["success"] = true; 
	$response["speciality"] = $colSpeciality;
	$response["staj"] = $colStaj;
    }
    
    echo json_encode($response);

?>