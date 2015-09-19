<?php
	$username = "ics415";
	$password = "password";
	$dbName = "forum_project";
	//connect
	$connection = mysqli_connect("localhost", $username, $password, $dbName);
	
	$user = $_POST['username'];
	
	//prevent unwanted sql queries from user input
	$query = 'SELECT username FROM users WHERE username = ?';
	
	$stmt = mysqli_stmt_init($connection);
	if(mysqli_stmt_prepare($stmt, $query))
	{
		mysqli_stmt_bind_param($stmt, 's', $user);
		mysqli_stmt_execute($stmt);
		$result = mysqli_stmt_get_result($stmt);
		//check how many rows were returned
		//if it is 0 then the username is available
		
		//echo"<script>alert(\"rows: ".mysqli_num_rows($result)."\")</script>";
		
		if(mysqli_num_rows($result) == 0)
		{
			echo 'true';
		}
		else//1+ rows so not valid username
		{
			echo 'false';
		}
	}
?>