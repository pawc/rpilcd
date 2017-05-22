<!DOCTYPE html> 
<html> 
<head> 
<title>RPi controller</title> 
<meta charset="utf-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/b.." integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/cs.."> 

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bo.." integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script> 

<style> 
html, body { 
fint-size: 14px; 
} 

.form-container { 
position: relative; 
overflow: auto; 
padding: 2rem .5rem; 
text-align: center; 
border-bottom: 1px solid #eee; 

box-shadow: 0 3px 5px #eee; 
} 

.form-container form { 
display: inline-block; 
text-align: left; 
} 

.form-container form .message { 
width: 40rem; 
} 
</style> 	
</head> 
<body> 
<div class="form-container"> 
<script type="text/javascript" src="/webclient/static/validate.js">
</script>
<form action="result.html" method="post" id="sendMsgForm" class="form-inline" onsubmit="return validateMessage(document.getElementById('message').value)"> 
<div class="form-group"> 
<label class="sr-only" for="message">message</label> 
<input id="message" type="text" name="message" class="form-control message" placeholder="message"><br> 
gpio 0 <input id="checkbox" type="checkbox" name="output0"><br>
gpio 1 reserved for LCD <input id="checkbox" type="checkbox" name="output1"><br>
gpio 2 <input id="checkbox" type="checkbox" name="output2"><br>
gpio 3 <input id="checkbox" type="checkbox" name="output3"><br>
gpio 4 <input id="checkbox" type="checkbox" name="output4"><br>
gpio 5 <input id="checkbox" type="checkbox" name="output5"><br>
gpio 6 <input id="checkbox" type="checkbox" name="output6"><br>
gpio 7 reserved for LCD <input id="checkbox" type="checkbox" name="output7"><br>
</div> 
<button type="submit" class="btn btn-default submit"><i class="fa fa-paper-plane-o" aria-hidden="true"></i> send</button> 
</form> 

<div> 
${result}
</body> 
</html>
