<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<div id="org_tree" style="overflow: hidden;">
     <ul>
         <li class="jstree-open" data-jstree='{"type":"root"}'>Company
             <ul>
                 <li>나오면안됨
                     <ul>
                         <li>사업 1팀</li>
                         <li>사입 2팀</li>
                         <li>디자인팀</li>
                     </ul>
                 </li>

                 <li>IT지원</li>
             </ul>
         </li>
     </ul>
 </div>
 
 
 
 <script>
 

 $(document).ready(function(){
	 $('#org_tree').jstree({
	     'core' : {
	         'check_callback' : true,
	         'data' : JSON.parse('${deptJson}')
	     },
         'plugins' : [ 'types', 'dnd', 'checkbox', 'contextmenu' ],
         'types' : {
             'default' : {
                 'icon' : 'fa fa-user-circle-o'
             },
             'root' : {
                 'icon' : 'fa fa-building'
             }
	     },
         "contextmenu":{         
             "items": function($node) {
                 var tree = $("#org_tree").jstree(true);
                 return {
                     "Create": {
                         "separator_before": false,
                         "separator_after": false,
                         "label": "생성",
                         "action": function (obj) { 
                             $node = tree.create_node($node);
                             tree.edit($node);
                         }
                     },
                     "Rename": {
                         "separator_before": false,
                         "separator_after": false,
                         "label": "이름변경",
                         "action": function (obj) { 
                             tree.edit($node);
                         }
                     },                         
                     "Remove": {
                         "separator_before": false,
                         "separator_after": false,
                         "label": "삭제",
                         "action": function (obj) { 
                             tree.delete_node($node);
                         }
                     }
                 };
             }
         },
	 });
 });
 
 </script>