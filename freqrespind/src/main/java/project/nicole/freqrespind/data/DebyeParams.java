package project.nicole.freqrespind.data;

import java.io.File;
import java.util.Scanner;

public class DebyeParams {
	private double conduct_sg_0;
	private double e_stat;
	private double e_inf;
	private double tau;

	public double getConduct_sg_0() {
		return conduct_sg_0;
	}

	public void setConduct_sg_0(double conduct_sg_0) {
		this.conduct_sg_0 = conduct_sg_0;
	}

	public double getE_stat() {
		return e_stat;
	}

	public void setE_stat(double e_stat) {
		this.e_stat = e_stat;
	}

	public double getE_inf() {
		return e_inf;
	}

	public void setE_inf(double e_inf) {
		this.e_inf = e_inf;
	}

	public double getTau() {
		return tau;
	}

	public void setTau(double tau) {
		this.tau = tau;
	}

	public void readDebye(long id) throws Exception {
		int noOfDouble = 0;
		File file = new File("./src/main/java/project/nicole/freqrespind/debye_input_data/" + id + "_DebyeParam.txt");
		Scanner scParam = new Scanner(file);

		//read the file called id_DebyeParam.txt skipping first line
		//label can be implemented - first line of each debyeParam file has the name of the respective tissue
		String label = scParam.nextLine();
		while (scParam.hasNextDouble()) {
			noOfDouble++;
			double curretLine = scParam.nextDouble();

			//add each Debye value
			switch(noOfDouble) {
				case 1: this.conduct_sg_0 = curretLine;
					break;
				case 2: this.e_stat = curretLine;
					break;
				case 3: this.e_inf = curretLine;
					break;
				case 4: this.tau = curretLine;
					break;
			}
		}
	}

	//function to calculate data for plotting chart on website
	public ConductivityAndPermittivity chartData(){
		ConductivityAndPermittivity conductivityAndPermittivity = new ConductivityAndPermittivity();

		double omg_0 = 2 * Math.PI * expoFunc(9);
		double dlt_ep = e_stat - e_inf;
		double tau_1_nl = tau * omg_0;
		double ep_0 = 8.854 * expoFunc(-12);
		double[] omg = new double[61];

		for(int i = 1; i <= 60; i++) {
			omg[i] = 2 * Math.PI * expoFunc(8)*i;
			Cmplx epsi = epsilon(omg[i], omg_0, ep_0, e_inf, dlt_ep, tau_1_nl, conduct_sg_0);
			double realepsi = Cmplx.getReal(epsi);
			double imgepsi = Cmplx.getImg(epsi);
			imgepsi = imgepsi * (-1) * omg[i] * ep_0;
			conductivityAndPermittivity.addConductivity(realepsi);
			conductivityAndPermittivity.addPermittivity(imgepsi);
		}
		return conductivityAndPermittivity;
	}

	//function to represent powers of 10
	public static double expoFunc(int expo) {
		double sol = 1;
		if(expo > 0)
		{
			for(int i = 1; i <= expo; i++)
				sol*= 10;
		}
		else
		{
			expo = expo * (-1);
			for(int i = 1; i <= expo; i++)
				sol*= 10;
		}
		return sol;
	}

	//function to calculate epsilon
	public static Cmplx epsilon(double omg, double omg_0, double ep_0, double e_inf,
								double dlt_ep, double tau_1_nl, double conduct_sg_0) {
		Cmplx epsi = new Cmplx(0,0);
		Cmplx e_inf_c = new Cmplx(e_inf, 0.0);
		Cmplx dlt_ep_c = new Cmplx(dlt_ep, 0.0);
		Cmplx tau_c = new Cmplx(1.0, tau_1_nl * omg / omg_0);
		Cmplx conduct_sg_0_c = new Cmplx(conduct_sg_0, 0.0);
		Cmplx omg_ep_c = new Cmplx(0.0, omg * ep_0);

		epsi = Cmplx.sum(Cmplx.sum(e_inf_c, Cmplx.div(dlt_ep_c, tau_c)),
				Cmplx.div(conduct_sg_0_c, omg_ep_c));

		return epsi;
	}

}
