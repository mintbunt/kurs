<?php
 $con = mysqli_connect("localhost", "root", "1234");
    mysqli_select_db ($con, "kurs");

    $id = $_POST["id"];

    $statement = mysqli_prepare($con, "SELECT f.name, f.address FROM doctors d, filial f WHERE d.id_user = ? and d.id_fillial=f.id");
    mysqli_stmt_bind_param($statement, "i", $id);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $colName, $colAddress);


    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
	$response["success"] = true;
	$response["name"] = $colName;
	$response["address"] = $colAddress;
    }

    echo json_encode($response);

?>
