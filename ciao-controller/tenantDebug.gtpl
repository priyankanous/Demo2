<html>
<head>
<style type="text/css">
header {
	text-align: justify;
	letter-spacing: 1px;
	width:100%;
	height:7em;
	padding: 1em 5%;
    	background: #4B99AD;
	color: #fff;
    	font: 13px "Lucida Sans Unicode", "Lucida Grande", sans-serif;
}

.section-style-2 {
	width: 350px;
	float: left;
	padding: 10px;
	border-left: 1px solid;
}

progress[value] {
	-webkit-appearance: none;
	appearance: none;
	width: 250px;
	height: 20px;
    	margin: 10px 20px auto;
}

progress[value]::-webkit-progress-bar {
	background-color: #eee;
	border-radius: 2px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.25) inset;
}

progress[value]::-webkit-progress-value {
	background-image:
		-webkit-linear-gradient(-45deg,
				transparent 33%, rgba(0, 0, 0, .1) 33%,
				rgba(0, 0, 0, .1) 66%, transparent 66%),
		-webkit-linear-gradient(top,
				rgba(255, 255, 255, .25),
				rgba(0, 0, 0, .25)),
		-webkit-linear-gradient(left, #4B99AD, #f44);
	border-radius: 2px;
	background-size: 35px 20px, 100% 100%, 100% 100%;
}

.form-style-1 {
    margin:10px auto;
    max-width: 400px;
    padding: 20px 12px 10px 20px;
    font: 13px "Lucida Sans Unicode", "Lucida Grande", sans-serif;
}

.form-style-1 li {
    padding: 0;
    display: block;
    list-style: none;
    margin: 10px 0 0 0;
}
.form-style-1 label{
    margin:0 0 3px 0;
    padding:0px;
    display:block;
    font-weight: bold;
}
.form-style-1 input[type=text], 
textarea, 
select{
    box-sizing: border-box;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    border:1px solid #BEBEBE;
    padding: 7px;
    margin:0px;
    -webkit-transition: all 0.30s ease-in-out;
    -moz-transition: all 0.30s ease-in-out;
    -ms-transition: all 0.30s ease-in-out;
    -o-transition: all 0.30s ease-in-out;
    outline: none;  
}
.form-style-1 input[type=text]:focus, 
.form-style-1 input[type=submit], .form-style-1 input[type=button]{
    background: #4B99AD;
    padding: 8px 15px 8px 15px;
    border: none;
    color: #fff;
}

.form-style-1 input[type=submit]:hover, .form-style-1 input[type=button]:hover{
    background: #4691A4;
    box-shadow:none;
    -moz-box-shadow:none;
    -webkit-box-shadow:none;
}
</style>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
</script>
<script>
$('document').ready(function() {
	function getScopedToken(projectId) {
		str = '{ "auth": { "identity": { "methods": [ "token" ], "token": { "id": "" } }, "scope": { "project": { "id": "" } } } }';
		authObj = JSON.parse(str);
		authObj.auth.identity.token.id = localStorage.auth_token;
		authObj.auth.scope.project.id = projectId;
		url = localStorage.keystoneURL + '/v3/auth/tokens';
		console.log(url);

		request = $.ajax({
			url: url,
			type: 'POST',
			data: JSON.stringify(authObj),
			dataType: 'json',
			contentType: 'application/json',
			async: false,
		})
		.done(function (data, textStatus, request) {
			var jsonObj = JSON.parse(request.responseText);
			console.log(JSON.stringify(jsonObj));
			console.log(request.getResponseHeader('X-Subject-Token'));
			localStorage.setItem("tokenObj", request.responseText);
			localStorage.setItem("auth_token", request.getResponseHeader('X-Subject-Token'));
		})
		.fail(function (jqXHR, textStatus, errorThrown) {
			alert(textStatus);
		});

	}

	$("#workload_form").submit(function(event){
		document.getElementById("token").value = localStorage.auth_token;
		var tenant = $('#tenants').val();
		console.log(tenant);
		var workload = $('#workloads').val();
		console.log(workload);
		var num = $('#num_instances').val();
		var trace = document.getElementById("trace").checked;
		var label = $('#label').val();
		console.log(num);
		getScopedToken(tenant);
		var formData = {
			'tenant_id' : tenant,
			'workload_id' : workload,
			'num_instances' : num,
			'token' : localStorage.auth_token,
			'trace' : trace,
			'label' : label
		};
		request = $.ajax({
			url: '/workload',
			type: 'POST',
			data: formData,
		})
		.done(function (data, textStatus, request) {
			alert(textStatus);
		})
		.fail(function (jqXHR, textStatus, errorThrown) {
			alert(textStatus);
		});

		event.preventDefault();
	});

	function getUserId() {
		tokenObj = JSON.parse(localStorage.tokenObj);
		userObj = tokenObj.token.user;	
		username = userObj.name;
		document.getElementById("username").innerHTML = username;
		console.log(JSON.stringify(userObj));
	}

	function getUserTenants() {
		var tokenObj = JSON.parse(localStorage.tokenObj);
		var userObj = tokenObj.token.user;
		var userid = userObj.id;
		var url = {{.}} + "/v3/users/" + userid + "/projects";
		console.log(JSON.stringify(userObj));
		console.log(url)
		request = $.ajax({
			url: url,
			type: 'GET',
			datatype: 'json',
			beforeSend: function(request) {
				request.setRequestHeader("X-Auth-Token", localStorage.auth_token);
			}
		})
		.done(function (data, textStatus, request) {
			console.log(request.responseText);
			var jsonObj = JSON.parse(request.responseText);
			$('#tenants').empty();
			projects = jsonObj.projects;
			$.each(projects, function(index, project) {
				$('#tenants').append('<option value="' + project.id + '">' + project.name + '</option>');
				console.log(project.name);
			});
		})
		.fail(function (jqXHR, textStatus, errorThrown) {
			alert(textStatus);
		});
	}

	function getWorkloads() {
		request = $.ajax({
			url: '/getWorkloads',
			type: 'GET',
			dataType: 'json',
			beforeSend: function (request) {
				request.setRequestHeader("X-Auth-Token", localStorage.auth_token);
			}
		})
		.done(function (data, textStatus, request) {
			var workloads = JSON.parse(request.responseText);
			$('#workloads').empty();
			$.each(workloads, function(index, wl) {
				$('#workloads').append('<option value="' + wl.id + '">' + wl.description + '</option>');
				console.log(wl.description);
			});

		})
		.fail(function (jqXHR, textStatus, errorThrown) {
			alert(textStatus);
		});
	}

	getUserTenants();
	getUserId();
	getWorkloads();

});
</script>
</head>
<body>
<header>
	<h1>ciao</h1>
	Logged in as:<div id="username">Placeholder</div>
</header>
<section class=section-style-2>
<h1>Launch Instance</h1>
<form id="workload_form">
<ul id="tenant_list" class="form-style-1">
	<li><label>Tenant</label>
		<select id="tenants">
		</select>
	</li>
	<li><label>Workload</label>
		<select id="workloads">
		</select>
	</li>
	<li><label>Number of Instances to Launch</label>
		<input type="int" id="num_instances" name="num_instances">
	</li>
	<li>
		<input type="submit" value="Send">
	</li>
	<li>
		<input type='hidden' name="token" id="token"></input>
	</li>
	<li><label>Trace this Instance</label>
		<input type="checkbox" name="trace" id="trace">
		<label>Label</label>
		<input type="text" name="label" id="label">
	</li>
</ul>
</form>
</section>
</body>
</html>
