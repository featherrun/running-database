public class TestJava {
	public static void main(String[] args) {
		Main.main(new String[]{
				"output=out/output-java/",
				"type=Java",
				"isAssign=true",
				"extendsName=AbsEntity",
		});

		Main.main(new String[]{
				"output=out/output-java/",
				"type=JavaDao",
		});

		Main.main(new String[]{
				"output=out/output-java/",
				"type=JavaModel",
		});
	}
}
