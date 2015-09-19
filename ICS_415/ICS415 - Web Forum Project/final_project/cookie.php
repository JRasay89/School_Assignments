<?php
	$user = $_POST['username'];
	$remember = $_POST['longTerm'];
	
	//number of days to keep cookie
	$days = 10;
	
	$cName = 'currentUser';
	$cVal = $user;
	$cTime = (time() + (60*60*24*$days))*$remember;
	//since remember is 1 (will be remembered)
	//or 0 (wont be remembered) this multiplication will work
	//for determining the cookie's time

	setcookie($cName, $cVal, $cTime, "/");


?>