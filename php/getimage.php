<?php
 $con = mysqli_connect("localhost", "root", "1234");
    mysqli_select_db ($con, "kurs");

    if($_SERVER['REQUEST_METHOD']=='GET'){

     $id = $_GET['id'];
     
     $sql = "select * from analys where id = '$id'";

     $r = mysqli_query($con,$sql);

     $result = mysqli_fetch_array($r);

     header('content-type: image/jpeg');

     echo base64_decode($result['image']);

     mysqli_close($con);

     }else{
     echo "Error";
     }
?>
