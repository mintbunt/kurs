<?php
 $con = mysqli_connect("localhost", "root", "1234");
    mysqli_select_db ($con, "kurs");

      $id1 = $_POST["id"];

      $result = array();
       $result['data'] = array();
       $select= "SELECT u.id, u.name, u.surname, u.patronymic FROM users u, cvyaz cv WHERE cv.id_doc = u.id and cv.id_pac = '$id1'";
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
