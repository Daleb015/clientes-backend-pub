package com.daleb.backend.api.rest.auth;

public class SecurityConfig {
	
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpQIBAAKCAQEAtW16kJ6esDutiwcoU39FEDfNRvWLQoBlhCQvjkfeySEJo196\r\n" + 
			"ySq6Cb12mnAVv+lyO39+jXZrCOkARYZij9rxEuNWIev9B+cfr7fMJzSMhgr87VEP\r\n" + 
			"h6KI73msQrLnyYkF1eP094uS9YxLkiQG5Meyu1jBdjoB3b1daA+OOCb8ROQMAWm4\r\n" + 
			"insknwXlSGtbMrlk9YBAlJzdnOQUB3V9r4LFlpS9fLr8M9Ms7hT+BU083aGIj0Pi\r\n" + 
			"PmrzFJY6KjvOEifOU2SVe3H8in+ln35wOD5mx4YcxbzcPjzGuzjUDZ/oopyb5fn0\r\n" + 
			"3Nn+whGEEGrwOTlsgIkDByGQsK5cNg7qMV58AwIDAQABAoIBAQCxvBXFVc9qIJyr\r\n" + 
			"xZBI9DZpY/FTIbuSo4xP/s8j2N61NQtnJGN6bcQhRJ3LQCDeiEkjZoxI84h6CyD7\r\n" + 
			"+2HVQgzHjzTTOIgC1PZiw3FZuw4rRtpvduj63EzAJfeOxAt0kSrJVm+mjvKiYRWr\r\n" + 
			"jmW+fLBo/tNQChXH7/pwtkSCWBbtOMDyWHa6A+75UAT19L4jjQz9pn+g5iY7j/1D\r\n" + 
			"uQNEDAuQl9FqHDWX+0B04+FU1z2LytZs5sNuddDtocTHYqd+3RShePRP4BWzgCrf\r\n" + 
			"YR8Y+vYxqR8aP4X5lwBHZnK5q6pEAFfFq5TUW794T3LhC8Le85LxA9ibebnXZ64f\r\n" + 
			"AHCzXKsRAoGBANz7MXAJMAjauxocCxhdUXwjkMRqFqVN8jNH0+yroXCYf797yWic\r\n" + 
			"wAhWFMmpSTRvbS71T1ko9ct9di0zv+/5DIqlBh0ucHpscYpG45fnQ3SvaBiW1/eU\r\n" + 
			"IWugvogDJUaqMwLccDTkmb2RURoRlt+bhrpRJGGQqT0vz4cXi2QXRYr5AoGBANIt\r\n" + 
			"q0lBunabDAkd8WeBDoLG5H6pHZt0fitswsf6AZtwQiEGXzfK5Bs8IHou3mD6PSEY\r\n" + 
			"/97FaRlt8OgSO+5L71qK2vbCp7b7vpTh5sIfUEU1RYIN8KzKDLT08iPxko2WncLh\r\n" + 
			"Z2qySmQksbZAZt4a+ZJYAU+uTr2mcvwGanwEyqHbAoGAKO5SIulgv1MccuKc339x\r\n" + 
			"d2fzCZ6I4UeFxB/aziygM4Xbq7EXBkx6eVDQeuXjTFYTSC5h6ybeGDn96LN8Ksvb\r\n" + 
			"mdalQkR2ywZJJY6p21oXsYZH5r4R9Ka4ZgIZKIJEpkkiN7ZzBikNnQJMnmKBqptZ\r\n" + 
			"IiujbHrqDg5MBz7CL+DrnakCgYEAwVT5Bj3Lw2YDwC8m68qvCmThshDmByU1GcTF\r\n" + 
			"tyWlbNHqQm6WD8A160d3qGyMajBFIrjYRJbagmW6//4l2qFG3sZzfCC6HXucZ18U\r\n" + 
			"4OPk9oR2F5ezF34UCjB2u9FuuYcePzEP6/lsrImEXt0Gwq+VeqQxMJZqH9GIW4Ob\r\n" + 
			"V9Egg5kCgYEAqfAlvIBoxmJ7Emk7Wl/+y3MsE4jM+Pfyr0U2xHpUfeaMFDPWsYIy\r\n" + 
			"ZyrIKESaTEhRPJcra+QzXhrgbc70/DdvxmBAq5wzfiszwnVDB45H9yS+qTrtK4A/\r\n" + 
			"agrvD/JB909u10GDFpeCuP56qAJsw09N1fSBffwb5EIuwtYlJ0D6b6M=\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtW16kJ6esDutiwcoU39F\r\n" + 
			"EDfNRvWLQoBlhCQvjkfeySEJo196ySq6Cb12mnAVv+lyO39+jXZrCOkARYZij9rx\r\n" + 
			"EuNWIev9B+cfr7fMJzSMhgr87VEPh6KI73msQrLnyYkF1eP094uS9YxLkiQG5Mey\r\n" + 
			"u1jBdjoB3b1daA+OOCb8ROQMAWm4insknwXlSGtbMrlk9YBAlJzdnOQUB3V9r4LF\r\n" + 
			"lpS9fLr8M9Ms7hT+BU083aGIj0PiPmrzFJY6KjvOEifOU2SVe3H8in+ln35wOD5m\r\n" + 
			"x4YcxbzcPjzGuzjUDZ/oopyb5fn03Nn+whGEEGrwOTlsgIkDByGQsK5cNg7qMV58\r\n" + 
			"AwIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
	
}
