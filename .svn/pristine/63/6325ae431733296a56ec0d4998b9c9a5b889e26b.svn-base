package org.unism.pro.service;

public class Bp {

	public static double Step=0.01;	//步长，学习率
	public static double mc=0.9;	//动量因子
	public static int TrainTimes=100;//学习次数
	public static int L0=5;	//输入层节点数
	public static int L1=3;	//隐层节点数
	public static int L2=1;	//输出层节点数
	
	public static double Weight01[][]=new double[L0][L1];
	public static double Weight12[][]=new double[L1][L2];
	public static double Threshold1[]=new double[L1];
	public static double Threshold2[]=new double[L2];
	
	/*函数说明
	 * public static void init()//初始化权值和阈值
	 * public static double transfer(double x)//传递函数
	 * public static double train(double i1 ,double i2,double i3, double i4,double i5,double d)
	 * 		训练函数。输入参数是归一化处理之后的数据，返回正向传播的结果
	 * public static double run(double i1,double i2,double i3,double i4,double i5)
	 * 		预测函数。输入参数是归一化处理之后的数据，返回值也是归一化的。和实际值进行比较，需要反归一化
	 * public static void print()//打印所有的权值和阈值
	 * public static void main(String[] args) //主函数，负责调用训练函数和测试函数，输出测试的结果
	 */
	Bp()//构造函数
	{
		
	}
	public void init()//初始化权值和阈值，[-1,1)
	{
		for(int i=0;i<L0;i++)
		{
			for(int j=0;j<L1;j++)
			{
				Weight01[i][j]=Math.random()*2-1;
			}
		}
		for(int i=0;i<L1;i++)
		{
			for(int j=0;j<L2;j++)
			{
				Weight12[i][j]=Math.random()*2-1;
			}
		}
		for(int i=0;i<L1;i++)
		{
			Threshold1[i]=Math.random()*2-1;
		}
		for(int i=0;i<L2;i++)
		{
			Threshold2[i]=Math.random()*2-1;
		}
	}
	public static double transfer(double x)//传递函数，sigmiod
	{
		return 1/(1+Math.exp(-x));
	}
	public double train(double i1 ,double i2,double i3, double i4,double i5,double d)//训练函数
	{
		double Hide1,Hide2,Hide3,out;
		//计算隐含层的神经元值，隐层的输入
		Hide1=Threshold1[0]+i1*Weight01[0][0]+i2*Weight01[1][0]+i3*Weight01[2][0]+i4*Weight01[3][0]+i5*Weight01[4][0];
		Hide2=Threshold1[1]+i1*Weight01[0][1]+i2*Weight01[1][1]+i3*Weight01[2][1]+i4*Weight01[3][1]+i5*Weight01[4][1];
		Hide3=Threshold1[2]+i1*Weight01[0][2]+i2*Weight01[1][2]+i3*Weight01[2][2]+i4*Weight01[3][2]+i5*Weight01[4][2];

		//使用S函数，隐层的输出
		Hide1=transfer(Hide1);
		Hide2=transfer(Hide2);
		Hide3=transfer(Hide3);

		//计算输出层的值，输出层的输入
		out=Threshold2[0]+Hide1*Weight12[0][0]+Hide2*Weight12[1][0]+Hide3*Weight12[2][0];

		//使用S函数，输出层的输出
		out=transfer(out);

		//计算误差，反向传播
		double error1[]=new double[L1];
		double error2[]=new double[L2];

		error2[0]=out*(1-out)*(d-out);

		error1[0]=Hide1*(1-Hide1)*(Weight12[0][0])*(error2[0]);
		error1[1]=Hide2*(1-Hide2)*(Weight12[1][0])*(error2[0]);
		error1[2]=Hide3*(1-Hide3)*(Weight12[2][0])*(error2[0]);

		//调整阈值。添加动量因子
		{
			Threshold1[0]=mc*Threshold1[0]-(1-mc)*Step*error1[0];
			Threshold1[1]=mc*Threshold1[1]-(1-mc)*Step*error1[1];
			Threshold1[2]=mc*Threshold1[2]-(1-mc)*Step*error1[2];
		}
		Threshold2[0]=mc*Threshold2[0]-(1-mc)*Step*error2[0];

		//调整权值。添加动量因子
		{
			Weight01[0][0]=mc*Weight01[0][0]-(1-mc)*Step*i1*error1[0];
			Weight01[0][1]=mc*Weight01[0][1]-(1-mc)*Step*i1*error1[1];
			Weight01[0][2]=mc*Weight01[0][2]-(1-mc)*Step*i1*error1[2];
			
			Weight01[1][0]=mc*Weight01[1][0]-(1-mc)*Step*i2*error1[0];
			Weight01[1][1]=mc*Weight01[1][1]-(1-mc)*Step*i2*error1[1];
			Weight01[1][2]=mc*Weight01[1][2]-(1-mc)*Step*i2*error1[2];
			
			Weight01[2][0]=mc*Weight01[2][0]-(1-mc)*Step*i3*error1[0];
			Weight01[2][1]=mc*Weight01[2][1]-(1-mc)*Step*i3*error1[1];
			Weight01[2][2]=mc*Weight01[2][2]-(1-mc)*Step*i3*error1[2];
			
			Weight01[3][0]=mc*Weight01[3][0]-(1-mc)*Step*i4*error1[0];
			Weight01[3][1]=mc*Weight01[3][1]-(1-mc)*Step*i4*error1[1];
			Weight01[3][2]=mc*Weight01[3][2]-(1-mc)*Step*i4*error1[2];
			
			Weight01[4][0]=mc*Weight01[4][0]-(1-mc)*Step*i5*error1[0];
			Weight01[4][1]=mc*Weight01[4][1]-(1-mc)*Step*i5*error1[1];
			Weight01[4][2]=mc*Weight01[4][2]-(1-mc)*Step*i5*error1[2];
		}
		Weight12[0][0]=mc*Weight12[0][0]-(1-mc)*Step*Hide1*error2[0];
		Weight12[1][0]=mc*Weight12[1][0]-(1-mc)*Step*Hide2*error2[0];
		Weight12[2][0]=mc*Weight12[2][0]-(1-mc)*Step*Hide3*error2[0];
		return out;
	}
	public double run(double i1,double i2,double i3,double i4,double i5)//预测函数
	{
		double Hide1,Hide2,Hide3,out;
		Hide1=1*Threshold1[0]+i1*Weight01[0][0]+i2*Weight01[1][0]+i3*Weight01[2][0]+i4*Weight01[3][0]+i5*Weight01[4][0];
		Hide2=1*Threshold1[1]+i1*Weight01[0][1]+i2*Weight01[1][1]+i3*Weight01[2][1]+i4*Weight01[3][1]+i5*Weight01[4][1];
		Hide3=1*Threshold1[2]+i1*Weight01[0][2]+i2*Weight01[1][2]+i3*Weight01[2][2]+i4*Weight01[3][2]+i5*Weight01[4][2];

		Hide1=transfer(Hide1);
		Hide2=transfer(Hide2);
		Hide3=transfer(Hide3);

		out=1*Threshold2[0]+Hide1*Weight12[0][0]+Hide2*Weight12[1][0]+Hide3*Weight12[2][0];
		return transfer(out);
	}
	public static void print()//打印所有的权值和阈值
	{
		System.out.println("权值Weight01:");
		for(int i=0;i<L0;i++)
		{
			for(int j=0;j<L1;j++)
			{
				System.out.print(Weight01[i][j]+"	");
			}
			System.out.println();
		}
		System.out.println("权值Weight12:");
		for(int i=0;i<L1;i++)
		{
			for(int j=0;j<L2;j++)
			{
				System.out.print(Weight12[i][j]+"	");
			}
			System.out.println();
		}
		System.out.println("阈值Threshold1:");
		for(int i=0;i<L1;i++)
		{
			System.out.print(Threshold1[i]+"	");
		}
		System.out.println("\n阈值Threshold2:");
		for(int i=0;i<L2;i++)
		{
			System.out.print(Threshold2[i]+"	");
		}
		System.out.println("\n权值阈值打印完毕！");
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}
}
