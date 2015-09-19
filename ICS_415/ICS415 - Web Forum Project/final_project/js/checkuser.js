$(document).ready(function()
{
	var username = "";

	$.ajax(
	{
		type:'get',
		cache:false,
		async:false,
		url:'check.php',
		success:function(output)
		{
			//will return false or a username
			if(output === 'false')
			{
				//if it is false then that means no one is logged in
				//so send them to the home page to login/register
				window.location.href = 'home.html';
			}
			else
			{
				//if it gets back something that isn't false then
				//display the welcome message
				$("#welcome_message").html("Welcome, " + output);
				username = output;
			}
		}
	});
	
	if(username == 'guest')
	{
		$("#logout_link").html("Sign-in/Register");
	}
	

});

function logout()
{
	$.post('remove_cookie.php',function()
	{
		window.location.href = 'menu.html';
	});
}