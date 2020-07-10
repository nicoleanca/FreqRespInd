package project.nicole.freqrespind.data;

public class Cmplx {
   private double real, img;

   //constructor to initialize the complex number
   //as complex = real + img * i;
   Cmplx(double real, double img) {
	    this.real = real;
	    this.img = img;
   }

   public static Cmplx sum(Cmplx c1, Cmplx c2) {
	 //creating a temporary complex number to hold the sum of two numbers
      Cmplx temp = new Cmplx(0, 0);

      temp.real = c1.real + c2.real;
      temp.img = c1.img + c2.img;

      //returning the output complex number
      return temp;
    }

    public static Cmplx div(Cmplx c1, Cmplx c2) {
   	//division of complex numbers by multiplying with conjugate
        Cmplx temp = new Cmplx(0, 0);
        temp.real = (c1.real * c2.real + c1.img * c2.img) /
                     (c2.real * c2.real + c2.img * c2.img);
        temp.img = (c1.img * c2.real - c1.real * c2.img)/
                    (c2.real * c2.real + c2.img * c2.img);
       //returning the output complex number
       return temp;
    }

    //method to return real part
    public static double getReal(Cmplx c1) { return c1.real; }

    //method to return imaginary part
    public static double getImg(Cmplx c1)
     {
       return c1.img;
     }

    public String toString()
    {
      return real + " + " + img + "i";
    }
}
