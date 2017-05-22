function validateMessage(message){
	var l = message.length
	if(l>16){
		alert("too many characters (max 16)")
		return false
	}
	else{
		return true
	}
}