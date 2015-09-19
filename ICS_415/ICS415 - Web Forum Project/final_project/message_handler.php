<?php
	// Create connection to mysql
	//TABLES ARE ALSO CREATED
	//USER TABLE uses unique names and is not case sensitive
	$host = "localhost";
	$user = "ics415";
	$pwd = "password";
	$db = "forum_project";
	$con=mysqli_connect($host,$user,$pwd);
	if(mysqli_connect_errno($con)){
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
	//Connect to the database
	else {
		mysqli_select_db($con, $db);
	}
	
	/*
	 *If a user creates a new topic
	 */
	if ($_REQUEST["display"] == "new_topic") {
		$title = $_REQUEST["title"];
		$table_title = str_replace(' ', '', $title); //Trim spaces to be use as a title of the table to be created
		$msg = $_REQUEST["msg"];
		$table = $_REQUEST["table"];
		$username = $_REQUEST["username"];
		
		
		//Getting the userID of the the user who created the topic
		$userQuery = "SELECT users.userID ".
								"FROM users ".
								"Where users.username = '$username'";
		$users = mysqli_query($con,$userQuery);
		$userRow = mysqli_fetch_array($users);
		$userID = $userRow["userID"];
		
		//Insert the new topic into the table
		$query = "INSERT INTO ".$table." (subject, userID) ".
								"VALUES ('$title', $userID)";
		mysqli_query($con, $query);
		
		/*
		 *Creating a new table for the topic created, and store any messages for that topic in this table
		 */
		//Creating a table with the title of the topic with no space as the name of the table
		$message_table = "CREATE TABLE ".$table_title.
					" ( userID   INT NOT NULL,
					  message text NOT NULL
					 )";
		mysqli_query($con, $message_table);
		
		//Insert the message into the new table
		$msg_insert = "INSERT INTO ".$table_title." (userID, message) ".
								"VALUES ($userID, '$msg')";
		mysqli_query($con, $msg_insert);
		
		//Display the message of the new topic on the page
		echo "<tr>";
		echo "<th>User</th>";
		echo "<th>Messages</th>";
		echo "</tr>";
		
		$query = "SELECT " .$table_title.".message, ".$table_title.".userID ".
								"FROM ".$table_title." ";
		$topic = mysqli_query($con,$query);
		while($row = mysqli_fetch_array($topic))
		{
			echo "<tr>";
			//Getting the user name
			$ID = $row['userID'];
			$queryUser = "SELECT users.username ".
								"FROM users ".
								"Where users.userID = $ID";
			$users = mysqli_query($con, $queryUser);
			$userRow = mysqli_fetch_array($users);
			
			echo "<td>".$userRow['username']."</td>";
			echo "<td>".$row['message']."</td>";
			echo "</tr>";
		}
	}
	
	/*
	 *When user post new message
	 */
	else if ($_REQUEST["display"] == "new_message") {
		$title = $_REQUEST["val"];
		$table_title = str_replace(' ', '', $title); //Trim spaces to be use as a title of the table to be created
		$msg = $_REQUEST["msg"];
		$table = $_REQUEST["table"];
		$username = $_REQUEST["username"];
		
		
		//Getting the userID of the the user who created the topic
		$userQuery = "SELECT users.userID ".
								"FROM users ".
								"Where users.username = '$username'";
		$users = mysqli_query($con,$userQuery);
		$userRow = mysqli_fetch_array($users);
		$userID = $userRow["userID"];
		
		//Insert the message into the new table
		$msg_insert = "INSERT INTO ".$table_title." (userID, message) ".
								"VALUES ($userID, '$msg')";
		mysqli_query($con, $msg_insert);
		
		//Display the message of the new topic on the page
		echo "<tr>";
		echo "<th>User</th>";
		echo "<th>Messages</th>";
		echo "</tr>";
		
		$query = "SELECT " .$table_title.".message, ".$table_title.".userID ".
								"FROM ".$table_title." ";
		$topic = mysqli_query($con,$query);
		while($row = mysqli_fetch_array($topic))
		{
			echo "<tr>";
			//Getting the user name
			$ID = $row['userID'];
			$queryUser = "SELECT users.username ".
								"FROM users ".
								"Where users.userID = $ID";
			$users = mysqli_query($con, $queryUser);
			$userRow = mysqli_fetch_array($users);
			
			echo "<td>".$userRow['username']."</td>";
			echo "<td>".$row['message']."</td>";
			echo "</tr>";
		}
	}
	
	/*
	 *When user views the main thread page
	 */
	else if( $_REQUEST["display"] == "thread")
	{
		$table = $_REQUEST["table"];
		//The header of the table

		echo "<tr>";
		echo "<th>Subject</th>";
		echo "<th>Created By</th>";
		echo "</tr>";
		
		$query = "SELECT " .$table.".subject, ".$table.".userID ".
								"FROM ".$table." ";
		$topic = mysqli_query($con,$query);
		while($row = mysqli_fetch_array($topic))
		{
			echo "<tr>";
			echo "<td>"."<a id='thread' href='#'>".$row['subject']."</a>"."</td>";
			
			//Getting the user name
			$userID = $row['userID'];
			$query2 = "SELECT users.username ".
								"FROM users ".
								"Where users.userID = $userID";
			$users = mysqli_query($con, $query2);
			$userRow = mysqli_fetch_array($users);
			
			echo "<td>".$userRow['username']."</td>";
			echo "</tr>";
		}
	}
	
	/*
	 *When user views the message page that they click
	 */
	else if ($_REQUEST["display"] == "messages"){
		$title = $_REQUEST['val'];
		$value = str_replace(' ', '', $title);
		echo "<tr>";
		echo "<th>User</th>";
		echo "<th>Messages</th>";
		echo "</tr>";
		
		$query = "SELECT " .$value.".message, ".$value.".userID ".
								"FROM ".$value." ";
		$topic = mysqli_query($con,$query);
		while($row = mysqli_fetch_array($topic))
		{
			echo "<tr>";
			//Getting the user name
			$userID = $row['userID'];
			$query2 = "SELECT users.username ".
								"FROM users ".
								"Where users.userID = $userID";
			$users = mysqli_query($con, $query2);
			$userRow = mysqli_fetch_array($users);
			
			echo "<td>".$userRow['username']."</td>";
			echo "<td>".$row['message']."</td>";
			echo "</tr>";
		}
	}
?>