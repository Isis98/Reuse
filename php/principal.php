<?php
session_start();

if (!isset($_SESSION["usuario"])) {
    header("Location: inicio_de_sesion.php");
    exit;
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Reuse - Principal</title>
    <link rel="stylesheet" href="/css/estilos_principal.css">
</head>
<body>

<header class="header">
    <h1>REUSE</h1>
    <p>Bienvenido <?= $_SESSION["usuario"] ?> (<?= $_SESSION["rol"] ?>)</p>
    <a href="logout.php">Cerrar sesión</a>
</header>

<main class="contenido">
    <h2>Página principal</h2>
    <p>Acceso correcto al sistema.</p>
</main>

</body>
</html>
