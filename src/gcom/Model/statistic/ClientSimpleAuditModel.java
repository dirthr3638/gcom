package gcom.Model.statistic;

import java.util.List;

import lombok.Data;

@Data
public class ClientSimpleAuditModel {
	private String action;
	private String userName;
	private String deptName;
	private String userId;	
}
