<?php
    require("password.php");

    $con = mysqli_connect("localhost", "root", "1234");
    mysqli_select_db ($con, "kurs");

    $login = $_POST['login'];
    $password = $_POST['password'];
    $role = $_POST['role'];
    $name = $_POST['name'];
    $surname = $_POST['surname'];
    $patronymic = $_POST['patronymic'];
    $date_birth = $_POST['date_birth'];
$sex = $_POST['sex'];
    $email = $_POST['email'];
    $phone = $_POST['phone'];

    $login = trim($login);
    $password = trim($password);

function registerUser() {
        global $con, $login, $password, $role, $name, $surname, $patronymic, $date_birth, $sex, $email, $phone;
        $passwordHash = password_hash($password, PASSWORD_DEFAULT);
        $statement = mysqli_prepare($con, "INSERT INTO users (login, password, name, surname, patronymic, role, date_birth, sex, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        mysqli_stmt_bind_param($statement, "ssssssssss", $login, $passwordHash, $name, $surname, $patronymic, $role, $date_birth, $sex, $email, $phone);
        mysqli_stmt_execute($statement);
        mysqli_stmt_close($statement);
    }

    function usernameAvailable() {
        global $con, $login;
        $statement = mysqli_prepare($con, "SELECT * FROM users WHERE login = ?");
        mysqli_stmt_bind_param($statement, "s", $login);
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
        $count = mysqli_stmt_num_rows($statement);
        mysqli_stmt_close($statement);
        if ($count < 1){
            return true;
        }else {
            return false;
        }
    }

    $response = array();
$response["success"] = false;

 if (usernameAvailable()){
        registerUser();
        $response["success"] = true;
    }

    echo json_encode($response);
?>
