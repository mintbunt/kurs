<?php
    require("password.php");

    $con = mysqli_connect("localhost", "root", "1234");
    mysqli_select_db ($con, "kurs");

    $id_pac = $_POST["id_pac"];
    $name = $_POST["name"];
    $id = $_POST["id"];
    $doz = $_POST["doz"];
    $receipt = $_POST["receipt"];
    $id_doc = $_POST["id_doc"];

        $statement = mysqli_prepare($con, "INSERT INTO new_pills (id_pac, name, id_zab, doz, receipt, id_doc) VALUES (?, ?, ?, ?, ?, ?)");
        mysqli_stmt_bind_param($statement, "isissi", $id_pac, $name, $id, $doz, $receipt, $id_doc);
        mysqli_stmt_execute($statement);
        mysqli_stmt_close($statement);


    $response = array();
    $response["success"] = true;

    echo json_encode($response);
?>
