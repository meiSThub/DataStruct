package com.mei.test.datastruct.list;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ��ջʵ���沨�����ʽ
 */
public class ReversePolishNotation {
	
	/**
	 * ����׺���ʽת���ɺ�׺���ʽ
	 * @param str
	 */
	public char[] getEndExpression(String str){
		if (str==null||"".equals(str)) {
			return null;
		}
		char[] source=str.toCharArray();
		int index=0;
		char[] res=new char[source.length];
		LinkedList<Character> stack=new LinkedList<Character>();
		stack.push('#');
		for (int i = 0; i < source.length; i++) {
			if (isNum(source[i])) {//���ַ��Ƿ�������
				res[index++]=source[i];
			}else if (isOperator(source[i])||source[i]=='('||source[i]==')'||source[i]=='#') {
				Character top=stack.peek();
				if (isBigger(top, source[i])) {//���ַ������ȼ���ջ���ַ����ȼ��ߣ����ջ
					stack.push(source[i]);
				}else {//ջ���ַ����ȼ��ߣ����ջ
					while (!stack.isEmpty()) {
						top=stack.peek();//ɾ��ջ���ַ�
						if (isOperator(top)) {//���ջ���ַ��������
							res[index++]=top;
							stack.removeFirst();
						}else if (source[i]==')'&&top=='(') {
							stack.removeFirst();
							break;
						}else if (isBigger(top, source[i])) {
							stack.push(source[i]);
							break;
						}else if (top=='#') {
							break;
						}
					}
				}
			} 
		}
		
		while (!stack.isEmpty()) {
			Character top=stack.pop();
			if (top!='#') {
				res[index++]=top;
			}
		}
		//��ӡ���
		System.out.println("��׺���ʽΪ��");
		for (int i = 0; i < res.length; i++) {
			System.out.print(" "+res[i]);
		}
		return res;
	}
	
	/**
	 * �Ƿ�������
	 * @param c
	 * @return
	 */
	public boolean isNum(char c){
		Pattern pattern=Pattern.compile("[0-9]*");
		Matcher isNum=pattern.matcher(String.valueOf(c));
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	/**
	 * �Ƿ��������
	 * @param c
	 * @return
	 */
	private boolean isOperator(char c){
		switch (c) {
		case '+':
		case '-':
		case '*':
		case '/':
			return true;
		}
		return false;
	}
	
	/**
	 * �µ��ַ������ȼ��Ƿ��ջ���ַ������ȼ���
	 * @param top		ջ���ַ�
	 * @param newChar	���ַ�
	 * @return
	 */
	private boolean isBigger(char top,char newChar) {
		boolean isBigger=false;
		switch (top) {
		case '+':
			switch (newChar) {
			case '+':
			case '-':
			case ')':
			case '#':
				isBigger= false;
				break;
			case '*':
			case '/':
			case '(':
				isBigger= true;
				break;
			}
			break;
		case '-':
			switch (newChar) {
			case '+':
			case '-':
			case ')':
			case '#':
				isBigger= false;
				break;
			case '*':
			case '/':
			case '(':
				isBigger= true;
				break;
			}
			break;
		case '*':
			switch (newChar) {
			case '+':
			case '-':
			case '*':
			case '/':
			case ')':
			case '#':
				isBigger= false;
				break;
			case '(':
				isBigger= true;
				break;
			}
			break;
		case '/':
			switch (newChar) {
			case '+':
			case '-':
			case '*':
			case '/':
			case ')':
			case '#':
				isBigger= false;
				break;
			case '(':
				isBigger= true;
				break;
			}
			break;
		case '(':
			switch (newChar) {
			case '+':
			case '-':
			case '*':
			case '/':
			case '(':
				isBigger= true;
				break;
			case ')':
				isBigger= false;
				break;
			case '#':
				isBigger= false;
				break;
			}
			break;
		case ')':
			switch (newChar) {
			case '+':
			case '-':
			case '*':
			case '/':
			case '(':
			case ')':
			case '#':
				isBigger= false;
				break;
			}
			break;
		case '#':
			switch (newChar) {
			case '+':
			case '-':
			case '*':
			case '/':
			case '(':
				isBigger=true;
				break;
			case ')':
			case '#':
				isBigger= false;
				break;
			}
			break;
		}
		
		return isBigger;
	}
	
	/**
	 * 
	 * @param str  �����������׺���ʽ
	 * @return
	 */
	public int calculate(String str){
		char[] res=getEndExpression(str);
		LinkedList<Integer> stack=new LinkedList<Integer>();
		for (int i = 0; i < res.length; i++) {
			if (isNum(res[i])) {
				stack.push(Character.getNumericValue(res[i]));
			}else if(res[i]!=0){
				int num1=stack.pop();
				int num2=stack.pop();
				int result=0;
				switch (res[i]) {
				case '+':
					result=num2+num1;
					break;
				case '-':
					result=num2-num1;
					break;
				case '*':
					result=num2*num1;
					break;
				case '/':
					if (num1!=0) {
						result=num2/num1;
					}
					break;
				}
				stack.push(result);
			}
		}
		
		return stack.pop();
	}

	public static void main(String[] args) {
		ReversePolishNotation reNotation=new ReversePolishNotation();
		int result=reNotation.calculate("9+((3-1)*5-4)*3+8/2");
		System.out.println("");
		System.out.println("��������"+result);
	}

}
