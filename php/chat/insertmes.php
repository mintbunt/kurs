<?php

    $con = mysqli_connect("localhost", "root", "1234");
    mysqli_select_db ($con, "kurs");


      $usuario = $_POST['id'];
      $usuarioDestino = $_POST['userDest'];
      $mensaje = $_POST['message'];

      $statement = mysqli_prepare($con, "INSERT INTO messages (message, userOrig, userDest) VALUES (?,?,?)");
      mysqli_stmt_bind_param($statement, "sii", $mensaje, $usuario, $usuarioDestino);
      mysqli_stmt_execute($statement);
      mysqli_stmt_close($statement);

      echo "Сообщение отправлено";

  ?>
