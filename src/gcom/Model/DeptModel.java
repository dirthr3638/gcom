package gcom.Model;

import lombok.Data;

@Data
public class DeptModel {
	private int dept_no;
	private int parent;
	private int leap;
	private int dept;
	private int admin_no;
	private String name;
	private String shortName;
	private boolean valid;
	private int childCount;	
}
