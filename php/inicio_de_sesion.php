<?php
session_start();

/* LOGIN BÁSICO QUEMADO (para proyecto SENA) */
$usuarios = [
    "INVITADO" => [
        "usuario" => "invitado",
        "password" => "1234"
    ],
    "COMPRADOR" => [
        "usuario" => "comprador",
        "password" => "1234"
    ],
    "VENDEDOR" => [
        "usuario" => "vendedor",
        "password" => "1234"
    ]
];

$error = "";

if ($_SERVER["REQUEST_METHOD"] === "POST") {
    $rol = $_POST["rol"] ?? "";
    $usuario = $_POST["usuario"] ?? "";
    $password = $_POST["password"] ?? "";

    if (isset($usuarios[$rol])) {
        if (
            $usuarios[$rol]["usuario"] === $usuario &&
            $usuarios[$rol]["password"] === $password
        ) {
            $_SESSION["usuario"] = $usuario;
            $_SESSION["rol"] = $rol;

            header("Location: principal.php");
            exit;
        }
    }

    $error = "Usuario, contraseña o rol incorrectos";
}
?>
