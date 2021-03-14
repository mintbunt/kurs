<?php
 $con = mysqli_connect("localhost", "root", "1234");
    mysqli_select_db ($con, "kurs");

$id = $_POST["id"];
$name = $_POST["name"];
$doz = $_POST["doz"];
$receipt = $_POST["receipt"];

    $statement = "UPDATE new_pills u SET u.name = '$name', u.doz = '$doz', u.receipt = '$receipt' WHERE u.id = '$id'";

    $response["success"] = false;

if(mysqli_query($con,$statement)){
	$response["success"] = true;
    }

    echo json_encode($response);

?>
