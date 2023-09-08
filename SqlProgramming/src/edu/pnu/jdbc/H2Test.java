package edu.pnu.jdbc;

import java.util.List;

public class H2Test {
	public static void main(String[] args) {
		
		H2study hs = new H2study("org.h2.Driver",
								"jdbc:h2:tcp://localhost/~/.h2/jdbcstudy",
								"sa",
								"abcd");
		
		List<List<String>> list = hs.queryWithStatement("select * from testtable");
		
		hs.printList(list);
	}
}
