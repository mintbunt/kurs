<?php
 $con = mysqli_connect("localhost", "root", "1234");
    mysqli_select_db ($con, "kurs");

    $id = $_POST["id"];

        $statement = mysqli_prepare($con, "DELETE FROM new_pills WHERE id = ?");
        mysqli_stmt_bind_param($statement, "i", $id, );
        mysqli_stmt_execute($statement);
        mysqli_stmt_close($statement);


    $response = array();
    $response["success"] = true;

    echo json_encode($response);

?>
