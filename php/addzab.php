<?php
    require("password.php");

    $con = mysqli_connect("localhost", "root", "1234");
    mysqli_select_db ($con, "kurs");

    $id = $_POST["id"];
    $name = $_POST["name_zab"];

        $statement = mysqli_prepare($con, "INSERT INTO zabolevanie (id_pac, name_zab) VALUES (?, ?)");
        mysqli_stmt_bind_param($statement, "is", $id, $name);
        mysqli_stmt_execute($statement);
        mysqli_stmt_close($statement);


    $response = array();
    $response["success"] = true;

    echo json_encode($response);
?>
