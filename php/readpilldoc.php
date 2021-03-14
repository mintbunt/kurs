<?php
 $con = mysqli_connect("localhost", "root", "1234");
    mysqli_select_db ($con, "kurs");

    $id = $_POST["id"];

    $statement = mysqli_prepare($con, "SELECT u.name, u.surname, u.patronymic, d.speciality FROM users u, doctors d, new_pills p WHERE p.id = ? and p.id_doc=d.id_user and d.id_user = u.id");
    mysqli_stmt_bind_param($statement, "i", $id);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $colName,  $colSurname,  $colPatronymic,  $colSpeciality);


    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
	$response["success"] = true;
	$response["name"] = $colName;
  $response["surname"] = $colSurname;
  $response["patronymic"] = $colPatronymic;
  $response["speciality"] = $colSpeciality;
    }

    echo json_encode($response);

?>
