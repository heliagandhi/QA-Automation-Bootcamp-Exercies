package Task2;

/******************** Importing Essential Libraries ************************/
import java.util.Scanner;


/*************************** MENU OF KALKULATOR ****************************/
class MainMenu
{
  public void menu()
  {
    System.out.println("\t\t\t****************************************************");
    System.out.println("\t\t\t\t  KALKULATOR - HELIA");
    System.out.println("\t\t****************************************************");
    System.out.println("\t\t\t         --------------------");
    System.out.println("\t\t\t       Sebagai Tugas Pertemuan 1");
    System.out.println("\t\t\t         --------------------");
    System.out.println("\n\nPress 1. Penjumlahan");
	System.out.println("Press 2. Pengurangan");
	System.out.println("Press 3. Perkalian");
	System.out.println("Press 4. Pembagian");
	System.out.println("Press 5. Modulus");
	System.out.println("Press 6. Akar");
	System.out.println("Press 7. Pangkat");
	System.out.println("Press 8. Persen");
	System.out.println("Press 9. Keluar");

  }
}

/************************ Keluar  *********************/
class CodeExit
{
  public void out()
  {
    System.out.println("\n*****************************************");
    System.out.println("$ Terima kasih anda telah mencobanya :) ");
    System.out.println("*****************************************");
    System.out.println("------sampai jumpa----------");
    System.exit(0);
  }
}


/***************************** Main Class *******************************/
public class Calculator {

	public static void main(String[] args) {

		/** clear Screen **/
	    System.out.print("\033[H\033[2J");

		Scanner sc = new Scanner(System.in);
		
		int pilih = 0;
		double hasil;
		double nilai1 = 0;
		double nilai2 = 0;
		
		/*** Call Mainmenu Class function ****/
	    MainMenu mm = new MainMenu();
	    mm.menu();
	    
	    while(pilih<10)
	    {
	    	System.out.print("\nPlease Enter choice :");
  	      	pilih = sc.nextInt();
  	      
	    	if(pilih == 9) {
	  	      	CodeExit obj = new CodeExit();
				obj.out();
	    	}
	    	
	    	boolean check = false;
	    	while(check == false) {
	    		String errorMessage = "";
	    		try {
	    		Scanner scan1 = new Scanner(System.in);
	    		System.out.print("Masukan nilai 1 yang akan dihitung  : ");
	    		nilai1 = scan1.nextDouble();
	    		System.out.print("Masukan nilai 2 yang akan dihitung  : ");
	    		nilai2 = scan1.nextDouble();
		    	}
		    	catch (Exception e1) {
					errorMessage = e1.toString();
					System.out.print((char)27+"[01;31m \nERROR, MASUKAN ANGKA!\n\n"+(char)27+"[00;00m");
//					System.out.println(errorMessage);
				}
	    		if(errorMessage.isEmpty()) {
	    			check = true;
	    		}else {
	    			check = false;
	    		}
	    		
	    	}
	    	
	   	    switch(pilih) {
	   	    	case 1:
					hasil=nilai1+nilai2;
					System.out.println ("Hasil Penjumlahan = "+hasil);
					break;
				case 2:
					hasil=nilai1-nilai2;
					System.out.println ("Hasil Pengurangan = "+hasil);
					break;
				case 3:
					hasil=nilai1*nilai2;
					System.out.println ("Hasil Perkalian = "+hasil);
					break;
				case 4:
					hasil=nilai1/nilai2;
					System.out.println ("Hasil Pembagian = "+hasil);
					break;
				case 5:
					hasil=nilai1%nilai2;
					System.out.println ("Hasil Modulus = "+hasil);
					break;
				case 6:
					hasil = Math.sqrt(nilai1);
					System.out.println("Hasil Akar  : " +hasil);
					break;
				case 7:
					hasil = Math.pow(nilai1,nilai2);
					System.out.println("Hasil Pangkat  : " +hasil);
					break;
				case 8:
					hasil = 100*(nilai1)/(nilai2)/(nilai1+nilai2);
					System.out.println("Hasil Persen  : " +hasil+ "%");
					break;		
				}//tutup switch case
	    }//tutup while
	}//tutup main 
}

