<?php
$con = mysqli_connect("localhost", "root", "1234");
   mysqli_select_db ($con, "kurs");

   $id1 = $_POST["id"];

   $result = array();
   	$result['data'] = array();
   	$select= "SELECT id, name FROM new_pills WHERE id_zab = '$id1'";
   	$responce = mysqli_query($con,$select);

    $result["success"]="0";

   	while($row = mysqli_fetch_array($responce))
   		{
        $result["success"]="1";
   			$index['id']      = $row['0'];
   			$index['name']    = $row['1'];

   			array_push($result['data'], $index);
   		}

   			echo json_encode($result);
   			mysqli_close($con);

?>
