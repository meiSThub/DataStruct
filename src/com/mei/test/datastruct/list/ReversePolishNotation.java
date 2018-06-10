package com.mei.test.datastruct.list;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用栈实现逆波兰表达式
 */
public class ReversePolishNotation {
	
	/**
	 * 把中缀表达式转化成后缀表达式
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
			if (isNum(source[i])) {//新字符是否是数字
				res[index++]=source[i];
			}else if (isOperator(source[i])||source[i]=='('||source[i]==')'||source[i]=='#') {
				Character top=stack.peek();
				if (isBigger(top, source[i])) {//新字符的优先级比栈顶字符优先级高，则进栈
					stack.push(source[i]);
				}else {//栈顶字符优先级高，则出栈
					while (!stack.isEmpty()) {
						top=stack.peek();//删除栈顶字符
						if (isOperator(top)) {//如果栈顶字符是运算符
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
		//打印结果
		System.out.println("后缀表达式为：");
		for (int i = 0; i < res.length; i++) {
			System.out.print(" "+res[i]);
		}
		return res;
	}
	
	/**
	 * 是否是数字
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
	 * 是否是运算符
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
	 * 新的字符的优先级是否比栈顶字符的优先级高
	 * @param top		栈顶字符
	 * @param newChar	新字符
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
	 * @param str  计算给定的中缀表达式
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
		System.out.println("计算结果："+result);
	}

}
