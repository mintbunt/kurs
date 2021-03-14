<?php 
 $con = mysqli_connect("localhost", "root", "1234");
    mysqli_select_db ($con, "kurs");
    
    $id = $_POST["id"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM pacients p WHERE p.id_user = ?");
    mysqli_stmt_bind_param($statement, "i", $id);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $colId, $colId_user, $colPolis, $colWeight, $colHeight);

    
    $response = array();
    $response["success"] = false; 
    
    while(mysqli_stmt_fetch($statement)){
	$response["success"] = true; 
 	$response["polis"] = $colPolis;
	$response["weight"] = $colWeight;
	$response["height"] = $colHeight;    
}
    
    
echo json_encode($response);


?>