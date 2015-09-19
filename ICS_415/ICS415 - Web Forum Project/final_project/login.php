<?php
	$username = "ics415";
	$password = "password";
	$dbName = "forum_project";
	//connect
	$connection = mysqli_connect("localhost", $username, $password, $dbName);
	if(mysqli_connect_errno($connection)){
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
	
	$user = $_POST['username'];
	$pass = $_POST['password'];

	//prevent unwanted sql queries from user input
	$query = 'SELECT username, password FROM users WHERE username = ?';
	
	$stmt = mysqli_stmt_init($connection);
	if(mysqli_stmt_prepare($stmt, $query))
	{
		mysqli_stmt_bind_param($stmt, 's', $user);
		mysqli_stmt_execute($stmt);
		$result = mysqli_stmt_get_result($stmt);
		//check how many rows were returned
		//if it is 1 then check password
		if(mysqli_num_rows($result) == 1)
		{
			$row = mysqli_fetch_assoc($result);
			if($row['password'] == $pass)
			{
				echo 'true';
			}
			else
			{
				echo 'false';
			}
		}
		else//0 or 2+ rows so not valid results
		{
			echo 'false';
		}
	}
	
?>