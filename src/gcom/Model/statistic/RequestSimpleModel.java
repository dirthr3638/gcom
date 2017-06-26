package gcom.Model.statistic;

import java.util.List;

import lombok.Data;

@Data
public class RequestSimpleModel {
	private int requestNo;
	private int requestSubject;
	private String requestDept;
	private String requestWriter;
}
