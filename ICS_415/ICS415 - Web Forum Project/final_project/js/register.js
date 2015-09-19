$(document).ready(function(){
	$("#password_confirm").keyup(checkMatch);
	$("#finished").toggle();
});

function checkMatch()
{
	var password = $("#password").val();
	var confirm = $("#password_confirm").val();
	
	if(password != confirm)
	{
		$("#match_error").html("passwords do not match");
	}
	else
	{
		$("#match_error").html("");
	}
}

function validate()
{
	//form input values
	var user = $("#username").val();
	var name = $("#name").val();
	var password = $("#password").val();
	var confirm = $("#password_confirm").val();
	var email = $("#email").val();

	//validation regular expressions
	var user_regex = /^[0-9a-zA-Z_]{5,}$/;
	var password_regex = /^(?=.*\d)(?=.*[a-z])[0-9a-zA-Z]{8,}$/;
	var email_regex = /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/;

	//valid input variables - default to false
	var valid_user = false;
	var valid_pass = false;
	var valid_name = false;
	var valid_email = false;
	
	//remove error messages
	$("#user_error").html("");
	$("#pass_error").html("");
	$("#name_error").html("");
	$("#email_error").html("");

	//check if username is empty
	if(user == null || user == "")
	{
		$("#user_error").html("please enter a username");
	}
	else//they entered something
	{
		//check for invalid usernames
		if(user == 'false')
		{
			$("#user_error").html("invalid username");
		}
		else
		{
			//check if username has valid characters
			if(!user_regex.test(user))
			{
				//invalid characters
				$("#user_error").html("invalid username");
			}
			else//valid characters
			{
				$.ajax(
				{
					type:'post',
					cache:false,
					async:false,
					url:'available.php', 
					data:{username:user}, 
					success: function(output)
					{
						if(output === "true")
						{
							valid_user = true;
						}
						else
						{
							$("#user_error").html("unavailable username");
						}
					}
				});
			}
		}
	}
	
	//check if password is empty
	if(password == null || password == "")
	{
		$("#pass_error").html("please enter a password");
	}
	else//they entered something
	{
		//check for valid password
		if(!password_regex.test(password))
		{
			//doesn't meet requirements
			$("#pass_error").html("invalid password");
		}
		else//password is valid
		{
			if(password == confirm)
			{
				valid_pass = true;
			}
		}
	}
	
	//check if name is empty
	if(name == null || name == "")
	{
		$("#name_error").html("please enter your name");
	}
	else//they entered something
	{
		valid_name = true;
	}
	
	//check if email is empty
	if(email == null || email == "")
	{
		$("#email_error").html("please enter an email address");
	}
	else//they entered something
	{
		//check for valid email
		if(!email_regex.test(email))
		{
			//invalid email
			$("#email_error").html("invalid email address");
		}
		else//valid email
		{
			valid_email = true;
		}
	}
	
	console.log("submit click: "+ valid_user +"***"+ valid_pass +"***"+ valid_name +"***"+ valid_email);
	
	if(valid_user && valid_pass && valid_name && valid_email)
	{
		register();
	}
}

function available(user)
{
	$.post('available.php', {username:user}, function(output)
	{
		alert("available output: " + output);
		if(output === "true")
		{
			return true;
		}
		else
		{
			return false;
		}
	});
}

function register()
{
	var user = $("#username").val();
	var name = $("#name").val();
	var password = $("#password").val();
	var email = $("#email").val();
	
	$.post('register.php', {un:user, n:name, p:password, e:email}, function()
	{
		$("#form_div").toggle();
		$("#finished").toggle();
	});
}