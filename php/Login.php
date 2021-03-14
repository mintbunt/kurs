<?php
    require("password.php");

    $con = mysqli_connect("localhost", "root", "1234");
    mysqli_select_db ($con, "kurs");

    $login = $_POST["login"];
    $password = $_POST["password"];

    $statement = mysqli_prepare($con, "SELECT * FROM users WHERE login = ?");
    mysqli_stmt_bind_param($statement, "s", $login);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $colId, $colLogin, $colPassword, $colName, $colSurname, $colPatronymic, $colRole, $colDate_birth, $colSex, $colEmail, $colPhone);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
if (password_verify($password, $colPassword)) {
        $response["success"] = true;
        $response["login"] = $colLogin;
        $response["role"] = $colRole;
	$response["name"] = $colName;
        $response["surname"] = $colSurname;
	$response["patronymic"] = $colPatronymic;
	$response["id"] = $colId;
	$response["phone"] = $colPhone;
    }
}



    echo json_encode($response);
?>
