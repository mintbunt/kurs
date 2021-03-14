<?php
$con = mysqli_connect("localhost", "root", "1234");
   mysqli_select_db ($con, "kurs");

   $id1 = $_POST["id"];

   $result = array();
   	$result['data'] = array();
   	$select= "SELECT id, name_zab FROM zabolevanie WHERE id_pac = '$id1'";
   	$responce = mysqli_query($con,$select);

    $result["success"]="0";

   	while($row = mysqli_fetch_array($responce))
   		{
        $result["success"]="1";
        $index['id']    = $row['0'];
   			$index['name_zab']    = $row['1'];

   			array_push($result['data'], $index);
   		}

   			echo json_encode($result);
   			mysqli_close($con);

?>
