<?php
	$user;

	if(isset($_COOKIE['currentUser']))
	{
		$user = $_COOKIE['currentUser'];
		echo $user;
	}
	else
	{
		echo 'false';
	}

?>