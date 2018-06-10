package com.mei.test.encrypt;



/**
 * Sha1�����㷨
 * Created by 48608 on 2017/12/22.
 */

public class SHA {
    //1.׼������
    public static final int[] abcde = {
            0x67452301,
            0xEFCDAB89,
            0x98BADCFE,
            0x10325476,
            0xC3D2E1F0
    };
    //ժҪ���ݴ洢�õ����飨������ĵ�)  20���ֽ�*8=160��
    public static int[] h=new int[5];
    //�����������Ҫ�õ�����ʱ���ݴ洢����
    public static int[] m=new int[80];

    //���帨������

    //���ַ�ת��Ϊʮ�������ַ���
    public static String byteToHexString(byte b){//97
        char[] digit={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        char[] ob=new char[2];
        ob[0]=digit[(b>>>4)&0x0F];//9
        ob[1]=digit[b&0x0f];//7
        String s=new String(ob);//"97"
        return s;
    }
    //���ֽ�����ת��Ϊʮ�������ַ���
    public static String byteArrayToHexString(byte[] byteArray){
        String strDigest="";
        for(int i=0;i<byteArray.length;i++){
            strDigest+=byteToHexString(byteArray[i]);
        }
        return strDigest;
    }

    //4�ֽ�����ת��Ϊint  i��byte�ϳɵ�byteData[]��
	//byteData��һ������Ϊ4���ֽ�����
	//��һ��intֵ��java�о���4���ֽڣ����Կ�����һ��int�洢һ������Ϊ4�ֽ�����
	//byteData[i]&0xff)<<24  ���ǰ��±�Ϊi���ֽڴ�ŵ�int�����λ��������ߵ�1���ֽ�
    public static int byteArrayToInt(byte[] byteData,int i){
        //0a 0b 0c 0d  24       16       8       0
        //         0a000000  or  0b0000  or   0c00  or   0d
        //            0a0b0c0d
		//���ֽ������е�ÿ���ֽڷŵ�int�Ķ�Ӧ���ֽ�λ���ϣ��ںϲ���һ��int
        return ((byteData[i]&0xff)<<24)|((byteData[i+1]&0xff)<<16)|((byteData[i+2]&0xff)<<8)|(byteData[i+3]&0xff);
    }
	
	//��������͸�����ķ������෴�Ĳ���������һ��intֵ�����ֽڲ�ֳ�һ������Ϊ4���ֽ�����
    //����ת��Ϊ4�ֽ�����   int�ֽ⵽byte������
    public static void intToByteArray(int intValue,byte[] byteData,int i){
        byteData[i]=(byte)((intValue>>>24)&0xff); //0xff== 1111 1111
        byteData[i+1]=(byte)((intValue>>>16)&0xff);
        byteData[i+2]=(byte)((intValue>>>8)&0xff);
        byteData[i+3]=(byte)((intValue&0xff));
    }
    /*
    Ft(b,c,d)  ((b&c)|((~b)&d))    (0 <= t <= 19)
    Ft(b,c,d) (b^c^d)             (20 <= t <= 39)
    Ft(b,c,d) ((b&c)|(b&d)|(c&d))  (40 <= t <= 59)
    Ft(b,c,d) (b^c^d)               (60 <= t <= 79)
     */
    public static int f1(int x,int y,int z){
        return (x&y)|(~x&z);//��Ӧ����Ĺ�ʽ
    }
    public static int f2(int x,int y,int z){
        return x^y^z;
    }
    public static int f3(int x,int y,int z){
        return (x&y)|(x&z)|(y&z);
    }
    public static int f4(int x,int y,int z){
        return x^y^z;
    }

    //��ʼ�߼�
    //���ж�ԭ���ݵĲ�λ
    public static byte[] byteArrayFormatData(byte[] byteData){
        //��0�ĸ���
        int fill=0;
        //��λ�����λ��,64�ı���
        int size=0;
        //ԭ���ݵĳ���
        int srcLength=byteData.length;
        //��64������   n%512      56����    8����   53
        int m=srcLength%64;
        if(m<56){
            fill=55-m;
            size=srcLength-m+64;//����ֻ��һ��
        }else if(m==56){
            fill=63;
            size=srcLength+8+64;
        }else{ 
            fill=63-m+56;//   58    60+56  116-64=52   55-52=3
            size=(srcLength+64)-m+64; 
        }
        //��λ�����ɵ������������
        byte[] newbyte=new byte[size];
        System.arraycopy(byteData,0,newbyte,0,srcLength);

        //��1
        int startLocation=srcLength;
        newbyte[startLocation++]=(byte)0x80;
        //��0
        for(int i=0;i<fill;i++){
            newbyte[startLocation++]=(byte)0x00;
        }
        //�����ȵ�λ��  �ֽ�*8=?λ   512-468=64λ��������ų���
        long n=(long)srcLength*8;
        byte h8=(byte)(n&0xff);
        byte h7=(byte)((n>>8)&0xff);
        byte h6=(byte)((n>>16)&0xff);
        byte h5=(byte)((n>>24)&0xff);
        byte h4=(byte)((n>>32)&0xff);
        byte h3=(byte)((n>>40)&0xff);
        byte h2=(byte)((n>>48)&0xff);
        byte h1=(byte)((n>>56));
        newbyte[startLocation++]=h1;
        newbyte[startLocation++]=h2;
        newbyte[startLocation++]=h3;
        newbyte[startLocation++]=h4;
        newbyte[startLocation++]=h5;
        newbyte[startLocation++]=h6;
        newbyte[startLocation++]=h7;
        newbyte[startLocation++]=h8;

        return newbyte;
    }
    //��ʼ�������� ��ժҪ
    public static int process_input_bytes(byte[] byteData){
        System.arraycopy(abcde,0,h,0,abcde.length);
        //��ʽ������
        byte[] newbyte=byteArrayFormatData(byteData);
        //�����ж��ٸ����
        int mCount=newbyte.length/64;
        //ѭ������ÿһ�������
        for(int pos=0;pos<mCount;pos++){
            //��ÿһ�鶼���м��ܼ���
            //(1). �� Mi �ֳ� 16 ���� W0, W1, ... , W15,  W0 ������ߵ���
            for(int i=0;i<16;i++){
                m[i]=byteArrayToInt(newbyte,(pos*64)+(i*4));
            }
            //����
            encrypt();
        }

        return 20;
    }
    //n��һ��������0<=n<=32��Sn(X) = (X<<n)OR(X>>(32-n))
    public static int s(int x,int i){
        return (x<<i)|x>>>(32-i);
    }
    public static void encrypt(){
        //(2). ���� t = 16 �� 79 ��
        // Wt = S1(Wt-3 XOR Wt-8 XOR Wt- 14 XOR Wt-16).
        for(int t=16;t<=79;t++){
            m[t]=s(m[t-3]^m[t-8]^m[t-14]^m[t-16],1);
        }
        //3.�� A = H0, B = H1, C = H2, D = H3, E = H4.
        int[] tempabcde=new int[5];
        for(int i=0;i<tempabcde.length;i++){
            tempabcde[i]=h[i];
        }
        //4.���� t = 0 �� 79��ִ�������ѭ��
        //TEMP = S5(A) + ft(B,C,D) + E + Wt + Kt;
        //E = D; D = C; C = S30(B); B = A; A = TEMP;
        //һ����80�β���
        //Kt = 0x5A827999  (0 <= t <= 19)
//        Kt = 0x6ED9EBA1 (20 <= t <= 39)
//        Kt = 0x8F1BBCDC (40 <= t <= 59)
//        Kt = 0xCA62C1D6 (60 <= t <= 79)
        for(int i=0;i<=19;i++){
            int temp=s(tempabcde[0],5)
                    +f1(tempabcde[1],tempabcde[2],tempabcde[3])
                    +tempabcde[4]
                    +m[i]+0x5A827999;
            tempabcde[4]=tempabcde[3];
            tempabcde[3]=tempabcde[2];
            tempabcde[2]=s(tempabcde[1],30);
            tempabcde[1]=tempabcde[0];
            tempabcde[0]=temp;
        }
        for(int i=20;i<=39;i++){
            int temp=s(tempabcde[0],5)
                    +f2(tempabcde[1],tempabcde[2],tempabcde[3])
                    +tempabcde[4]
                    +m[i]+0x6ED9EBA1;
            tempabcde[4]=tempabcde[3];
            tempabcde[3]=tempabcde[2];
            tempabcde[2]=s(tempabcde[1],30);
            tempabcde[1]=tempabcde[0];
            tempabcde[0]=temp;
        }
        for(int i=40;i<=59;i++){
            int temp=s(tempabcde[0],5)
                    +f3(tempabcde[1],tempabcde[2],tempabcde[3])
                    +tempabcde[4]
                    +m[i]+0x8F1BBCDC;
            tempabcde[4]=tempabcde[3];
            tempabcde[3]=tempabcde[2];
            tempabcde[2]=s(tempabcde[1],30);
            tempabcde[1]=tempabcde[0];
            tempabcde[0]=temp;
        }
        for(int i=60;i<=79;i++){
            int temp=s(tempabcde[0],5)
                    +f4(tempabcde[1],tempabcde[2],tempabcde[3])
                    +tempabcde[4]
                    +m[i]+0xCA62C1D6;
            tempabcde[4]=tempabcde[3];
            tempabcde[3]=tempabcde[2];
            tempabcde[2]=s(tempabcde[1],30);
            tempabcde[1]=tempabcde[0];
            tempabcde[0]=temp;
        }
        //5.�� H0 = H0 + A, H1 = H1 + B, H2 = H2 + C, H3 = H3 + D, H4 = H4 + E.
        for(int i=0;i<tempabcde.length;i++){
            h[i]=h[i]+tempabcde[i];
        }
        //�����һ�β���
        //���֮ǰ�����ݣ���ʼ��һ����ļ���
        for(int i=0;i<m.length;i++){
            m[i]=0;
        }

    }
    //���Ѿ���õ������ṩһ���ӿڽ�����������
    public static byte[] getDigestOfBytes(byte[] byteData){
        process_input_bytes(byteData);
        byte[] digest=new byte[20];
        for(int i=0;i<h.length;i++){
            intToByteArray(h[i],digest,i*4);
        }
        return digest;
    }
    public static String getDigestOfString(byte[] byteData){
        return byteArrayToHexString(getDigestOfBytes(byteData));
    }

    public static  void main(String[] args){
        //ad93ae3d06a9114b3cbb33b6433ad546f0aa9f42
        //378940973d2f16265b7a7f2a78a253c45d953b0b
        String param="jeTt";
        System.out.println("����ǰ:"+param);
        String digest=getDigestOfString(param.getBytes());
        System.out.println("���ܺ�Ľ����"+digest);
    }
}










