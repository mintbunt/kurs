<?php
$con = mysqli_connect("localhost", "root", "1234");
mysqli_select_db ($con, "kurs");

$token = $_POST["fcm_token"];
$id = $_POST["id"];

    $statement = mysqli_prepare($con, "INSERT INTO fcm_info (fcm_token, id) VALUES (?, ?)");
    mysqli_stmt_bind_param($statement, "si", $token, $id);
    mysqli_stmt_execute($statement);
    mysqli_stmt_close($statement);


$response = array();
$response["success"] = true;

echo json_encode($response);

?>
