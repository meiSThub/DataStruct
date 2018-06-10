package com.mei.test.recall;

/**
 * �˻ʺ�����
 * 
 * ��8*8�������У�����8���ʺ�ʹ֮�˴�֮�䲻��ͬһ�У�ͬһ�У�ͬһ�Խ�����
 * 
 * @author mei
 *
 */
public class EightQueens {

	// ���ڴ��ÿ���ʺ��ŵ�λ�ã��� queensLocations[0]=4����ʾ�ڵ�һ�е�5����һ���ʺ�
	static int array[] = new int[8];

	/**
	 * ��ʼ�㷨
	 */
	public static void eightQuees(int row) {
		 //����н��ֱ���˳�
		if (row == 8) {// ����
			printResult();
			return;
		}
		//��ʼ�ӵ�һ�е����һ��һ��������
		for (int colomn = 0; colomn < 8; colomn++) {
			array[row] = colomn;
			if (judge(row)) {// ��������Ԫ����ǰ������Ĳ���ͬһ�л���ͬһ�Խ�����
				eightQuees(row + 1);// ����һ��
			}
		}
	}

	private static boolean judge(int row) {
		// ѭ���ж���ǰ���[0,row)���Ƿ��г�ͻ
		for (int i = 0; i < row; i++) {
			// queensLocations[row] == queensLocations[i]
			// ��ǰ�з���Ļʺ����������еĻʺ��Ƿ���ͬһ����

			// ��ά����Ĺ��ɣ������n�����i�е�Ԫ�ص��к�����ľ���ֵ�����к�����ľ���ֵ��ȣ�
			// ���Ӧ��Ԫ����ͬһ�Խ�����
			// Math.abs(row - i) == Math.abs(array[row] - array[i])
			if (array[row] == array[i]// ͬһ��
					|| Math.abs(row - i) == Math.abs(array[row] - array[i])) {// ͬһ��
				return false;
			}
		}
		return true;
	}

	static int index = 1;

	public static void printResult() {
		System.out.println("���ࣺ" + index++);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		eightQuees(0);// �ӵ�0�п�ʼ����
	}

}
