<?php

    $con = mysqli_connect("localhost", "root", "1234");
    mysqli_select_db ($con, "kurs");

    $id = $_POST["id"];
$polis = $_POST["polis"];
$weight = $_POST["weight"];
$height = $_POST["height"];

        $statement = mysqli_prepare($con, "INSERT INTO pacients (id_user, polis, weight, height) VALUES (?, ?, ?, ?)");
        mysqli_stmt_bind_param($statement, "isii", $id, $polis, $weight, $height);
        mysqli_stmt_execute($statement);
        mysqli_stmt_close($statement);


    $response = array();
    $response["success"] = true;

    echo json_encode($response);
?>
