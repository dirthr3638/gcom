function getDashboardFlotChartData(obj){
	var data;
	$.ajax({      
        type:"POST",  
        url:'/ax/admin/statistic/chart',
        async: false,
        data:input,
        success:function(args){   
        	//console.log(args)
        	data = args;

        },   
        //beforeSend:showRequest,  
        error:function(e){  
            console.log(e.responseText);  
        }  
    }); 
	return data;
}

function getDashboardBoxData(obj){
	var data;
	$.ajax({      
        type:"POST",  
        url:'/ax/admin/statistic/chart',
        async: false,
        data:input,
        success:function(args){   
        	//console.log(args)
        	data = args;

        },   
        //beforeSend:showRequest,  
        error:function(e){  
            console.log(e.responseText);  
        }  
    }); 
	return data;
}

function getDashboardSimpleRequestData(obj){
	var data;
	$.ajax({      
        type:"POST",  
        url:'/ax/admin/statistic/chart',
        async: false,
        data:input,
        success:function(args){   
        	//console.log(args)
        	data = args;

        },   
        //beforeSend:showRequest,  
        error:function(e){  
            console.log(e.responseText);  
        }  
    }); 
	return data;
}

function getDashboardSimpleAuditData(obj){
	var data;
	$.ajax({      
        type:"POST",  
        url:'/ax/admin/statistic/chart',
        async: false,
        data:input,
        success:function(args){   
        	//console.log(args)
        	data = args;

        },   
        //beforeSend:showRequest,  
        error:function(e){  
            console.log(e.responseText);  
        }  
    }); 
	return data;
}

function getDashboardSimpleContactData(obj){
	var data;
	$.ajax({      
        type:"POST",  
        url:'/ax/admin/statistic/chart',
        async: false,
        data:input,
        success:function(args){   
        	//console.log(args)
        	data = args;

        },   
        //beforeSend:showRequest,  
        error:function(e){  
            console.log(e.responseText);  
        }  
    }); 
	return data;
}

