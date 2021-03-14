<?php
    $con = mysqli_connect("localhost", "root", "1234");
    mysqli_select_db ($con, "kurs");

      $usuario = $_POST['id'];
      $usuarioDestino = $_POST['userDest'];

      $result = array();
       $result['data'] = array();
       $select= "SELECT u.id, u.name, u.surname, u.patronymic, m.idMes, m.message, m.userOrig, m.userDest FROM messages m INNER JOIN users u ON m.userOrig = u.id
     WHERE (m.userOrig='$usuario' AND m.userDest='$usuarioDestino') OR (m.userOrig='$usuarioDestino' AND m.userDest='$usuario')";
       $responce = mysqli_query($con,$select);

       $result["success"]="0";

       while($row = mysqli_fetch_array($responce))
         {
           $result["success"]="1";
           $index['id']    = $row['0'];
           $index['name']    = $row['1'];
           $index['surname']    = $row['2'];
           $index['patronymic']    = $row['3'];
           $index['idMes']    = $row['4'];
           $index['message']    = $row['5'];
           $index['userOrig']    = $row['6'];
           $index['userDest']    = $row['7'];

           array_push($result['data'], $index);
         }

           echo json_encode($result);
           mysqli_close($con);

  ?>
