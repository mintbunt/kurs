<?php
 $con = mysqli_connect("localhost", "root", "1234");
    mysqli_select_db ($con, "kurs");

    $id = $_POST["id"];

    $statement = mysqli_prepare($con, "SELECT * FROM new_pills p WHERE p.id = ?");
    mysqli_stmt_bind_param($statement, "i", $id);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $colId, $colId_pac, $colName, $colId_zab, $colDoz, $colReceipt, $colId_doc);


    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
	$response["success"] = true;
	$response["name"] = $colName;
	$response["doz"] = $colDoz;
  $response["receipt"] = $colReceipt;
    }

    echo json_encode($response);

?>
