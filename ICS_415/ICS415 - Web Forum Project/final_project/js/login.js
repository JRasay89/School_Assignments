function check()
{
	$.get('check.php', function(output)
	{
		if(output !== 'false')
		{
			window.location.href = 'menu.html';
		}
	});
}

function login(user, pass, remember)
{
	$("#errors").html("");
	
	//output will return true or false
	//true if valid user false if not
	$.ajax(
	{
		type:'post',
		cache:false,
		async:false,
		url:'login.php',
		data: {username:user, password:pass},
		success:function(output)
		{
			console.log('valid login: '+ output);
			if(output === 'true')
			{
				$.ajax(
				{
					type:'post',
					cache:false,
					async:false,
					url:'cookie.php',
					data:{username:user, longTerm:remember}
				});
				window.location.href = 'menu.html';
			}
			else
			{
				$("#errors").html("invalid username/password combination");
			}
		}
	});
}

function go()
{
	var username = $("#username").val();
	var password = $("#password").val();
	var remember = $("#remember").is(':checked');
	
	//had to use 1/0 instead of true/false because i think
	//php wasn't reading them as boolean when they were
	//passed in the post
	if(remember)
	{
		login(username, password, 1);
	
	}
	else
	{
		login(username, password, 0);
	}
	
	console.log('remember?: ' + remember);
	
	
}

function guest()
{
	var username = 'guest';
	var password = '1234';

	login(username, password, 0);
}