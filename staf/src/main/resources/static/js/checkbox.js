function show(id)
{
	if(id == 'all')
	{
		var idCol = document.querySelectorAll("[class*=Col]");
		for (var i = 0; i < idCol.length; i++) 
  		{
  			idCol[i].style.display = "table-cell";
        }
	}
	else 
	{
		var checkbox = document.getElementById(id);
		var idCol = document.getElementsByClassName(id + "Col");
	
	  	if (checkbox.checked == true) 
	  	{
	  		document.getElementById(id + "Label").style.color = "#E68D1D";
	  		
	  		for (var i = 0; i < idCol.length; i++) 
	  		{
	  			idCol[i].style.display = "table-cell";
            }
	  	} 
	  	else 
	  	{
	  		document.getElementById(id + "Label").style.color = "#141167 ";
	  		
	  		for (var i = 0; i < idCol.length; i++) 
	  		{
	  			idCol[i].style.display = "none";
            }
	  	}
	}
}