<?php
	$username = "ics415";
	$password = "password";
	$dbName = "forum_project";
	//connect
	$connection = mysqli_connect("localhost", $username, $password);
	if(mysqli_connect_errno($connection)){
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
	
	//create database
	function createDatabase($con)
	{
		global $dbName;
		$dbCreate = "CREATE DATABASE ".$dbName;
		if(mysqli_query($con, $dbCreate))
		{
			echo "Database Created!";
		}
		else
		{
			echo"<script>alert(\"failed to make db". mysqli_error($con) ."\")</script>";
		}
	}
	
	function connectToDB($con)
	{
		global $dbName;
		mysqli_select_db($con, $dbName);
	}
	
	$topics = array
		(
			"gaming",
			"movies",
			"music",
			"hardware",
			"comics",
			"other"
		);
	//create tables if they dont exist
	function createTables($con)
	{
		$userTable="CREATE TABLE users
			(
				userID INT NOT NULL AUTO_INCREMENT,
				PRIMARY KEY(userID),
				username CHAR(40) NOT NULL UNIQUE,
				name CHAR(40) NOT NULL,
				email CHAR(100),
				password CHAR(50) NOT NULL
			)";


		global $topics;
		foreach($topics as $topic)
		{
			$topicTable="CREATE TABLE ".$topic."
				(
					topicID INT NOT NULL AUTO_INCREMENT,
					PRIMARY KEY(topicID),
					subject CHAR(40) NOT NULL,
					userID INT NOT NULL
				)";
			mysqli_query($con, $topicTable);
			if(mysqli_error($con))
			{
				echo "error: " . mysqli_error($con);
			}
		}
		if(!mysqli_query($con,$userTable))
		{
			//echo "error: " . mysqli_error($con);
		}
	}
	
	function addGuest($con)
	{
		$query = "INSERT INTO users(username, name, email, password)
				VALUES('guest', 'guest', 'guest@guest.com', 1234)";
		mysqli_query($con, $query);
	}

	createDatabase($connection);
	connectToDB($connection);
	createTables($connection);
	addGuest($connection);
	mysqli_close($connection);
?>