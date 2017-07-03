<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<div id="org_tree" style="overflow: hidden;">
     <ul>
         <li class="jstree-open" data-jstree='{"type":"root"}'>Company
             <ul>
             </ul>
         </li>
     </ul>
 </div>
 
 
 
 <script>

 var getCheckedDept = function(){
	 var result = $('#org_tree').jstree(true).get_selected();
	 $.each(result, function(index, value){
		 result[index] = result[index].replace('_','');
	 })

	 var reValue = [];
	 $.each(result, function(i, el){
 		 if($.inArray(el, reValue) === -1) reValue.push(el);
	 })
	 
	 return reValue;
 }

 $(document).ready(function(){
	 $('#org_tree').jstree({
	     'core' : {
	         'check_callback' : true,
	         'data' : JSON.parse('${deptJson}')
	     },
         'plugins' : [ 'types', 'dnd', 'checkbox'],
         'types' : {
             'default' : {
                 'icon' : 'fa fa-user-circle-o'
             },
             'root' : {
                 'icon' : 'fa fa-building'
             }
	     },
	 });
	 
	 $('#org_tree').bind('loaded.jstree', function(e, data) {
	 		$('#org_tree').jstree(true).check_all();		
		})
 });
 
 </script>