<?php

	$cName = 'currentUser';
	$cVal = "";
	$cTime = time()-3600;


	if(isset($_COOKIE[$cName]))
	{
		unset($_COOKIE[$cName]);
		setcookie($cName, $cVal, $cTime, "/");
	}

?>