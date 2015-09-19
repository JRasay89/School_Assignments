<?php
	$username = "ics415";
	$password = "password";
	$dbName = "forum_project";
	//connect
	$connection = mysqli_connect("localhost", $username, $password, $dbName);
	if(mysqli_connect_errno($connection)){
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
	
	$user = $_POST['un'];
	$password = $_POST['p'];
	$name = $_POST['n'];
	$email = $_POST['e'];
	
	//prevent unwanted sql queries from user input
	$query = "INSERT INTO users(username, name, email, password)
			VALUES(?, ?, ?, ?)";
	
	$stmt = mysqli_stmt_init($connection);
	if(mysqli_stmt_prepare($stmt, $query))
	{
		mysqli_stmt_bind_param($stmt, 'ssss', $user, $name, $email, $password);
		mysqli_stmt_execute($stmt);
	}
?>