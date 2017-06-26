package gcom.Model.statistic;

import lombok.Data;

@Data
public class UserAgentStatisticModel {
	private int totalUserCount;
	private int installedAgentCount;
	private int todayInstalledCount;
	private int requstEnrollCount;
	private int connectAgentCount;
}
