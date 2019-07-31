package test;

public class test {

	public static void main(String[] args) {
		String str = "abcdefg";
		int a;
		StringBuffer stb=new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			a=str.charAt(i)+1;
			char c=(char)a;
			stb.append(c);
			
		}System.out.println(stb.toString());
	}

}
