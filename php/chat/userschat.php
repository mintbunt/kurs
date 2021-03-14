<?php
 $con = mysqli_connect("localhost", "root", "1234");
    mysqli_select_db ($con, "kurs");

    $usuario = $_POST['id'];
    $usuarioDestino = $_POST['userDest'];

      $result = array();
       $result['data'] = array();
       $select= "SELECT u.id, u.name, u.surname, u.patronymic FROM users u where u.id = '$usuario' UNION ALL SELECT u.id, u.name, u.surname, u.patronymic FROM users u where u.id='$usuarioDestino'";
       $responce = mysqli_query($con,$select);

       $result["success"]="0";

       while($row = mysqli_fetch_array($responce))
         {
           $result["success"]="1";
           $index['id']      = $row['0'];
           $index['name']    = $row['1'];
           $index['surname']   = $row['2'];
           $index['patronymic'] = $row['3'];

           array_push($result['data'], $index);
         }

           echo json_encode($result);
           mysqli_close($con);
  ?>
