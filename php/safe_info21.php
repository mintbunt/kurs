<?php 
 $con = mysqli_connect("localhost", "root", "1234");
    mysqli_select_db ($con, "kurs");
    
    $id = $_POST["id"];
$polis = $_POST["polis"];
$weight = $_POST["weight"];
$height = $_POST["height"];
    
    $statement = "UPDATE pacients p SET p.polis = '$polis', p.weight = '$weight', p.height = '$height' WHERE p.id_user = '$id'";

    $response["success"] = false; 
    
if(mysqli_query($con,$statement)){
	$response["success"] = true; 
    } 
    
    echo json_encode($response);

?>