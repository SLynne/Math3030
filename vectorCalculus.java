import java.util.Arrays;

/*
 * Stephanie L. Wyckoff
 * 8 July 2017
 * The problem. Five points A, B, C, D1 and D2 in R^3 are given.
 * (1) Determine whether three points A, B, C are collinear (i.e. lie on the same line).
 * (2) If A, B, C are not collinear, find the plane that contains these three points.
 * (3) Determine whether D1 and D2 are at the same side from the ABC-plane.
 * 
 * This program finds the vector between two points, the cross product, the plane equation. Points A, B, and C are taken in as arrays of size three and are populated
 * with user input. Points D1 and D2 are taken in as a sinle array of size 2. 
*/


import java.util.Scanner;

public class project {

	
	
	//Determine whether three points a, b, c are collinear (i.e. lie on the same line)
	public static int[] isCollinear(int[] a, int[] b, int[] c){
		
		//collinear equation
		int[] ab = new int[3];
		int[] ac = new int[3];
		int[] bc = new int[3];
		int[] tru = {1,1,1};
		
		int[] normal = new int[3];
				
		//find line ab
		for(int i = 0; i < 3; i++){
			ab[i] = b[i]-a[i];
		}
		
		//find line ac
		for(int j = 0; j < 3; j++){
			ac[j] = c[j]-a[j];
		}
	
		//find line bc
		for(int k = 0; k < 3; k++){
			bc[k] = c[k]-b[k];
		}
		
	
		//determine if triple product is zero vector
		normal[0] = bc[0]*((ab[1] * ac[2]) - (ab[2] * ac[1]));
		
		normal[1] = bc[1]*((ab[0] * ac[2]) - (ab[2] * ac[0]));
		
		normal[2] = bc[2]*((ab[0] * ac[1]) - (ab[1] * ac[0]));
		
		//checks if normal is zero vector, if zero vector, collinear
		if((normal[0] == 0) && (normal[1] == 0) && (normal[2] == 0)){
			return tru;
			
		}
			
	
		return notCollinearFindPlane(ab, ac, bc);
		
	
				
	}
	
	
	//If a, b, c are not collinear, find the plane that contains these three points
	public static int[] notCollinearFindPlane(int[] ab, int[] ac, int[] bc){
		
		int[] normal = new int[3];
	
		//cross product ab x bc
		normal[0] = ((ab[1] * ac[2]) - (ab[2] * ac[1]));
		
		normal[1] = ((ab[0] * ac[2]) - (ab[2] * ac[0]));
		
		normal[2] = ((ab[0] * ac[1]) - (ab[1] * ac[0]));
		
		return normal; //returns the normal vector, use this send this to the sSOP method!
	}
	
	
	//Determine whether D1 and D2 are at the same side from the a, b, c-plane
	public static int sameSideOfPlane(int[] d1, int[] d2, int[] a, int[] n){
		
		//n1(x-x0)-n2(y-y0)+n3(z-z0)=0
		//n1(d11-a1)-n2(d12-a2)+n3(d13-a3)
		//n1(d21-a1)-n2(d22-a2)+n3(d23-a3)
		//x,y,z = d11, d12, d13
		//x,y,z = d21, d22, d23
		//x0, y0, z0 = a1, a2, a3
		
		int p1 = (n[0] * (d1[0] - a[0])) - (n[1] * (d1[1] - a[1])) + (n[2] * (d1[2] - a[2]));
		int p2 = (n[0] * (d2[0] - a[0])) - (n[1] * (d2[1] - a[1])) + (n[2] * (d2[2] - a[2]));
		System.out.println("p1 is " + p1);
		System.out.println("p2 is " + p2);
		
		//if p1 or p2 == 0, then p1,p2 is on the plane
		if(p1 == 0){
			if(p2 == 0){
				return 2; //both are on the plane
			}
			else{
				return 3; //p1 point is on the plane 
			}
		}
		
		if(p2 == 0){
			if(p1 == 0){
				return 2; //both are on the plane
			}
			else{
				return 4; //p2 point is on the plane 
			}
		}
		
		if((p1 > 0 && p2 > 0) || (p1 < 0 && p2 < 0)){ //if both p1 and p2 are pos or both neg, then both points are on same side of plane
			return 1;
		}
		
		
		
		return 5;//the signs do not match, points are on opposite sides of plane
	}
	
	
	public static void main(String[] args){
				
		System.out.println("Please enter the coordinates of point A: ");
		System.out.print("Point A1: ");
		
		Scanner input = new Scanner(System.in);
			
		int[] pointA = new int[3]; 
		
		pointA[0] = input.nextInt();
		System.out.print("Point A2: ");
		pointA[1] = input.nextInt();
		System.out.print("Point A3: "); 
		pointA[2] = input.nextInt();
		
		int[] pointB = new int[3]; 
		System.out.println("Please enter the coordinates of point B: ");
		System.out.print("Point B1: ");
		pointB[0] = input.nextInt();
		System.out.print("Point B2: ");
		pointB[1] = input.nextInt();
		System.out.print("Point B3: "); 
		pointB[2] = input.nextInt();
		
		int[] pointC = new int[3]; 
		System.out.println("Please enter the coordinates of point C: ");
		System.out.print("Point C1: ");
		pointC[0] = input.nextInt();
		System.out.print("Point C2: ");
		pointC[1] = input.nextInt();
		System.out.print("Point C3: "); 
		pointC[2] = input.nextInt();
		
		int[] tru = isCollinear(pointA, pointB, pointC);
		
		//if tru array is [1,1,1,], then array is collinear. [1,1,1] is specific test case and can only be returned
		if((tru[0] == 1) && (tru[1] == 1) && (tru[2] == 1)){
			System.out.println("Point A " + java.util.Arrays.toString(pointA) + ", Point B " + java.util.Arrays.toString(pointB) + ", and Point C " + java.util.Arrays.toString(pointC) +", are Collinear.");
		}
			else{

				int[] plane = isCollinear(pointA, pointB, pointC);
				
				
				int[] pointD1 = new int[3];
				System.out.println("Please enter the coordinates of point D1: ");
				System.out.println("Point D1.1: ");
				pointD1[0] = input.nextInt();
				System.out.println("Point D1.2: ");
				pointD1[1] = input.nextInt();
				System.out.println("Point D1.3: ");
				pointD1[2] = input.nextInt();
				
				int[] pointD2 = new int[3];
				System.out.println("Please enter the coordinates of point D2: ");
				System.out.println("Point D2.1: ");
				pointD2[0] = input.nextInt();
				System.out.println("Point D2.2: ");
				pointD2[1] = input.nextInt();
				System.out.println("Point D2.3: ");
				pointD2[2] = input.nextInt();
				
				
				if(sameSideOfPlane(pointD1, pointD2, pointA, plane) == 1){
					System.out.println("Point A " + java.util.Arrays.toString(pointA) + ", Point B " + java.util.Arrays.toString(pointB) + ", and Point C " + java.util.Arrays.toString(pointC) + ", are not Collinear, lie on the plane " + plane[0] + "(x - " + pointA[0] + ") - " + plane[1] + "(y - " + pointA[1] +") + " + plane[2] + "(z - " + pointA[2] + ") = 0, and are on the same side of the ABC-plane.");
				}
				else if(sameSideOfPlane(pointD1, pointD2, pointA, plane) == 2){
					System.out.println("Point A " + java.util.Arrays.toString(pointA) + ", Point B " + java.util.Arrays.toString(pointB) + ", and Point C " + java.util.Arrays.toString(pointC) + ", are not Collinear, lie on the plane " + plane[0] + "(x - " + pointA[0] + ") - " + plane[1] + "(y - " + pointA[1] +") + " + plane[2] + "(z - " + pointA[2] + ") = 0, and are on the ABC-plane.");
				}
				if(sameSideOfPlane(pointD1, pointD2, pointA, plane) == 3){
					System.out.println("Point A " + java.util.Arrays.toString(pointA) + ", Point B " + java.util.Arrays.toString(pointB) + ", and Point C " + java.util.Arrays.toString(pointC) + ", are not Collinear, lie on the plane " + plane[0] + "(x - " + pointA[0] + ") - " + plane[1] + "(y - " + pointA[1] +") + " + plane[2] + "(z - " + pointA[2] + ") = 0, and are on the same side of the ABC-plane, D1 is in the plane.");
				}
				if(sameSideOfPlane(pointD1, pointD2, pointA, plane) == 4){
					System.out.println("Point A " + java.util.Arrays.toString(pointA) + ", Point B " + java.util.Arrays.toString(pointB) + ", and Point C " + java.util.Arrays.toString(pointC) + ", are not Collinear, lie on the plane " + plane[0] + "(x - " + pointA[0] + ") - " + plane[1] + "(y - " + pointA[1] +") + " + plane[2] + "(z - " + pointA[2] + ") = 0, and are on the same side of the ABC-plane, D2 is in the plane.");
				}
				if(sameSideOfPlane(pointD1, pointD2, pointA, plane) == 5){
					System.out.println("Point A " + java.util.Arrays.toString(pointA) + ", Point B " + java.util.Arrays.toString(pointB) + ", and Point C " + java.util.Arrays.toString(pointC) + ", are not Collinear, lie on the plane " + plane[0] + "(x - " + pointA[0] + ") - " + plane[1] + "(y - " + pointA[1] +") + " + plane[2] + "(z - " + pointA[2] + ") = 0, and are not on the same side of the ABC-plane.");
	
				}
				
				
				}
			}
		}
	
	
	
	

