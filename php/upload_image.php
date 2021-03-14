<?php
 $con = mysqli_connect("localhost", "root", "1234");
    mysqli_select_db ($con, "kurs");
if($_SERVER['REQUEST_METHOD']=='POST'){
$id = $_POST["id"];
$name = $_POST["name"];
	$image = $_POST["image"];

 $sql = "INSERT INTO analys (id_pac,name,image) VALUES (?,?,?)";

 $stmt = mysqli_prepare($con,$sql);

 mysqli_stmt_bind_param($stmt,"iss",$id,$name,$image);
 mysqli_stmt_execute($stmt);

 $check = mysqli_stmt_affected_rows($stmt);

 if($check == 1){
 echo "Изображение успешно загружено!";
 }else{
 echo "Не удалось загрузить изображение. Имя изображения должно быть уникальным.";
 }
 mysqli_close($con);
 }else{
 echo "Ошибка";
 }


?>
