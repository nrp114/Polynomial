package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	 /**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		Node ptrn1= poly1;
		Node ptrn2 = poly2;
		Node first=null; 
		Node last=null;
		while(ptrn1 != null && ptrn2 != null)
		{
			float sum=0;
			if(ptrn1.term.degree > ptrn2.term.degree)
			{
				Node result= new Node(ptrn2.term.coeff, ptrn2.term.degree, null);
				if(last != null)
				{
					last.next=result;
				}
				else
				{
					first=result;
				}
				last=result;
				ptrn2=ptrn2.next;
			}
			else if(ptrn1.term.degree < ptrn2.term.degree)
				{
					Node result= new Node(ptrn1.term.coeff, ptrn1.term.degree, null);
					if(last != null)
					{
						last.next=result;
					}
					else
					{
						first=result;
					}
					last=result;
					ptrn1=ptrn1.next;
				}
				else if(ptrn1.term.degree == ptrn2.term.degree)
					{
						sum= ptrn1.term.coeff + ptrn2.term.coeff;
						if (sum == 0) {
							ptrn1=ptrn1.next;
							ptrn2=ptrn2.next;
							continue;
						}
						Node result= new Node(sum, ptrn1.term.degree, null);
						if(last != null)
						{
							last.next=result;
						}
						else
						{
							first=result;
						}
						last=result;
						ptrn1=ptrn1.next;
						ptrn2=ptrn2.next;
				}
		}
		while(ptrn1 == null && ptrn2 != null)
		{
			Node result= new Node(ptrn2.term.coeff, ptrn2.term.degree, null);
			if(last != null)
			{
				last.next=result;
			}
			else
			{
				first=result;
			}
			last=result;
			ptrn2=ptrn2.next;
		}
		while(ptrn1 != null && ptrn2 == null)
		{
			Node result= new Node(ptrn1.term.coeff, ptrn1.term.degree, null);
			if(last != null)
			{
				last.next=result;
			}
			else
			{
				first=result;
			}
			last=result;
			ptrn1=ptrn1.next;
		}
		return first;
	}
	/*	
Node ptr_final= null;
Node ptr1 = poly1;
Node ptr2 = poly2;

if(ptr1 != null || ptr2 != null) {
	
	if(ptr1 == null && ptr2 != null) {
		return ptr2;
	}
	if(ptr2 == null && ptr1 != null) {
		return ptr1;
	}

	
if(ptr1.term.degree > ptr2.term.degree) {
	 ptr_final = new Node(ptr1.term.coeff,ptr1.term.degree,null);
	ptr1=ptr1.next;
}
else if(ptr2.term.degree > ptr1.term.degree) {
	 ptr_final = new Node(ptr2.term.coeff,ptr2.term.degree,null);
	ptr2=ptr2.next;
}
else {
	float coeff_sum= ptr1.term.coeff + ptr2.term.coeff;
	 ptr_final = new Node(coeff_sum,ptr2.term.degree,null);
	ptr1 = ptr1.next;
	ptr2= ptr2.next;
}


while(ptr1 != null && ptr2 != null) {
	if(ptr1.term.degree > ptr2.term.degree) {
		Node demo = new Node(ptr1.term.coeff, ptr1.term.degree , ptr_final);
		ptr_final = demo;
		ptr1 = ptr1.next;
	}
	else if(ptr2.term.degree > ptr1.term.degree) {
		Node demo = new Node(ptr2.term.coeff, ptr2.term.degree , ptr_final);
		ptr_final = demo;
		ptr2=ptr2.next;
	}
	else {
		float coeff_sum= ptr1.term.coeff + ptr2.term.coeff;
		Node n = new Node(coeff_sum,ptr1.term.degree,ptr_final);
		ptr_final = n;
		ptr1=ptr1.next;
		ptr2=ptr2.next;
		
	}

}

while(ptr1 != null) {
	Node demo = new Node(ptr1.term.coeff, ptr1.term.degree , ptr_final);
	ptr_final = demo;
	ptr1 = ptr1.next;
	
}
while(ptr2 != null) {
	Node demo = new Node(ptr2.term.coeff, ptr2.term.degree , ptr_final);
	ptr_final = demo;
	ptr2 = ptr2.next;
}
return ptr_final;

}	
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
	//	return null;
	//}
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
Node first_term=null, last_term= null;
		for(Node ptr1= poly1; ptr1 != null; ptr1=ptr1.next)
		{
			for(Node ptr2= poly2; ptr2 != null; ptr2= ptr2.next)
			{
				float result= (ptr1.term.coeff)*(ptr2.term.coeff);
				int degree= (ptr1.term.degree)+(ptr2.term.degree);
				Node resultLL= new Node(result, degree, null);
				if(last_term != null)
				{
					last_term.next=resultLL;
				}
				else
				{
					first_term=resultLL;
				}
				last_term=resultLL;
			}
		}
		Node finalfront=null;
		Node finallast = null;
		if (first_term == null || last_term == null) {
			return null;
		}
		int highestOrder= last_term.term.degree;
		for(int d=0; d <= highestOrder; d++)
		{
			float sum= 0;
			for(Node ptr= first_term; ptr!= null; ptr=ptr.next)
			{
				if(ptr.term.degree == d)
				{
					sum+= ptr.term.coeff;
				}
			}
			if (sum == 0) continue;
			Node tempNode = new Node(sum, d, null);
			if(finallast != null)
			{
				finallast.next = tempNode;
			}
			else
			{
				finalfront = tempNode;
			}
			finallast = tempNode;
		}
		return finalfront;
	}
	/*
		Node ptr1= poly1;
		Node ptr2 = poly2;
		
		if(ptr1==null || ptr2==null ) {
			return null;
		}
		
		while(ptr1.next != null) {
			ptr1 = ptr1.next;
		}
		int poly1_highestdegree = ptr1.term.degree;
		while(ptr2.next != null) {
			ptr2 = ptr2.next;
		}
		int poly2_highestdegree = ptr2.term.degree;
		
		int sizeOfPoly = poly1_highestdegree + poly2_highestdegree;
		
		Node result = new Node(0,0,null);
		Node head = result;
		Node tail = result;
		for(int i = 0; i< sizeOfPoly; i++) {
			Node n = new Node(0,i,head);
			head = n;
		}
		Node n = new Node(0,sizeOfPoly,head);
		head = n;
		
		ptr1 = poly1;
		ptr2 = poly2;
		while(ptr1 != null) {
			ptr2 = poly2;
			while(ptr2 != null) {
				int degree = ptr1.term.degree + ptr2.term.degree;
				float coeff = ptr1.term.coeff*ptr2.term.coeff;
				Node pointer = head;
				for(int j=0; j<sizeOfPoly-degree; j++) {
					pointer = pointer.next;
				}
				pointer.term.coeff = coeff + pointer.term.coeff;
				ptr2 = ptr2.next;
						}
			ptr1 = ptr1.next;
		}
		
		
		
		Node checker = head;
		Node checker2 = tail; 
		while(checker.term.coeff == 0) {
			checker = checker.next;
			head = head.next;
		}
		while(checker2.term.coeff == 0) {
			while(checker.next != checker2) {
				checker= checker.next;
			}
			checker.next=null;
			checker2 = checker;
			checker = head;
		}
		
		while(checker != checker2) {
			while(checker.next.term.coeff == 0) {
				checker.next = checker.next.next;
			}
			checker = checker.next;
		}
		return head;
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		float result_term = 0;
		Node front = poly;
		while (front != null) {
			
			double term = Math.pow(x,front.term.degree) * front.term.coeff;
			result_term = (float) (result_term + term);
			front = front.next;
		}
		return result_term;
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}
	
}