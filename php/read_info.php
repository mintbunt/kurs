<?php
 $con = mysqli_connect("localhost", "root", "1234");
    mysqli_select_db ($con, "kurs");

    $id = $_POST["id"];

    $statement = mysqli_prepare($con, "SELECT * FROM users u  WHERE u.id = ?");
    mysqli_stmt_bind_param($statement, "i", $id);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $colId, $colLogin, $colPassword, $colName, $colSurname, $colPatronymic, $colRole, $colDate_birth, $colSex, $colEmail, $colPhone);


    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
	$response["success"] = true;
 	$response["login"] = $colLogin;
 	$response["email"] = $colEmail;
	$response["name"] = $colName;
  $response["surname"] = $colSurname;
	$response["patronymic"] = $colPatronymic;
	$response["date_birth"] = $colDate_birth;
	$response["phone"] = $colPhone;
    }

    echo json_encode($response);

?>
