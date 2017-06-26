package gcom.Model.statistic;

import java.util.List;

import lombok.Data;

@Data
public class ContactStatisticModel {
	private int totalContactCount;
	private int replyCount;
	private List<Integer> flowContactCount;
}
